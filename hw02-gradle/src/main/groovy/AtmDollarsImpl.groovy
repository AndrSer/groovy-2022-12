class AtmDollarsImpl implements Atm {

    AtmDollarsImpl() {}

    void putSumToCashFond(CashFond cashFond, Map<Dollars, Integer> sumFromUser) {
        try {
            cashFond + sumFromUser
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    Map<Dollars, Integer> getRequestedSumFromFond(CashFond cashFond, long requestedSum) {
        def resultMap = [:] as Map
        resultMap.put(Dollars.HUNDRED, 0)
        resultMap.put(Dollars.FIFTY, 0)
        resultMap.put(Dollars.TWENTY, 0)
        resultMap.put(Dollars.TEN, 0)
        resultMap.put(Dollars.FIVE, 0)
        resultMap.put(Dollars.ONE, 0)

        if (requestedSum <= cashFond.getTotalAmount()) {
            def denominations = cashFond.getDenominations().sort(Comparator.reverseOrder())

            denominations.each {
                def countOfHundred = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.HUNDRED)))
                def countOfFifty = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.FIFTY)))
                def countOfTwenty = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.TWENTY)))
                def countOfTen = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.TEN)))
                def countOfFive = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.FIVE)))
                def countOfOne = Math.round(Math.floor(requestedSum / Dollars.getDigitOnName(Dollars.ONE)))

                def denominationsFromUser = [:] as Map
                denominationsFromUser.put(Dollars.HUNDRED, countOfHundred)
                denominationsFromUser.put(Dollars.FIFTY, countOfFifty)
                denominationsFromUser.put(Dollars.TWENTY, countOfTwenty)
                denominationsFromUser.put(Dollars.TEN, countOfTen)
                denominationsFromUser.put(Dollars.FIVE, countOfFive)
                denominationsFromUser.put(Dollars.ONE, countOfOne)

                if (denominationsFromUser.get(it.getKey()) != 0) {
                    def subtrahendMap = [:] as Map
                    subtrahendMap.put(it.getKey(), denominationsFromUser.get(it.getKey()))
                    cashFond - subtrahendMap
                    resultMap[it.getKey()] = denominationsFromUser.get(it.getKey())
                    requestedSum = requestedSum -
                            denominationsFromUser.get(it.getKey()) * Dollars.getDigitOnName(it.getKey())

                    if (requestedSum == 0) {
                        return
                    }
                } else {
                    return
                }
            }
        } else {
            throw new Exception("Requested amount cannot be withdrawn")
        }
        return resultMap
    }

    int getAccountBalance(CashFond cashFond) {
        return cashFond.getTotalAmount()
    }
}
