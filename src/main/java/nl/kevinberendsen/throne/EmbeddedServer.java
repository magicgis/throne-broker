package nl.kevinberendsen.throne;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.moquette.interception.InterceptHandler;
import io.moquette.proto.messages.AbstractMessage;
import io.moquette.proto.messages.PublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.ClasspathConfig;
import io.moquette.server.config.IConfig;

/**
 * Embedding the server. Entry point of the application
 * which also starts the MQTT broker.
 * 
 * @author Kevin Berendsen
 */
public class EmbeddedServer {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedServer.class);

    /**
     * Moquette broker instance.
     */
    private Server mqttBroker = null;

    /**
     * Public constructor that creates a new instance of Server class
     * and stores it in mqttBroker.
     */
    public EmbeddedServer() {
        mqttBroker = new Server();
    }

    /**
     * Obtain the MQTT broker instance.
     * @return null if mqttBroker is not initialized and otherwise it's returning a Server object.
     */
    public Server getBroker() {
        return mqttBroker;
    }


    /**
     * Adds instance of ThroneInterceptHandler to list of InterceptHandler handlers and starts the MQTT broker with
     * ThroneAuthenticator and ThroneAuthorizator. Adds a shutdown hook to stop the broker.
     * @throws InterruptedException
     * @throws IOException
     */
    public void run() throws InterruptedException, IOException {
        // Loading moquette configuration
        final IConfig classPathConfig = new ClasspathConfig();

        List<? extends InterceptHandler> userHandlers = asList(new ThroneInterceptHandler());
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
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        EmbeddedServer server = new EmbeddedServer();
        server.run();

        Thread.sleep(2000);
        LOG.debug("Before self publish");
        PublishMessage message = new PublishMessage();
        message.setTopicName("exit");
        message.setRetainFlag(true);
        message.setQos(AbstractMessage.QOSType.EXACTLY_ONCE);
        message.setPayload(ByteBuffer.wrap("Hello World!!".getBytes()));
        server.getBroker().internalPublish(message);
        LOG.debug("After self publish");
    }
}
