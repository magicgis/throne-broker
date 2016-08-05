package nl.kevinberendsen.throne.db;

import com.avaje.ebean.EbeanServer;
import com.google.inject.AbstractModule;

/**
 * Created by nedermail on 05/08/16.
 */
public class EbeanModule extends AbstractModule {

    protected void configure() {
        bind(EbeanServer.class).toProvider(EbeanServerProvider.class).asEagerSingleton();
    }

}
