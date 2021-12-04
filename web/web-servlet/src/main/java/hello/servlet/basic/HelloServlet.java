package hello.servlet.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // 해당 서블릿이 호출되면 `service` 메서드가 호출된다
    // org.apache.catalina.core.ApplicationFilterChain.java 227 line 참고
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        log.debug("HelloServlet.service");
        log.debug("req = " + req);
        log.debug("resp = " + resp);

        // 요청 값 가져오기
        final String username = req.getParameter("username");
        log.debug("username = " + username);

        // 응답하기
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("Hello, " + username);
    }
}
