import org.junit.jupiter.api.Test

class TestGenerateFile {

    def PATH_TO_FILE = 'build/src-gen/org.example.result/CodeGenClass.groovy'
    def TEXT = """class CodeGenClass {
                                             def propertyName = realValue                                        
                                        }"""

    @Test
    void 'test_001_should_generate_file_in_package'() {
        def file = new File(PATH_TO_FILE)
        assert file.exists() && file.isFile()
        assert file.getText() == TEXT
    }
}
