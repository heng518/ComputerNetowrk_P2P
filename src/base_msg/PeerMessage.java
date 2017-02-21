package base_msg;
import java.io.Serializable;

/**
 * Created by Yanjun on 17/2/19.
 */
public interface PeerMessage extends Serializable {
    public int getType();
//    public int getMessageLength();
//    public int getMessagePayLoad();
    public int getMessageNumber();
}
