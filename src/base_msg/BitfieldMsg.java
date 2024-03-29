package base_msg;

/**
 * Created by Mark on 2/21/2017.
 */
public class BitfieldMsg extends BaseMsg{

    // The bitfield vector.
    private boolean bitfieldVector[];
    // The size of bitfield.
    private int length;

    /**
     * Initialize a new bitfield.
     */
    public BitfieldMsg(int numOfPieces){
        super(MsgConstant.BITFIELD_MESSAGE);
        this.bitfieldVector = new boolean[numOfPieces];
        this.length = numOfPieces;

        for (int i = 0; i < this.length; i++){
            this.bitfieldVector[i] = false;
        }
    }

    /**
     * Get the bitfield payload length.
     */
    public int getLength(){
        return this.length;
    }

    /**
     * Get the bitfield vector
     */
    public boolean[] getBitfieldVector(){
        return this.bitfieldVector;
    }

    /**
     * Get the Bitfield Bit
     */
    public boolean getBitfieldBit(int index){
        return this.bitfieldVector[index];
    }

    /**
     * Set the Bitfield Bit
     */
    public void setBitfieldVector(boolean[] bitfieldVector){
        this.bitfieldVector = bitfieldVector;
    }

    /**
     * Set the bitfield bit
     */
    public void setBitfieldBit(int index, boolean value){
        this.bitfieldVector[index] = value;
    }

    /**
     * Set all the bitfield bit on
     */
    public void setBitfieldAllOn(){
        for (int i=0; i < this.length ; i++){
            bitfieldVector[i] = true;
        }
    }

    /**
     * Get the true bit number
     */
    public int getOnBitNumber(){
        int counter = 0;
        for (int i = 0; i < this.getLength(); i++){
            if (this.bitfieldVector[i] == true){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checks whether the file is downloaded completely.
     *
     * @return true, if is file download complete
     */
    public boolean isFileDownLoadComplete(){
        if (bitfieldVector == null || bitfieldVector.length == 0){
            return false;
        }

        for (int i = 0; i < this.getLength(); i++){
            if (this.bitfieldVector[i] == false){
                return false;
            }
        }
        return true;
    }

    /**
     * Print the Bitfield Vector
     */
    public void printVector(){
        System.out.println("Printing the bitvector of length " + this.getLength());
        for (int i = 0; i < this.getLength(); i++){
            System.out.print(this.bitfieldVector[i]);
        }
        System.out.println();
    }
}