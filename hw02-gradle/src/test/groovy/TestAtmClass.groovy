import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

class TestAtmClass {

    private def denominations = Map.of(
            Dollars.HUNDRED, 0,
            Dollars.FIFTY, 0,
            Dollars.TWENTY, 0, Dollars.TEN, 0, Dollars.FIVE, 0, Dollars.ONE, 0)

    CashFond cashFond = new CashFond(denominations)
    Atm atm = new AtmDollarsImpl()

    @Test
    void '001 Test put some to cash fond'() {
        def sumFromUser = Map.of(
                Dollars.HUNDRED, 2,
                Dollars.FIFTY, 1,
                Dollars.TWENTY, 0,
                Dollars.TEN, 0,
                Dollars.FIVE, 0,
                Dollars.ONE, 4
        )

        atm.putSumToCashFond(cashFond, sumFromUser)
        assert cashFond.getTotalAmount() == 254
        cashFond.getDenominations().each {
            assert it.getValue() == sumFromUser.get(it.getKey())
        }
    }

    @Test
    void '002 Test get account balance'() {
        def sumFromUser = Map.of(
                Dollars.HUNDRED, 10,
                Dollars.FIFTY, 1,
                Dollars.TWENTY, 0,
                Dollars.TEN, 0,
                Dollars.FIVE, 0,
                Dollars.ONE, 4
        )

        atm.putSumToCashFond(cashFond, sumFromUser)
        assert atm.getAccountBalance(cashFond) == 1054

        sumFromUser = Map.of(
                Dollars.HUNDRED, 20,
                Dollars.FIFTY, 1,
                Dollars.TWENTY, 0,
                Dollars.TEN, 0,
                Dollars.FIVE, 0,
                Dollars.ONE, 4
        )

        atm.putSumToCashFond(cashFond, sumFromUser)
        assert atm.getAccountBalance(cashFond) == 3108
    }


    @Test
    void '003 get requested sum from fond'() {
        def sumFromUser = Map.of(
                Dollars.HUNDRED, 35,
                Dollars.FIFTY, 1,
                Dollars.TWENTY, 0,
                Dollars.TEN, 0,
                Dollars.FIVE, 0,
                Dollars.ONE, 4
        )

        atm.putSumToCashFond(cashFond, sumFromUser)
        assert atm.getAccountBalance(cashFond) == 3554

        atm.getRequestedSumFromFond(cashFond, 247)
        assert atm.getAccountBalance(cashFond) == 3307

        Exception exception = assertThrows(Exception.class, () -> {
            atm.getRequestedSumFromFond(cashFond, 3308)
        })

        String expectedMessage = "Requested amount cannot be withdrawn"
        String actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)
    }
}
