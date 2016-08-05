package nl.kevinberendsen.throne.db;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.google.inject.Provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by nedermail on 05/08/16.
 */
public class EbeanServerProvider implements Provider<EbeanServer> {

    public EbeanServer get() {

        ServerConfig config = new ServerConfig();
        config.setName("mysql");
        // load configuration from ebean.properties

        File f = new File("config/ebean.properties");
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(f));
            config.loadFromProperties(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            config.loadFromProperties();
        } catch (IOException e) {
            e.printStackTrace();
            config.loadFromProperties();
        }

        config.setRegister(true);
        config.setDefaultServer(true);

        return EbeanServerFactory.create(config);
    }
}