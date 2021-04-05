package fun.happype.proxyaddon.protocol;

import fun.happype.proxyaddon.network.DownstreamPacketHandler;

/**
 * Classes whose extends ProxyRequest represents requests for proxy
 *  -> Server is using this class to REQUEST data from the proxy
 */
abstract public class ProxyRequest extends CustomMessagePacket {

    abstract public void decodePayload();

    abstract public boolean handle(DownstreamPacketHandler handler);
}