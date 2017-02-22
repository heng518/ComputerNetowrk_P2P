package base_msg;
import java.io.Serializable;

/**
 * Created by Yanjun on 17/2/19.
 */
public class HandshakeMsg implements Serializable {

    /** The hand shake header. */
    private String handShakeHeader;

    /** The peer id. */
    private String peerID;

    /**
     * Initialize the handshakeMsg
     */
    public HandshakeMsg(){
        handShakeHeader = MsgConstant.HANDSHAKE_HEADER_STRING;
    }

    /**
     * Get the handshakeMsg
     */
    public byte[] getHandshakeMsg(){
        return null;
    }

    /**
     * Set the PeerID
     */
    public void setPeerID(String peerID) {
        this.peerID = peerID;
    }

    /**
     * Gets the Message Length
     */
    public int getMessageLength() {
        return 0;
    }

    /**
     * Gets the Message Type
     */
    public int getType(){
        return MsgConstant.HANDSHAKE_MESSAGE;
    }

}
