import org.junit.jupiter.api.Test

class TestJsonParser {
    def receivedJson = """{
                            "id": 1,
                            "name": "Alex",
                            "age": 34,
                            "secretIdentity": "xtro",
                            "powers": [
                                         67
                                   ]
                            }"""

    @Test
    void '001_to_html_parse'() {
        def reference = """<div>
  <div id='employee'>
    <p>Alex</p>
    <br />
    <p>34</p>
    <br />
    <p>xtro</p>
    <br />
    <ul id='powers'>
      <li>67</li>
    </ul>
  </div>
</div>"""

        def html = JsonParser.toHtml receivedJson

        assert html == reference
    }

    @Test
    void '002_to_xml_parse'() {
        def reference = """<?xml version='1.0'?>
<employees>
  <id id='1'>
    <name>Alex</name>
    <age>34</age>
    <secretIdentity>xtro</secretIdentity>
    <powers>
      <power>67</power>
    </powers>
  </id>
</employees>"""

        def xml = JsonParser.toXml receivedJson

        assert xml == reference
    }

}
