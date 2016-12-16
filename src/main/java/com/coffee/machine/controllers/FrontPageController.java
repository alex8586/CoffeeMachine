package com.coffee.machine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontPageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView frontpage(){
        return new ModelAndView("index");
    }
}
