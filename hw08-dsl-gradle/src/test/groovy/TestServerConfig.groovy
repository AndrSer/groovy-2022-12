import org.junit.jupiter.api.Test

class TestServerConfig {
    @Test
    void '001_get_base_config'() {
        ConfigProcessor processor = new ConfigProcessor()
        def result = processor
                .runConfig("src/main/groovy/server_config.dsl")
        println result
        assert result.contains("Base Server")
    }

    @Test
    void '002_get_test_config'() {
        ConfigProcessor processor = new ConfigProcessor()
        def result = processor
                .runConfig("src/main/groovy/test_server_config.dsl")
        println result
        assert result.contains("Test Server") &&
                result.contains("port:8080") &&
                result.contains("port:8081")
    }

    @Test
    void '003_get_prod_config'() {
        ConfigProcessor processor = new ConfigProcessor()
        def result = processor
                .runConfig("src/main/groovy/prod_server_config.dsl")
        println result
        assert result.contains("Production Server") &&
                result.contains("port:8084") &&
                result.contains("port:8085")
    }

    @Test
    void '004_get_develop_config'() {
        ConfigProcessor processor = new ConfigProcessor()
        def result = processor
                .runConfig("src/main/groovy/develop_server_config.dsl")
        println result
        assert result.contains("Develop Server") &&
                result.contains("port:8082") &&
                result.contains("port:8083")
    }

}
