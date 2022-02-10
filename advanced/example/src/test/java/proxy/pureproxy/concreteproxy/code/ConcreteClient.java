package proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteClient {
    private final ConcreteLogic logic;

    public void execute() {
        this.logic.operation();
    }
}
