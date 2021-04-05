package fun.happype.proxyaddon.protocol.packets;

import fun.happype.proxyaddon.network.DownstreamPacketHandler;
import fun.happype.proxyaddon.protocol.RequestPool;
import fun.happype.proxyaddon.protocol.ProxyRequest;
import lombok.Getter;

public class ServerInfoRequest extends ProxyRequest {

    @Getter
    private String serverName;

    @Override
    public void decodePayload() {
        this.serverName = this.getBuffer().readString();
    }

    @Override
    public int getMessageId() {
        return RequestPool.SERVER_INFO;
    }

    @Override
    public boolean handle(DownstreamPacketHandler handler) {
        return handler.handleRequest(this);
    }
}
