import com.google.common.collect.ComparisonChain

class Main {

    static boolean compareStrings (String s1, String s2) {
        return ComparisonChain.start()
                .compare(s1, s2)
                .result()
                .any { Integer result ->
            result == 0 }
    }

    static void main(String[] args) {
        String s1 = "Eva01"
        String s2 = "Eva01"

        println("Result compare: ${compareStrings(s1, s2)}")
    }
}


