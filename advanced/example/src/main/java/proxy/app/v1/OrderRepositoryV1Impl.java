package proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    @Override
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
