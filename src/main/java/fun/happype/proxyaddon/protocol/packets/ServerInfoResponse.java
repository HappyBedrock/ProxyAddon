package fun.happype.proxyaddon.protocol.packets;

import fun.happype.proxyaddon.protocol.RequestPool;
import fun.happype.proxyaddon.protocol.ProxyResponse;
import lombok.Setter;

public class ServerInfoResponse extends ProxyResponse {

    public static final boolean RESPONSE_SUCCESS = true;
    public static final boolean RESPONSE_ERROR = false;

    @Setter
    private String serverName;
    @Setter
    private boolean responseStatus = ServerInfoResponse.RESPONSE_SUCCESS;

    @Setter
    private String[] playerList;

    @Override
    public void encodePayload() {
        this.getBuffer().writeString(this.serverName);
        this.getBuffer().writeBoolean(this.responseStatus);

        if(this.responseStatus == ServerInfoResponse.RESPONSE_SUCCESS) {
            this.getBuffer().writeLInt(this.playerList.length);
            for(String player : this.playerList) {
                this.getBuffer().writeString(player);
            }
        }
    }

    @Override
    public int getMessageId() {
        return RequestPool.SERVER_INFO;
    }
}
