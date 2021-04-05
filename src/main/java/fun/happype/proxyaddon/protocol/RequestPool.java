package fun.happype.proxyaddon.protocol;

import fun.happype.proxyaddon.protocol.packets.ServerInfoRequest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestPool {

    public static final int SERVER_INFO = 0x00;
    public static final int TRANSFER = 0x01;

    private static final Map<Integer, ProxyRequest> requestMap = new HashMap<>();

    public static void init() {
        RequestPool.register(SERVER_INFO, new ServerInfoRequest());
    }

    public static ProxyRequest decodeRequest(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

        PacketBuffer buffer = new PacketBuffer() {{
            this.setBuffer(bytes);
        }};

        int id = buffer.readUnsignedVarInt();
        ProxyRequest request = (ProxyRequest) RequestPool.requestMap.get(id).clone();
        request.setBuffer(buffer);

        request.decodePayload();

        return request;
    }

    public static void register(int id, ProxyRequest request) {
        RequestPool.requestMap.put(id, request);
    }
}
