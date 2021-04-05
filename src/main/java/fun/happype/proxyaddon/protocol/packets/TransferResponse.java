package fun.happype.proxyaddon.protocol.packets;

import fun.happype.proxyaddon.protocol.ProxyResponse;
import lombok.Setter;

public class TransferResponse extends ProxyResponse {

    public final static byte STATUS_SUCCESS = 0x00;
    public final static byte INVALID_PLAYER = 0x01;
    public final static byte INVALID_SERVER = 0x02;

    @Setter
    private int status = TransferResponse.STATUS_SUCCESS;

    @Override
    public void encodePayload() {
        this.getBuffer().writeLShort(this.status);
    }

    @Override
    public int getMessageId() {
        return 0;
    }
}
