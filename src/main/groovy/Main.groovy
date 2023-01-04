
class Main {

    static String performString(String who) {
        return "Hello ${who}!"
    }

    static void main(String[] args) {
        println(performString("world"))
    }
}
