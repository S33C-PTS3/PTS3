/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.Game;
import Lobby.User;
import Shared.IHockeyField;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import observer.BasicPublisher;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Eric The HockeyField represents the board where the game is played on
 */
public class HockeyField extends UnicastRemoteObject implements RemotePublisher, IHockeyField {

    final private float sizefactor = 0.7f;
    int middleX = 0;
    int middleY = 0;
    SideColor[] colors;

    private Puck puck;
    private Side[] sides;
    private SideName lastHitSide;
    private Side hitSide;
    private Side hitBat;
    private Side goal;
    private List<Side> batsHitsPuck;
    private int rounds;
    private ArrayList<Integer> scores;
    private List<Side> gameResult = null;
    private boolean gameOver = false;
    private Mode mode;
    private BasicPublisher publisher;

    /**
     * Constructor used for HockeyField.
     */
    public HockeyField() throws RemoteException {
        publisher = new BasicPublisher(new String[]{"bat", "puck"});
        puck = new Puck();
    }

    @Override
    public void init(Mode mode) {
        this.mode = mode;
        this.lastHitSide = null;
        hitSide = null;
        batsHitsPuck = new ArrayList<>();
        rounds = 1;
        scores = new ArrayList<>();
        gameResult = new ArrayList();

        sides = new Side[3];
        colors = new SideColor[3];
        colors[0] = SideColor.BLUE;
        colors[1] = SideColor.RED;
        colors[2] = SideColor.GREEN;
        int zijdeX1 = Math.round(400 * sizefactor);
        int zijdeY1 = 0;
        int zijdeX2 = 0;
        int zijdeY2 = Math.round(693 * sizefactor);
        SideName sideName = SideName.LEFT;
        IPlayer player = null;

        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                zijdeX1 = zijdeX2;
                zijdeY1 = zijdeY2;
            } else if (i == 0) {
                if (this.mode.equals(Mode.SINGLE)) {
                    try {
                        player = new RobotPlayer("Karel");
                    } catch (RemoteException ex) {
                        Logger.getLogger(HockeyField.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (this.mode.equals(Mode.MULTI)) {
                    //make new human player
                    System.out.println("New human player: Karel");
                    try {
                        player = new HumanPlayer("Karel");
                    } catch (RemoteException ex) {
                        Logger.getLogger(HockeyField.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (i == 1) {
                zijdeX2 = Math.round(800 * sizefactor);
                zijdeY2 = Math.round(693 * sizefactor);
                sideName = SideName.BOTTOM;
                try {
                    player = new HumanPlayer("joepkerste");
                } catch (RemoteException ex) {
                    Logger.getLogger(HockeyField.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (i == 2) {
                zijdeX2 = Math.round(400 * sizefactor);
                zijdeY2 = 0;
                sideName = SideName.RIGHT;
                if (mode.equals(Mode.SINGLE)) {
                    try {
                        player = new RobotPlayer("Sjef");
                    } catch (RemoteException ex) {
                        Logger.getLogger(HockeyField.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (this.mode.equals(Mode.MULTI)) {
                    System.out.println("New human player: Sjef");
                    try {
                        player = new HumanPlayer("Human");
                    } catch (RemoteException ex) {
                        Logger.getLogger(HockeyField.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            Side s = new Side(zijdeX1, zijdeY1, zijdeX2, zijdeY2, colors[i], sideName, player);
            sides[i] = s;
            middleX += zijdeX2;
            middleY += zijdeY2;
        }
        middleX /= 3;
        middleY /= 3;
    }

    /**
     * Method to check if the puck collided with a side, a goal or a bat and
     * changes its direction
     */
    public void checkColl() {
        for (int i = 0; i < sides.length; i++) {
            hitSide = sides[i].ballHitsWall(puck.getPosition().getX(), puck.getPosition().getY(), puck.getVelocity().getX(), puck.getVelocity().getY());
            hitBat = sides[i].hasCollided(puck);
            goal = sides[i].checkGoalLine(puck.getPosition().getX(), puck.getPosition().getY(), puck.getVelocity().getX(), puck.getVelocity().getY(), puck.getRadius());
            if (hitSide != null && lastHitSide != hitSide.getSideName()) {
                puck.setVelocityWithoutNormal(hitSide.getStartPoint(), hitSide.getEndPoint());
                lastHitSide = sides[i].getSideName();
                hitSide = null;
            }
            if (hitBat != null && lastHitSide != hitBat.getBat().getSideName()) {
                puck.setVelocityWithNormal(hitBat.getBat().getPosition(), puck.getPosition());
                if (batsHitsPuck.isEmpty()) {
                    batsHitsPuck.add(hitBat);
                } else if (!hitBat.equals(batsHitsPuck.get(batsHitsPuck.size() - 1))) {
                    batsHitsPuck.add(hitBat);
                }
                lastHitSide = sides[i].getBat().getSideName();
                hitBat = null;
            }
            if (goal != null) {
                System.out.println(goal.getSideName().toString());

                if (!batsHitsPuck.isEmpty()) {

                    if (goal.equals(batsHitsPuck.get(batsHitsPuck.size() - 1))) {
                        if (batsHitsPuck.size() <= 1) {
                            System.out.println("eigen goal gemaakt en aangeraakt door" + batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().changeScore(-1);
                        } else {
                            System.out.println("1 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().toString());
                            System.out.println("1 Gescoord bij " + goal.getBoundPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().changeScore(-1);
                        }
                        resetGame();

                    } else if (batsHitsPuck.size() > 0) {
                        System.out.println("2 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().toString());
                        System.out.println("2 Gescoord bij " + goal.getBoundPlayer().toString());
                        batsHitsPuck.get(batsHitsPuck.size() - 1).getBoundPlayer().changeScore(1);
                        goal.getBoundPlayer().changeScore(-1);
                        resetGame();
                    }
                } else {
                    System.out.println("3 Eigen goal, niemand geraakt " + goal.getBoundPlayer().toString());
                    goal.getBoundPlayer().changeScore(-1);
                    resetGame();
                }
            }
        }
    }

    public Puck getPuck() {
        return this.puck;
    }

    public BasicPublisher getPublisher() {
        return this.publisher;
    }

    public List<Side> isGameOver() {
        if (gameOver) {
            return gameResult;
        } else {
            return null;
        }
    }

    public Mode getMode() {
        return this.mode;
    }

    public void movePuck() {
        this.puck.move();
        publisher.inform(this, "puck", null, puck.getPosition());
    }

    /**
     * Used to reset the ball and checks each time if there is a winner.
     */
    private void resetGame() {
        puck.setPosition(new Point2D(280, 323));
        puck.randomizePuck();
        this.batsHitsPuck.clear();
        this.hitBat = null;
        this.hitSide = null;
        this.lastHitSide = null;
        for (Side side : sides) {
            scores.add(side.getBoundPlayer().getInGameScore());
        }
        checkWinner();
    }

    public void movePlayerBat(KeyCode key) {
        Bat bat = sides[1].getBat();
        if (key.equals(KeyCode.LEFT)) {
            if (sides[1].getGoalX1() < bat.getXpos()) {
                bat.move("1left");
            }
        } else if (key.equals(KeyCode.RIGHT)) {
            if (sides[1].getGoalX2() > bat.getXpos()) {
                bat.move("1right");
            }
        }
    }

    public void moveAIPlayers() {
        if (puck.getPosition().getY() < sides[0].getBat().getYpos()
                && sides[0].getGoalY1() + 20 < sides[0].getBat().getYpos()) {
            sides[0].moveBat("0right");
        } else if (puck.getPosition().getY() > sides[0].getBat().getYpos()
                && sides[0].getGoalY2() - 10 > sides[0].getBat().getYpos()) {
            sides[0].moveBat("0left");
        }

        if (puck.getPosition().getY() > sides[2].getBat().getYpos()
                && sides[2].getGoalY2() - 10 > sides[2].getBat().getYpos()) {
            sides[2].moveBat("2right");
        } else if (puck.getPosition().getY() < sides[2].getBat().getYpos()
                && sides[2].getGoalY1() + 20 < sides[2].getBat().getYpos()) {
            sides[2].moveBat("2left");
        }
    }

    @Override
    public void setBindedPlayers(Game g) throws RemoteException {
        for (int i = 0; i < 3; i++) {
            IPlayer player = g.getUsersObject().get(i);
            int userID = g.getUsersObject().get(i).getID();
            player.setID(userID);
            sides[i].setBoundPlayer(player);
        }
    }

    /**
     * Checks if there is a winner.
     */
    public void checkWinner() {
        if (rounds < 10) {
            rounds++;
        } else {
            int highscore = 0;
            for (Side side : sides) {
                highscore = side.getBoundPlayer().getInGameScore();
                gameResult.add(side);
                gameOver = true;

            }
            Collections.sort(gameResult, new Comparator<Side>() {

                @Override
                public int compare(Side side1, Side side2) {
                    if (side1.getBoundPlayer().getInGameScore() > side2.getBoundPlayer().getInGameScore()) {
                        return -1;
                    }
                    if (side1.getBoundPlayer().getInGameScore() < side2.getBoundPlayer().getInGameScore()) {
                        return 1;
                    }
                    return 0;
                }
            });
        }
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.removeListener(listener, property);
    }

    @Override
    public double[] getPuckPosition() throws RemoteException {
        double[] positionArray = new double[2];
        positionArray[0] = puck.getPosition().getX();
        positionArray[1] = puck.getPosition().getY();
        return positionArray;
    }

    @Override
    public Side[] getSides() throws RemoteException {
        return this.sides;
    }

    @Override
    public void setPuckPosition(double x, double y) throws RemoteException {
        this.puck.setPosition(new Point2D(x, y));
    }

    @Override
    public int getRound() throws RemoteException {
        return this.rounds;
    }

    @Override
    public double getDiameter() throws RemoteException {
        return this.puck.getDiameter();
    }

    @Override
    public void setPlayerBatPosition(String direction, String userName) throws RemoteException {
        Player boundPlayer = null;
        for (Side s : sides) {
            boundPlayer = (Player) s.getBoundPlayer();
            s.getBat().move(String.valueOf(s.getBoundPlayer().getID()) + direction);
            System.out.println(s.getBoundPlayer().getID());
            break;
        }
    }
}
