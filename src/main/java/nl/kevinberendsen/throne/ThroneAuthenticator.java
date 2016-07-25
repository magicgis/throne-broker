package nl.kevinberendsen.throne;

import io.moquette.spi.security.IAuthenticator;

/**
 * Created by nedermail on 25/07/16.
 */
public class ThroneAuthenticator implements IAuthenticator {

    //TODO Implement authenticator.
    public boolean checkValid(String s, byte[] bytes) {
        return true;
    }
}
