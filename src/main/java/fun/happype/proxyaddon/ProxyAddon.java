package fun.happype.proxyaddon;

import dev.waterdog.ProxyServer;
import dev.waterdog.event.defaults.PlayerPreLoginEvent;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;
import dev.waterdog.plugin.Plugin;
import fun.happype.proxyaddon.player.Player;
import fun.happype.proxyaddon.protocol.RequestPool;
import lombok.Getter;

@SuppressWarnings("unused")
public class ProxyAddon extends Plugin {

    @Getter
    private static ProxyAddon instance;

    @Override
    public void onEnable() {
        RequestPool.init();
        this.getProxy().getEventManager().subscribe(PlayerPreLoginEvent.class, this::onPreLogin);
    }

    public void onPreLogin(PlayerPreLoginEvent event) {
        event.setBaseClass(Player.class);
    }

    /**
     * Finds server connected to the proxy
     * @return ServerInfo or null, if the server was not found
     */
    public static ServerInfo findServer(String serverName) {
        return ProxyServer.getInstance().getServerInfo(serverName);
    }

    /**
     * Finds player connected to the proxy
     * @return ProxiedPlayer or null, if player was not found
     */
    public static ProxiedPlayer findPlayer(String playerName) {
        return ProxyServer.getInstance().getPlayer(playerName);
    }
}
