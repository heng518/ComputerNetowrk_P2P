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
        

        //for this peer, create server and connect to other peers
        peerList.get(peerId).createServer();
        peerList.get(peerId).connectToOtherPeer(peerList);


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
        (new Thread(new serverThread(this.getPort()))).start();

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
                (new Thread(new clientThread(peerIPAdress, peerPortNumber))).start();
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                String time = dateFormat.format(new Date());
                String logContent = "[ " + time + " ]: Peer [peer_ID " + this.peerId + "] makes a connections to Peer [peer_ID " + peerID + "].";
                this.writeLog(logContent);
            }
        }
    }
}
