package proxy.pureproxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DecoratorClient {
    private final Component component;

    public void execute(final String input) {
        final String output = this.component.operation(input);
        log.info("output={}", output);
    }
}
