package fun.happype.proxyaddon.player;

import com.nukkitx.protocol.bedrock.BedrockServerSession;
import dev.waterdog.ProxyServer;
import dev.waterdog.network.session.LoginData;
import dev.waterdog.player.ProxiedPlayer;
import fun.happype.proxyaddon.network.DownstreamPacketHandler;

public class Player extends ProxiedPlayer {

    @SuppressWarnings("unused")
    public Player(ProxyServer proxy, BedrockServerSession session, LoginData loginData) {
        super(proxy, session, loginData);

        // Maybe I overlooked something, but there should be better way to do this
        this.setPluginDownstreamHandler(new DownstreamPacketHandler(session));
    }
}
