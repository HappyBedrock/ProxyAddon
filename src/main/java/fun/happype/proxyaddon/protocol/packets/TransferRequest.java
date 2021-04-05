package fun.happype.proxyaddon.protocol.packets;

import fun.happype.proxyaddon.network.DownstreamPacketHandler;
import fun.happype.proxyaddon.protocol.RequestPool;
import fun.happype.proxyaddon.protocol.ProxyRequest;
import lombok.Getter;

public class TransferRequest extends ProxyRequest {

    @Getter
    private String playerName;
    @Getter
    private String currentServer;
    @Getter
    private String targetServer;

    @Override
    public void decodePayload() {
        this.playerName = this.getBuffer().readString();
        this.currentServer = this.getBuffer().readString();
        this.targetServer = this.getBuffer().readString();
    }

    @Override
    public int getMessageId() {
        return RequestPool.TRANSFER;
    }

    @Override
    public boolean handle(DownstreamPacketHandler handler) {
        return handler.handleRequest(this);
    }
}
