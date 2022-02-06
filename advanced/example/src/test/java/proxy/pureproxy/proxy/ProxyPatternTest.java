package proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import proxy.pureproxy.proxy.code.CacheProxySubject;
import proxy.pureproxy.proxy.code.ProxyClient;
import proxy.pureproxy.proxy.code.RealSubject;
import proxy.pureproxy.proxy.code.Subject;

class ProxyPatternTest {
    @Test
    void noProxyTest() {
        final Subject subject = new RealSubject();
        final ProxyClient client = new ProxyClient(subject);

        client.execute("Proxy");
        client.execute("Proxy");
        client.execute("Proxy");
    }

    @Test
    void cacheProxyTest() {
        final Subject subject = new CacheProxySubject(new RealSubject());
        final ProxyClient client = new ProxyClient(subject);

        client.execute("Proxy");
        client.execute("Proxy");
        client.execute("Proxy");
    }
}
