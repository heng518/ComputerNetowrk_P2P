import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Created by yaoheng on 2/19/17.
 */
public class clientThread extends Thread{
    Socket requestSocket;           //socket connect to the server
    ObjectOutputStream out;         //stream write to the socket
    ObjectInputStream in;          //stream read from the socket
    String message;                //message send to the server
    String MESSAGE;                //capitalized message read from the server
    private int port;
    private String ipAddress;
    public clientThread(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;
    }
    public void run()
    {
        try{
            //create a socket to connect to the server
            requestSocket = new Socket(this.ipAddress, this.port);
            System.out.println("Connected to " + this.ipAddress + " in port " + port);
            //initialize inputStream and outputStream
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //get Input from standard input
            message = String.valueOf(this.port);
            sendMessage(message);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                System.out.print("Send a message");
                //read a sentence from the standard input
                message = bufferedReader.readLine();
                //Send the sentence to the server
                sendMessage(message);
                //Receive the upperCase sentence from the server
                MESSAGE = (String)in.readObject();
                //show the message to the user
                System.out.println("Receive message: " + MESSAGE);
            }
        }
        catch (ConnectException e)
        {
            System.err.println("Connection refused. You need to initiate a server first.");
        }
        catch ( ClassNotFoundException e )
        {
            System.err.println("Class not found");
        }
        catch(UnknownHostException unknownHost)
        {
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            //Close connections
            try
            {
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }
    //send a message to the output stream
    void sendMessage(String msg)
    {
        try
        {
            //stream write the message
            out.writeObject(msg);
            out.flush();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}
