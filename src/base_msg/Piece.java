package base_msg;

import java.io.Serializable;

/**
 * Created by Mark on 2/20/2017.
 */
public class Piece implements Serializable {
    private byte[] data;
    int size;

    public Piece(int size){
        this.size = size;
    }

    public int getSize(){
        if (this.data == null){
            return -1;
        }
        else{
            return this.data.length;
        }
    }

    public byte[] getData(){
        return data;
    }

    public void setData(byte[] data){
        this.data = data;
    }

}
