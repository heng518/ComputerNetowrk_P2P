package base_msg;
import java.io.Serializable;

/**
 * Created by Mark on 2/21/2017.
 */
public class BaseMsg implements Serializable{

    private int messageLength;
    private int messageType;

    /**
     * Initialize base P2PMessage.
     */
    public BaseMsg(int messageType) {
        this.messageType = messageType;
    }

    /**
     * Get BaseMsg Type.
     */
    public int getMessageType(){
        return this.messageType;
    }

    /**
     * Set BaseMsg Type.
     */
    public void setMessageType(int messageType){
        this.messageType = messageType;
    }

    /**
     * Get BaseMsg Length.
     */
    public int getMessageLength(){
        return this.messageLength;
    }

    /**
     * Get BaseMsg Length.
     */
    public void setMessageLength(int messageLength){
        this.messageLength = messageLength;
    }
}
