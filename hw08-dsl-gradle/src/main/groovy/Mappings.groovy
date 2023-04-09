import groovy.transform.ToString

@ToString(includeNames = true)
class Mappings {
    private def url
    private def active

    def getUrl() {
        return url
    }

    void setUrl(url) {
        this.url = url
    }

    def getActive() {
        return active
    }

    void setActive(active) {
        this.active = active
    }
}
