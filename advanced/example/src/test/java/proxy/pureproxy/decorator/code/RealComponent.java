package proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {
    @Override
    public String operation(final String input) {
        log.info("RealComponent called");
        return "output: " + input;
    }
}
