class CashFond {

    private HashMap<Dollars, Integer> denominations
    private int totalAmount

    CashFond(def denominations) {
        this.denominations = denominations
    }

    def getDenominations() {
        return denominations
    }

    int getTotalAmount() {
        return totalAmount
    }

    void setDenominations(def denominations) {
        this.denominations = denominations
    }

    private void calculateTotalAmount() {
        def result = 0
        denominations.each {
            def currentEntryResult = it.getValue() * Dollars.getDigitOnName(it.getKey())
            result += currentEntryResult
        }
        totalAmount = result
    }

    def plus(Map<Dollars, Integer> sumFromUser) {
        if (sumFromUser.size() > denominations.size() && !sumFromUser.isEmpty()) {
            throw new Exception("Size is not equal")
        } else {
            sumFromUser.each {
                def newValue = it.getValue()
                def oldValue = denominations.get(it.getKey())
                denominations[it.getKey()] = oldValue + newValue
            }
            calculateTotalAmount()
        }
        return denominations
    }

    def minus(Map<Dollars, Integer> sumFromUser) {
        if (sumFromUser.size() > denominations.size() && !sumFromUser.isEmpty()) {
            throw new Exception("Size is not equal")
        } else {
            sumFromUser.each {
                def newValue = it.getValue()
                def oldValue = denominations.get(it.getKey())
                denominations[it.getKey()] = oldValue - newValue
            }
            calculateTotalAmount()
        }
        return denominations
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        CashFond cashFond = (CashFond) o

        if (denominations != cashFond.denominations) return false
        if (totalAmount != cashFond.totalAmount) return false

        return true
    }

    int hashCode() {
        int result
        result = Objects.hash(denominations)
        result = 31 * result + totalAmount
        return result
    }
}
