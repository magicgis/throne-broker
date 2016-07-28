package nl.kevinberendsen.throne;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.proto.messages.AbstractMessage;
import io.moquette.proto.messages.PublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.ClasspathConfig;
import io.moquette.server.config.IConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by nedermail on 24/07/16.
 */
public class EmbeddedServer {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedServer.class);

    static class PublisherListener extends AbstractInterceptHandler {

        @Override
        public void onPublish(InterceptPublishMessage msg) {
            System.out.println("Received on topic: " + msg.getTopicName() + " content: " + new String(msg.getPayload().array()));
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final IConfig classPathConfig = new ClasspathConfig();

        final Server mqttBroker = new Server();
        List<? extends InterceptHandler> userHandlers = asList(new PublisherListener());
        mqttBroker.startServer(classPathConfig,
                userHandlers,
                null, // ssl context
                new ThroneAuthenticator(),
                new ThroneAuthorizator());

        System.out.println("Broker started press [CTRL+C] to stop");
        //Bind  a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Stopping broker");
                mqttBroker.stopServer();
                System.out.println("Broker stopped");
            }
        });

        Thread.sleep(2000);
        LOG.debug("Before self publish");
        PublishMessage message = new PublishMessage();
        message.setTopicName("/exit");
        message.setRetainFlag(true);
//        message.setQos(AbstractMessage.QOSType.MOST_ONE);
//        message.setQos(AbstractMessage.QOSType.LEAST_ONE);
        message.setQos(AbstractMessage.QOSType.EXACTLY_ONCE);
        message.setPayload(ByteBuffer.wrap("Hello World!!".getBytes()));
        mqttBroker.internalPublish(message);
        LOG.debug("After self publish");
    }
}
