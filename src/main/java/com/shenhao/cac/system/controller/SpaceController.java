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
@RequestMapping("/space")
public class SpaceController {

    @GetMapping("/list")
    public String serverInfo(){
        return "module/space/list";
    }

    @GetMapping("/update")
    public String update(String spaceId, String spaceName, String objectVoltage,String spaceTag,String id, Model model){
        model.addAttribute("spaceId",spaceId);
        model.addAttribute("spaceName",spaceName);
        model.addAttribute("objectVoltage",objectVoltage);
        model.addAttribute("spaceTag",spaceTag);
        model.addAttribute("id",id);
        return "module/space/updateSpace";
    }

    @GetMapping("/add")
    public String add(){
        return "module/space/addSpace";
    }
}
