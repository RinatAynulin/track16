package track.messenger.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.messenger.messages.Message;

import java.io.*;

/**
 * Created by Aynulin on 23.11.2016.
 */
public class SerializationProtocol implements Protocol {
    static Logger log = LoggerFactory.getLogger(SerializationProtocol.class);

    @Override
    public Message decode(byte[] bytes) throws ProtocolException {
        Message res = null;
        java.io.ObjectInputStream in;
        java.io.ByteArrayInputStream bs;

        bs = new ByteArrayInputStream(bytes);
        try {
            in = new ObjectInputStream(bs);
            res = (Message) in.readObject();
            in.close();
            bs.close();
        } catch (Exception e) {
            log.error("Couldn't decode message .\n" + e.getMessage());
        }
        return res;
    }

    @Override
    public byte[] encode(Message msg) throws ProtocolException {
        ObjectOutputStream out;
        ByteArrayOutputStream bs;

        bs = new ByteArrayOutputStream();
        try {
            out = new ObjectOutputStream(bs);
            out.writeObject(msg);
            out.close();
        } catch (IOException e) {
            log.error("Couldn't encode message " + msg + ".\n" + e.getMessage());
        }

        return bs.toByteArray();
    }
}
