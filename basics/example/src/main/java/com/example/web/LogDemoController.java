package com.example.web;

import com.example.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
        private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @GetMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

//        MyLogger myLogger = this.myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");
        return "OK";
    }
}
