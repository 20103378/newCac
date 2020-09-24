package com.shenhao.cac.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thyme
 * @ClassName SystemInfoController
 * @Description TODO
 * @Date 2019/12/27 16:57
 */
@Controller
@RequestMapping("/system")
public class SystemInfoController {

    @GetMapping("/serverInfo")
    public String serverInfo(){
        return "module/system/server";
    }

    @RequestMapping("/introduce")
    public String introduce(){
        return "module/system/introduce";
    }
}
