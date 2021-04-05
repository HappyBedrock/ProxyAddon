package fun.happype.proxyaddon.protocol;

/**
 * Classes whose extends ProxyResponse represents response from proxy
 *  -> Proxy is using this class to RESPONSE data to the server
 */
abstract public class ProxyResponse extends CustomMessagePacket {

    final public byte[] encode() {
        this.encodeHeader();
        this.encodePayload();

        return this.getBuffer().getBuffer();
    }

    final public void encodeHeader() {
        this.getBuffer().writeUnsignedVarInt(this.getMessageId());
    }

    abstract public void encodePayload();
}