package nl.kevinberendsen.throne;

import io.moquette.spi.security.IAuthorizator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nedermail on 25/07/16.
 */
public class ThroneAuthorizator implements IAuthorizator {

    private static final Logger LOG = LoggerFactory.getLogger(ThroneAuthorizator.class);

    //TODO Implement authorizator's write
    public boolean canWrite(String s, String s1, String s2) {
        LOG.debug(String.format("Checking write.. %s", s));

        return false;
    }

    //TODO Implement authorizator's read
    public boolean canRead(String s, String s1, String s2) {
        LOG.debug(String.format("Checking read.. %s", s));

        return false;
    }
}
