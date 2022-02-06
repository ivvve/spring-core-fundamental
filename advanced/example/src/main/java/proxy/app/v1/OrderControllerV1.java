package proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public interface OrderControllerV1 {
    @GetMapping("/request")
    String request(@RequestParam("itemId") final String itemId);

    @GetMapping("/no-log")
    String noLog();
}
