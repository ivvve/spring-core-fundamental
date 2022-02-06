package proxy.pureproxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ComponentAsteriskDecorator implements Component {
    private final Component component;

    @Override
    public String operation(final String input) {
        log.info("ComponentAsteriskDecorator called");

        final String output = this.component.operation(input);
        return "***" + output + "***";
    }
}
