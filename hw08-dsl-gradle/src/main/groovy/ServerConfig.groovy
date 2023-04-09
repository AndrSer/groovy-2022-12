import groovy.transform.ToString

@ToString(includeNames = true)
class ServerConfig extends ConfigProcessor {
    def name
    def description

    Connector http
    Connector https

    private List<Mappings> mappings

    List<Mappings> getMappings() {
        return mappings
    }

    void setMappings(List<Mappings> mappings) {
        this.mappings = mappings
    }

    def getName() {
        return name
    }

    void setName(name) {
        this.name = name
    }

    def getDescription() {
        return description
    }

    void setDescription(description) {
        this.description = description
    }

    def http(Closure closure) {
        http = new Connector()
        closure.setDelegate(http)
        closure.setResolveStrategy(Closure.DELEGATE_FIRST)
        closure.call()
    }

    def https(Closure closure) {
        https = new Connector()
        closure.setDelegate(https)
        closure.setResolveStrategy(Closure.DELEGATE_FIRST)
        closure.call()
    }
}
