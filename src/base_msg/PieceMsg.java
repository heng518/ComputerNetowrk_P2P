package base_msg;

/**
 * Created by Mark on 2/21/2017.
 */
public class PieceMsg extends BaseMsg {

    private int pieceIndex;     // piece index field
    private byte[] content;     // content of the piece
    int loadSize;                   // size of the payload

    /**
     * Initialize PiecePayload.
     */
    public PieceMsg(int pieceIndex){
        super(MsgConstant.PIECE_MESSAGE);
        this.pieceIndex = pieceIndex;
    }

    /**
     * Get Content Size.
     */
    public int getSize(){
        if (this.content == null){
            return -1;
        }
        else{
            return this.content.length;
        }
    }

    /**
     * Get load size.
     */
    public int getLoadSize(){
        return this.loadSize;
    }

    /**
     * Get Piece Index.
     */
    public int getIndex(){
        return pieceIndex;
    }

    /**
     * Get Piece payload content.
     */
    public byte[] getContent(){
        return content;
    }

    /**
     * Set Piece payload content.
     */
    public void setContent(byte[] content){
        this.content = content;
    }

    /**
     * Set Piece payload index.
     */
    public void setPieceIndex(int pieceIndex){
        this.pieceIndex = pieceIndex;
    }
}
