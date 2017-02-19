import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tom on 2/18/17.
 */
public class peerProcess {
    public static void main(String args[]) throws IOException {

        String peerId = args[0];

        LinkedHashMap<String, Peer> peerList = new LinkedHashMap<String, Peer>();

        //read PeerInfo.cfg and store in peerList
        BufferedReader peerInfoReader = new BufferedReader(new FileReader("PeerInfo.cfg"));
        String line = peerInfoReader.readLine();
        while (line != null){
            System.out.println(line);
            Peer peer = new Peer();
            String tokens[] = line.trim().split(" ");
            peer.setPeerId(tokens[0]);
            peer.setIpAddress(tokens[1]);
            peer.setPort(Integer.parseInt(tokens[2]));
            peer.createLog();
            if (tokens[3].equals("1")){
                peer.setFileExists(true);
            }else{
                peer.setFileExists(false);
            }

            peerList.put(tokens[0], peer);
            line = peerInfoReader.readLine();
        }
        
        for (Map.Entry<String, Peer> entry : peerList.entrySet()){
            String currentID = entry.getKey();
            if (currentID.equals(peerId))
            {
                peer.createServer();
                entry.getValue().connectToOtherPeer(peerList);
            }
        }

        HashMap<String, String> commonCfg = new HashMap<String, String>();

        //read common.cfg and store in commonCfg
        BufferedReader commonCfgReader = new BufferedReader(new FileReader("common.cfg"));
        line = commonCfgReader.readLine();
        while (line != null){
            System.out.println(line);
            String tokens[] = line.trim().split(" ");
            commonCfg.put(tokens[0], tokens[1]);

            line = commonCfgReader.readLine();
        }

        int pieceSize = Integer.parseInt(commonCfg.get("PieceSize"));
        int fileSize = Integer.parseInt(commonCfg.get("FileSize"));

        int numOfPiece = (int) Math.ceil(fileSize/pieceSize);

        boolean bitfield[] = new boolean[numOfPiece];

        //set bitfield value
        if (peerList.get(peerId).getFileExists() == false){
            for (int i = 0; i < bitfield.length; i++){
                bitfield[i] = false;
            }
        }else{
            for (int i = 0; i < bitfield.length; i++){
                bitfield[i] = true;
            }
        }
    }
}


class Peer {
    private String peerId;
    private String ipAddress;
    private int port;
    private boolean fileExists;

    public void setPeerId(String peerId){
        this.peerId = peerId;
    }
    public String getPeerId (){
        return peerId;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
    public String getIpAddress(){
        return ipAddress;
    }

    public void setPort(int port){
        this.port = port;
    }
    public int getPort(){
        return port;
    }

    public void setFileExists(boolean fileExists){
        this.fileExists = fileExists;
    }
    public boolean getFileExists(){
        return fileExists;
    }

    public void createLog()
    {
        String fileName = "peer_" + Integer.valueOf(this.peerId) + ".log";
        File logFile = new File(fileName);
        if(logFile.exists())
        {
            logFile.delete();
        }
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLog(String log)
    {
        try
        {
            FileWriter writer = new FileWriter("peer_" + Integer.valueOf(this.peerId) + ".log",true);
            writer.write("\n" + log);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createServer() throws IOException {
        ServerSocket listener = new ServerSocket(this.getPort());
        int clientNum = 1;
        try {
            while(true) {
                new Peer.Handler(listener.accept(), clientNum).start();
                System.out.println("Client "  + clientNum + " is connected!");
                clientNum++;
            }
        } finally {
            listener.close();
        }
    }

    public static class Handler extends Thread {
        private String message;    //message received from the client
        private String MESSAGE;    //uppercase message send to the client
        private Socket connection;
        private ObjectInputStream in;	//stream read from the socket
        private ObjectOutputStream out;    //stream write to the socket
        private int no;		//The index number of the client

        public Handler(Socket connection, int no) {
            this.connection = connection;
            this.no = no;
        }

        public void run() {
            try {
                //initialize Input and Output streams
                out = new ObjectOutputStream(connection.getOutputStream());
                out.flush();
                in = new ObjectInputStream(connection.getInputStream());
                try{
                    while(true)
                    {
                        //receive the message sent from the client
                        message = (String)in.readObject();
                        //show the message to the user
                        System.out.println("Receive message: " + message + " from client " + no);
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
                System.out.println("Disconnect with Client " + no);
            }
            finally{
                //Close connections
                try{
                    in.close();
                    out.close();
                    connection.close();
                }
                catch(IOException ioException){
                    System.out.println("Disconnect with Client " + no);
                }
            }
        }

        //send a message to the output stream
        public void sendMessage(String msg) {
            try{
                out.writeObject(msg);
                out.flush();
                System.out.println("Send message: " + msg + " to Client " + no);
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }

    }


    public void connectToOtherPeer(LinkedHashMap<String, Peer> peerList)
    {
        for (Map.Entry<String, Peer> entry : peerList.entrySet()){
            String peerID = entry.getKey();
            Peer currentPeer = entry.getValue();
            if (Integer.valueOf(peerID) < Integer.valueOf(this.peerId))
            {
                String peerIPAdress = currentPeer.getIpAddress();
                int peerPortNumber = currentPeer.getPort();
                try {
                    Socket socket = new Socket(peerIPAdress, peerPortNumber);
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                String time = dateFormat.format(new Date());
                String logContent = "[ " + time + " ]: Peer [peer_ID " + this.peerId + "] makes a connections to Peer [peer_ID " + peerID + "].";
                this.writeLog(logContent);
            }
        }
    }
}
