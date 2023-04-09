import groovy.transform.ToString

@ToString(includeNames = true, includes = ['host', 'port', 'secure'])
class Connector extends ServerConfig {
    private def host
    private def port
    private def secure

    def getHost() {
        return host
    }

    void setHost(host) {
        this.host = host
    }

    def getPort() {
        return port
    }

    void setPort(port) {
        this.port = port
    }

    def getSecure() {
        return secure
    }

    void setSecure(secure) {
        this.secure = secure
    }
}
