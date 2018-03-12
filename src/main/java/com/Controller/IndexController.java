package com.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public String error() {
        return "<H1>Error handling</H1><br><H2>HANDLING...</H2>";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}