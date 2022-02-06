package proxy.pureproxy.proxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CacheProxySubject implements Subject {
    private final Subject subject;

    private final Map<String, String> cache = new HashMap<>();

    @Override
    public String operation(final String input) {
        log.info("CacheProxy called");

        if (!this.cache.containsKey(input)) {
            this.cache.put(input, this.subject.operation(input));
        }

        return this.cache.get(input);
    }
}
