package nl.kevinberendsen.throne;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;

/**
 * Created by nedermail on 29/07/16.
 */
public class ThroneInterceptHandler extends AbstractInterceptHandler {

    @Override
    public void onPublish(InterceptPublishMessage msg) {
        System.out.println("Received on topic: " + msg.getTopicName() + " content: " + new String(msg.getPayload().array()));
    }

}