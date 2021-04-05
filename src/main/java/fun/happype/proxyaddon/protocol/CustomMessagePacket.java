package fun.happype.proxyaddon.protocol;

import lombok.Getter;
import lombok.Setter;

abstract public class CustomMessagePacket {

    @Getter @Setter
    protected PacketBuffer buffer = new PacketBuffer();

    abstract public int getMessageId();

    public CustomMessagePacket clone() {
        try {
            return  (CustomMessagePacket) super.clone();
        }
        catch (CloneNotSupportedException ignore) {}

        return null;
    }
}
