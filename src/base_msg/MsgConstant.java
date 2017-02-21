package base_msg;

/**
 * Created by Yanjun on 17/2/19.
 */
public class MsgConstant {

    /** The Constant MAX_MESSAGE_SIZE. */
    public static final int MAX_MESSAGE_SIZE = 40000;

    /** The Constant RAW_DATA_SIZE. */
    public static final int RAW_DATA_SIZE = 1000;

    /** The Constant CHOKE_MESSAGE. */
    public static final byte CHOKE_MESSAGE = 0;

    /** The Constant UNCHOKE_MESSAGE. */
    public static final byte UNCHOKE_MESSAGE = 1;

    /** The Constant INTERESTED_MESSAGE. */
    public static final byte INTERESTED_MESSAGE = 2;

    /** The Constant NOT_INTERESTED_MESSAGE. */
    public static final byte NOT_INTERESTED_MESSAGE = 3;

    /** The Constant HAVE_MESSAGE. */
    public static final byte HAVE_MESSAGE = 4;

    /** The Constant BITFIELD_MESSAGE. */
    public static final byte BITFIELD_MESSAGE = 5;

    /** The Constant REQUEST_MESSAGE. */
    public static final byte REQUEST_MESSAGE = 6;

    /** The Constant PIECE_MESSAGE. */
    public static final byte PIECE_MESSAGE = 7;

    /** The Constant HANDSHAKE_MESSAGE. */
    public static final byte HANDSHAKE_MESSAGE = 8;

    /** The Constant SHUTDOWN_MESSAGE. */
    public static final byte SHUTDOWN_MESSAGE = 9;

    /** The Constant HANDSHAKE_HEADER_STRING. */
    public static final String HANDSHAKE_HEADER_STRING = "P2PFILESHARINGPROJ";

    public static String getMessageName(int type){
        if (type == MsgConstant.CHOKE_MESSAGE){
            return "CHOKE_MESSAGE";
        }

        if (type == MsgConstant.UNCHOKE_MESSAGE){
            return "UNCHOKE_MESSAGE";
        }

        if (type == MsgConstant.INTERESTED_MESSAGE){
            return "INTERESTED_MESSAGE";
        }

        if (type == MsgConstant.NOT_INTERESTED_MESSAGE) {
            return "NOT_INTERESTED_MESSAGE";
        }

        if (type == MsgConstant.HAVE_MESSAGE){
            return "HAVE_MESSAGE";
        }

        if (type == MsgConstant.BITFIELD_MESSAGE) {
            return "BITFIELD_MESSAGE";
        }

        if (type == MsgConstant.REQUEST_MESSAGE){
            return "REQUEST_MESSAGE";
        }

        if (type == MsgConstant.PIECE_MESSAGE){
            return "PIECE_MESSAGE";
        }

        if (type == MsgConstant.HANDSHAKE_MESSAGE){
            return "HANDSHAKE_MESSAGE";
        }

        if (type == MsgConstant.SHUTDOWN_MESSAGE){
            return "SHUTDOWN_MESSAGE";
        }

        return null;
    }

}
