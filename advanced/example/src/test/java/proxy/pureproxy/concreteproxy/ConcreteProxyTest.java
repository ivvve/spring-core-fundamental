package proxy.pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;
import proxy.pureproxy.concreteproxy.code.ConcreteClient;
import proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import proxy.pureproxy.concreteproxy.code.ConcreteLogicTimeProxy;

public class ConcreteProxyTest {
    @Test
    void noProxy() {
        final ConcreteLogic logic = new ConcreteLogic();
        final ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }

    @Test
    void addProxy() {
        final ConcreteLogic logic = new ConcreteLogic();
        final ConcreteLogicTimeProxy logicProxy = new ConcreteLogicTimeProxy(logic);

        final ConcreteClient client = new ConcreteClient(logicProxy);
        client.execute();
    }
}
