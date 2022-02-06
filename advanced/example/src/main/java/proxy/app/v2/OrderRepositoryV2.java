package proxy.app.v2;

public class OrderRepositoryV2 {
    public void save(final String itemId) {
        if ("ex".equals(itemId)) {
            throw new IllegalStateException("예외 발생!");
        }

        // TODO 저장 로직
        sleep(1_000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
