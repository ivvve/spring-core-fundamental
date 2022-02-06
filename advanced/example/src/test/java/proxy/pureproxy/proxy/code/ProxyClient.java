package proxy.pureproxy.proxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProxyClient {
    private final Subject subject;

    public void execute(final String input) {
        this.subject.operation(input);
    }
}
