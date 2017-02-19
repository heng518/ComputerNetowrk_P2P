import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

            if (tokens[3].equals("1")){
                peer.setFileExists(true);
            }else{
                peer.setFileExists(false);
            }

            peerList.put(peerId, peer);
            line = peerInfoReader.readLine();
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



        if (peerList.get(peerId).getFileExists() == false){

        }else{

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
}
