package proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;
import proxy.pureproxy.decorator.code.*;

class DecoratorPatternTest {

    @Test
    void noDecorator() {
        final Component component = new RealComponent();
        final DecoratorClient client = new DecoratorClient(component);

        client.execute("Hello");
    }

    @Test
    void decorator1() {
        final Component component =
                new ComponentParenthesisDecorator(
                        new RealComponent()
                );
        final DecoratorClient client = new DecoratorClient(component);

        client.execute("Hello");
    }

    @Test
    void decorator2() {
        final Component component =
                new ComponentAsteriskDecorator(
                        new ComponentParenthesisDecorator(
                                new RealComponent()
                        )
                );
        final DecoratorClient client = new DecoratorClient(component);

        client.execute("Hello");
    }
}
