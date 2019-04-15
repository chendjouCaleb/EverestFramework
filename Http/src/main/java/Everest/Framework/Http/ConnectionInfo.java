package Everest.Framework.Http;

/**
 * @author Chendjou deGrace
 * @version 1
 * @since 10/04/2019
 */
public abstract class ConnectionInfo {
    public abstract String id();
    public abstract String remoteIpAddress();
    public abstract String localIPAddress();
    public abstract int remotePort();
    public abstract int localPort();
}
