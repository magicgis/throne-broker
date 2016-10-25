package nl.kevinberendsen.throne;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Berendsen
 */
public class ThroneInterceptHandler extends AbstractInterceptHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThroneInterceptHandler.class);

    @Override
    public void onConnect(InterceptConnectMessage msg) {
        LOG.debug(String.format("onConnect: %s is trying to connect.", msg.getUsername()));
    }

    @Override
    public void onDisconnect(InterceptDisconnectMessage msg) {
        LOG.debug(String.format("onDisconnect: %s is trying to disconnect.", msg.getClientID()));
    }

    @Override
    public void onSubscribe(InterceptSubscribeMessage msg) {
        LOG.debug(String.format("onSubscribe: %s is trying to subscribe on %s with QOS %s.",
                msg.getClientID(),
                msg.getTopicFilter(),
                msg.getRequestedQos()));
    }

    @Override
    public void onUnsubscribe(InterceptUnsubscribeMessage msg) {
        LOG.debug(String.format("onUnsubscribe: %s is trying to unsubscribe on %s.",
                msg.getClientID(),
                msg.getTopicFilter()));
    }

    @Override
    public void onPublish(InterceptPublishMessage msg) {
        LOG.debug(String.format("onPublish: %s is trying to publish on %s with %s.",
                msg.getClientID(),
                msg.getTopicName(),
                new String(msg.getPayload().array())));
    }

}