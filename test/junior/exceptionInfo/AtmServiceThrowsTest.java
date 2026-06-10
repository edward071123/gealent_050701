package junior.exceptionInfo;

public class AtmServiceThrowsTest {
    public static void main(String[] args) throws Exception {
        shouldWithdrawSuccessfully();
        shouldThrowExceptionToCaller();
        System.out.println("AtmServiceThrowsTest: PASS");
    }

    private static void shouldWithdrawSuccessfully() throws Exception {
        AtmService atm = new AtmService(1000);

        String message = atm.withdrawWithThrows("300");

        assertEquals("提款成功：300 元，剩餘餘額：700 元", message);
    }

    private static void shouldThrowExceptionToCaller() {
        AtmService atm = new AtmService(1000);

        try {
            atm.withdrawWithThrows("1500");
            throw new AssertionError("餘額不足時應將例外交給呼叫端");
        } catch (Exception exception) {
            assertEquals("餘額不足", exception.getMessage());
        }
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("預期：" + expected + "，實際：" + actual);
        }
    }
}
