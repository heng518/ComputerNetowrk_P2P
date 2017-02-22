import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;


/**
 * Created by yaoheng on 2/19/17.
 */
public class serverThread extends Thread{
    private int port;
    private ArrayList<Integer> connectedList = new ArrayList<>();
    public serverThread(int port) {
        this.port = port;
    }

    public void run() {

        try
        {
            System.out.println("The server is running.");
            ServerSocket listener = new ServerSocket(this.port);
            while(true)
            {
                new Handler(listener.accept(), connectedList).start();


                System.out.println("Client is connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Handler extends Thread {
    private ArrayList<Integer> connectList = new ArrayList<>();
    private String message;    //message received from the client
    private String MESSAGE;    //uppercase message send to the client
    private Socket connection;
    private ObjectInputStream in;	//stream read from the socket
    private ObjectOutputStream out;    //stream write to the socket

    public Handler(Socket connection, ArrayList<Integer> connectList) {
        this.connectList = connectList;
        this.connection = connection;
        System.out.println(connection.getInetAddress().toString().substring(1));
        System.out.println(connection.toString());
        new clientThread(connection.getInetAddress().toString(), connection.getPort()).start();
    }

    public void run() {
        try {

            //initialize Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            try {
                message = (String) in.readObject();
                if (!connectList.contains(Integer.valueOf(message)))
                {
                    new clientThread(connection.getInetAddress().toString().substring(1), Integer.valueOf(message)).start();
                }
                connectList.add(Integer.valueOf(message));
            }catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
            }
            try{
                while(true)
                {
                    System.out.println("Server established");
                    //receive the message sent from the client
                    message = (String)in.readObject();
                    //show the message to the user
                    System.out.println("Receive message: " + message + " from " + connection.getInetAddress().toString().substring(1));
                    //Capitalize all letters in the message
                    MESSAGE = message.toUpperCase();
                    //send MESSAGE back to the client
                    sendMessage(MESSAGE);
                }
            }
            catch(ClassNotFoundException classnot){
                System.err.println("Data received in unknown format");
            }
        }
        catch(IOException ioException){
            System.out.println("Disconnect with Client");
        }
        finally{
            //Close connections
            try{
                in.close();
                out.close();
                connection.close();
            }
            catch(IOException ioException){
                System.out.println("Disconnect with Client");
            }
        }
    }

    //send a message to the output stream
    public void sendMessage(String msg) {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("Send message: " + msg + " to Client");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

}