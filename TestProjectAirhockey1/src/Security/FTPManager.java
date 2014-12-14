/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Joep Kerste
 */
public class FTPManager 
{
    private final String username = "airhockey%40joepkerste.nl";
    private final String password = "hebjeevenvoormij";
    private final String urlString = "ftp://" + username + ":" + password + "@joepkerste.nl/ServerIP.txt";
    
    public String getIP()
    {
        String ip = "";

        try 
        {
            URL url = new URL(urlString);
            URLConnection urlConn = url.openConnection();
            InputStream is = urlConn.getInputStream();
            
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String s = bf.readLine();
            System.out.println("Opgehaalde waarde: " + s);
            ip = s;

        } 
        catch (MalformedURLException ex) 
        {
            throw new RuntimeException("invalid URL");
        }
        catch (IOException ex) 
        {
            throw new RuntimeException("IO Exception");
        }
        
        return ip;
    }
    
    public void writeIP(String ip)
    {
        try 
        {
            URL url = new URL(urlString);
            URLConnection urlConn = url.openConnection();
            PrintStream ps = new PrintStream(urlConn.getOutputStream());
            ps.println(ip);
            ps.close();
            System.out.println("Server IP saved to web server.");
        } 
        catch (MalformedURLException ex) 
        {
            throw new RuntimeException("invalid URL");
        }
        catch (IOException ex) 
        {
            throw new RuntimeException("IO Exception");
        }
    }
}
