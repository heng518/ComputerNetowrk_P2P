package base_msg;

/**
 * Created by Yanjun on 17/2/19.
 */
public class HandshakeMessage implements PeerMessage{

    /** The hand shake header. */
    private String handShakeHeader;

    /** The peer id. */
    private String peerID;

    private static int instanceCounter;

    private int messageNumber;


    /**
     * Inits the. if successful return true.
     */

    private boolean init(){
        instanceCounter++;
        messageNumber = instanceCounter;
        return true;
    }

    /**
     * Close.
     */
    public void close(){

    }

    /**
     * Gets the single instance of HandshakeMessage, @return single instance of HandshakeMessage
     */
    public static HandshakeMessage getInstance(){
        HandshakeMessage handshakeMessage = new HandshakeMessage();
        boolean isSuccessful = handshakeMessage.init();
        if(isSuccessful == false){
            handshakeMessage.close();
            handshakeMessage = null;
        }
        return handshakeMessage;
    }

    public byte[] getHandshakeMessage(){
        return null;
    }

    public void setPeerID(String peerID) {
        this.peerID = peerID;
    }

    public int getMessageLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getType(){
        return MsgConstant.HANDSHAKE_MESSAGE;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}
