import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

class JsonParser {
   // def downloadUrl = ''

//    def downloadJson = {
//
//    }

    static def toHtml = { String json ->
        def mapper = new JsonSlurper().parseText(json)

        def writer = new StringWriter()
        new MarkupBuilder(writer).with {
            div {
                div(id:"employee") {
                    p(mapper["name"])
                    br()
                    p(mapper["age"])
                    br()
                    p(mapper["secretIdentity"])
                    br()
                    ul(id:"powers") {
                        mapper["powers"].each {
                            li(it)
                        }
                    }
                }
            }
        }
        return writer.toString()
    }

    static def toXml = { String json ->
        def mapper = new JsonSlurper().parseText(json)

        def writer = new StringWriter()
        new MarkupBuilder(writer).with {
            mkp.xmlDeclaration(version:'1.0')
            employees {
                id(id:mapper["id"]) {
                    name(mapper["name"])
                    age(mapper["age"])
                    secretIdentity(mapper["secretIdentity"])
                    powers {
                        mapper["powers"].each {
                            power(it)
                        }
                    }
                }
            }
        }
        return writer.toString()
    }
}