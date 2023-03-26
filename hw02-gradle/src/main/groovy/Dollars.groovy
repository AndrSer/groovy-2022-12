enum Dollars {
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100)

    int digit

    Dollars(int digit) {
            this.digit = digit
    }

    static int getDigitOnName(Dollars banknotes) {
        return banknotes.digit
    }
}