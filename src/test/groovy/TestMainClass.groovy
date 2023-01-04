import org.junit.jupiter.api.Test

class TestMainClass {

    @Test
    void '001_assert_main_method'() {
        assert Main.performString("world") == "Hello world!"
    }
}
