package nl.kevinberendsen.throne;

import io.moquette.spi.security.IAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by nedermail on 25/07/16.
 */
public class ThroneAuthenticator implements IAuthenticator {

    private static final Logger LOG = LoggerFactory.getLogger(ThroneAuthenticator.class);

    //TODO Implement authenticator.
    public boolean checkValid(String s, byte[] bytes) {
        LOG.debug(String.format("Checking.. %s", s));
        return true;
    }
}
