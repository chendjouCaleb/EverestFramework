package Everest.Framework.Mvc.Runner;

public class HostConfig {

    /**
     * Java program arguments
     */
    private String[] programArgs;

    /**
     * The Server port
     * The default port is 8080
     */
    private int port = 8080;

    /**
     * Server context directory
     * The default directory is /web
     */
    private String contextDirectory = "web";

    /**
     * The server http url hostPath
     * default is '/'
     */
    private String hostPath = "/";

    private String webAppMount = "/WEB-INF/classes";

    private String appBasePath = ".";

    /**
     * The directory of compiled class
     * The default is "target/classes" for maven
     */
    private String targetClassDirectory = "target/classes";

    private String internalPath = "/";

    public String[] getProgramArgs() {
        return programArgs;
    }

    public HostConfig setProgramArgs(String[] programArgs) {
        this.programArgs = programArgs;
        return this;
    }

    public int getPort() {
        return port;
    }

    public HostConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getContextDirectory() {
        return contextDirectory;
    }

    public HostConfig setContextDirectory(String contextDirectory) {
        this.contextDirectory = contextDirectory;
        return this;
    }

    public String getHostPath() {
        return hostPath;
    }

    public HostConfig setHostPath(String hostPath) {
        this.hostPath = hostPath;
        return this;
    }

    public String getTargetClassDirectory() {
        return targetClassDirectory;
    }

    public HostConfig setTargetClassDirectory(String targetClassDirectory) {
        this.targetClassDirectory = targetClassDirectory;
        return this;
    }

    public String getWebAppMount() {
        return webAppMount;
    }

    public HostConfig setWebAppMount(String webAppMount) {
        this.webAppMount = webAppMount;
        return this;
    }

    public String getInternalPath() {
        return internalPath;
    }

    public HostConfig setInternalPath(String internalPath) {
        this.internalPath = internalPath;
        return this;
    }

    public String getAppBasePath() {
        return appBasePath;
    }

    public HostConfig setAppBasePath(String appBasePath) {
        this.appBasePath = appBasePath;
        return this;
    }
}
