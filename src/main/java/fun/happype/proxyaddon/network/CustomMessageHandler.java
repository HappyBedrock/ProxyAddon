package fun.happype.proxyaddon.network;

import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;
import fun.happype.proxyaddon.ProxyAddon;
import fun.happype.proxyaddon.protocol.CustomMessagePacket;
import fun.happype.proxyaddon.protocol.packets.ServerInfoRequest;
import fun.happype.proxyaddon.protocol.packets.ServerInfoResponse;

public class CustomMessageHandler {

    public static void handle(CustomMessagePacket request) {
        if(request instanceof ServerInfoRequest) {
            String serverName = ((ServerInfoRequest) request).getServerName();
            ServerInfo serverInfo = ProxyAddon.getInstance().getProxy().getServerInfo(serverName);
            if(serverInfo == null) {
                ServerInfoResponse response = new ServerInfoResponse();
                response.setServerName(serverName);
                response.setResponseStatus(ServerInfoResponse.RESPONSE_ERROR);
                return;
            }

            ServerInfoResponse response = new ServerInfoResponse();
            response.setServerName(serverName);
            response.setResponseStatus(ServerInfoResponse.RESPONSE_SUCCESS);
            response.setPlayerList(serverInfo.getPlayers().stream().map(ProxiedPlayer::getName).toArray(String[]::new));
        }
    }
}
