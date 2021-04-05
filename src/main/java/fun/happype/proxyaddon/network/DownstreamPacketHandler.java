package fun.happype.proxyaddon.network;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.packet.ScriptCustomEventPacket;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;
import dev.waterdog.utils.types.PacketHandler;
import fun.happype.proxyaddon.ProxyAddon;
import fun.happype.proxyaddon.protocol.ProtocolUtils;
import fun.happype.proxyaddon.protocol.RequestPool;
import fun.happype.proxyaddon.protocol.packets.ServerInfoRequest;
import fun.happype.proxyaddon.protocol.packets.ServerInfoResponse;
import fun.happype.proxyaddon.protocol.packets.TransferRequest;
import fun.happype.proxyaddon.protocol.packets.TransferResponse;

public class DownstreamPacketHandler extends PacketHandler {

    public DownstreamPacketHandler(BedrockSession session) {
        super(session);
    }

    @Override
    public boolean handle(ScriptCustomEventPacket packet) {
        if(!packet.getEventName().equals("happybedrock:message")) {
            return false;
        }

        return RequestPool.decodeRequest(packet.getData()).handle(this);
    }

    public boolean handleRequest(ServerInfoRequest request) {
        ServerInfoResponse response = new ServerInfoResponse();
        response.setServerName(request.getServerName());

        ServerInfo server = ProxyAddon.findServer(request.getServerName());
        if(server == null) {
            response.setResponseStatus(ServerInfoResponse.RESPONSE_ERROR);

            ProtocolUtils.writeMessageResponse(this.getSession(), response);
            return true;
        }

        response.setResponseStatus(ServerInfoResponse.RESPONSE_SUCCESS);
        response.setPlayerList((String[]) server.getPlayers().stream().map(ProxiedPlayer::getName).toArray());

        ProtocolUtils.writeMessageResponse(this.getSession(), response);
        return true;
    }

    public boolean handleRequest(TransferRequest request) {
        TransferResponse response = new TransferResponse();

        ServerInfo from = ProxyAddon.findServer(request.getCurrentServer());
        ServerInfo to = ProxyAddon.findServer(request.getTargetServer());
        ProxiedPlayer player = ProxyAddon.findPlayer(request.getPlayerName());
        if(from == null || to == null) {
            response.setStatus(TransferResponse.INVALID_SERVER);

            ProtocolUtils.writeMessageResponse(this.getSession(), response);
            return true;
        }
        if(player == null || !player.isConnected()) {
            response.setStatus(TransferResponse.INVALID_PLAYER);

            ProtocolUtils.writeMessageResponse(this.getSession(), response);
            return true;
        }

        player.connect(to);

        ProtocolUtils.writeMessageResponse(this.getSession(), response);
        return true;
    }
}
