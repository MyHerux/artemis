package com.xu.artemis.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Environment env;

    @Value("${apollo.foo}")
    private String foo;

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public  String  welcome(){
        return "welcome";
    }

    @GetMapping(value = "/config/{configName:.*}")
    @ResponseBody
    public String queryConfig(@PathVariable String configName) {
        return env.getProperty(configName, "undefined");
    }
}
