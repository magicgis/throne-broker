package nl.kevinberendsen.throne;

import io.moquette.spi.security.IAuthorizator;

/**
 * Created by nedermail on 25/07/16.
 */
public class ThroneAuthorizator implements IAuthorizator {

    //TODO Implement authorizator's write
    public boolean canWrite(String s, String s1, String s2) {
        return false;
    }

    //TODO Implement authorizator's write
    public boolean canRead(String s, String s1, String s2) {
        return false;
    }
}
