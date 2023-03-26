interface Atm {
    void putSumToCashFond(CashFond cashFond, Map<Dollars, Integer> sumFromUser)
    Map<Dollars, Integer> getRequestedSumFromFond(CashFond cashFond, long requestedSum)
    int getAccountBalance(CashFond cashFond)
}