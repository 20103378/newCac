package com.shenhao.cac.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thyme
 * @ClassName SystemInfoController
 * @Description TODO
 * @Date 2019/12/27 16:57
 */
@Controller
@RequestMapping("/site")
public class SiteController {

    @GetMapping("/list")
    public String serverInfo(){
        return "module/site/list";
    }

    @GetMapping("/update")
    public String update(String site, String siteName, String siteAddr,String id, Model model){
        model.addAttribute("site",site);
        model.addAttribute("siteName",siteName);
        model.addAttribute("siteAddr",siteAddr);
        model.addAttribute("id",id);
        return "module/site/updateSite";
    }

    @GetMapping("/add")
    public String add(){
        return "module/site/addSite";
    }
}
