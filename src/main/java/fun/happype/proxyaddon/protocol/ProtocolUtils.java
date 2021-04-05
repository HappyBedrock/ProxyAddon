package fun.happype.proxyaddon.protocol;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.packet.ScriptCustomEventPacket;

import java.nio.charset.StandardCharsets;

public class ProtocolUtils {

    public static void writeMessageResponse(BedrockSession session, ProxyResponse response) {
        ScriptCustomEventPacket pk = new ScriptCustomEventPacket();
        pk.setEventName("happybedrock:message");
        pk.setData(new String(response.encode(), StandardCharsets.UTF_8));

        session.sendPacket(pk);
    }
}
