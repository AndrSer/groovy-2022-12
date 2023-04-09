import groovy.xml.MarkupBuilder
import org.codehaus.groovy.control.CompilerConfiguration

import java.nio.file.Paths

class ConfigProcessor extends GroovyObjectSupport {

    private URI scriptPath

    URI getScriptPath() {
        return scriptPath
    }

    void setScriptPath(URI scriptPath) {
        this.scriptPath = scriptPath
    }

    def include(String path) {
        URI uri = Paths.get(scriptPath).getParent().resolve(path).toUri()
        run(uri)
    }

    def run(URI uri) {
        this.scriptPath = uri

        CompilerConfiguration compiler = new CompilerConfiguration()
        compiler.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(this.class.classLoader, new Binding(), compiler)
        DelegatingScript script = (DelegatingScript)sh.parse(uri)
        script.setDelegate(this)
        script.run()
    }

    def runConfig(String path) {
        CompilerConfiguration compiler = new CompilerConfiguration()
        compiler.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(this.class.getClassLoader(), new Binding(), compiler)
        def file = new File(path)
        DelegatingScript script = (DelegatingScript)sh.parse(file)
        ServerConfig config = new ServerConfig()
        config.setScriptPath(file.toURI())
        script.setDelegate(config)

        script.run()
        return config.toString()
    }

    def methodMissing(String name, Object args) {
        if (name != "call") {
            MetaProperty metaProperty = getMetaClass().getMetaProperty(name)
            if (metaProperty != null) {
                Closure closure = (Closure) ((Object[]) args)[0]
                def value = getProperty(name) == null ?
                        metaProperty.getType().getConstructor().newInstance() :
                        getProperty(name)

                if (value instanceof ConfigProcessor) {
                    ((ConfigProcessor) value).scriptPath = scriptPath
                }
                closure.setDelegate(value)
                closure.setResolveStrategy(Closure.DELEGATE_FIRST)
                closure.call()
                setProperty(name, value)
            } else {
                throw new IllegalArgumentException("No such field: " + name)
            }
        }
    }
}
