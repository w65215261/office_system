package com.pmcc.soft.week.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by asus on 2015/10/23.
 */

@Controller
@RequestMapping("projectDetail")
public class projectDetailController {
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        return "project/projectDetail";
    }
}
