package base_msg;

/**
 * Created by Mark on 2/20/2017.
 */
public class P2PMessage implements PeerMessage {

    private int messageLength;
    private int messageType;
    private int pieceIndex;
    private Piece data;
    private BitFieldHandler handler = null;
    public int messageNumber = 0;
    private static int messageCounter = 0;

    /**
     * Instantiates a new p2p message.
     */
    private P2PMessage(){

    }

    /**
     * Gets the single instance of P2PMessage.
     *
     * @return single instance of P2PMessage
     */
    public static P2PMessage getInstance(){
        P2PMessage message = new P2PMessage();
        boolean isSuccessful = message.init();

        if (isSuccessful == false) {
            message.close();
            message = null;
        }

        return message;
    }

    private boolean init(){
        messageCounter++;
        messageNumber = messageCounter;
        return true;
    }

    public void close(){

    }

    public int getPieceIndex(){
        return pieceIndex;
    }

    // Return the message data
    public Piece getData(){
        return data;
    }

    public BitFieldHandler getHandler(){
        return handler;
    }


    public int setMessageType(int messageType){
        return this.messageType = messageType;
    }

    public void setData(Piece data){
        this.data = data;
    }

    public void setPieceIndex(int pieceIndex){
        this.pieceIndex = pieceIndex;
    }

    public void setMessageLength(int messageLength){
        this.messageLength = messageLength;
    }

    public void setHandler(BitFieldHandler hander){
        this.handler = hander;
    }


    public int getType(){
        return this.messageType;
    }

    public int getMessageNumber(){
        return messageNumber;
    }
}
