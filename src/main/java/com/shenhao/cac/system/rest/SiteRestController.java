package com.shenhao.cac.system.rest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.common.base.ApiResponse;
import com.shenhao.cac.system.entity.SiteConfig;
import com.shenhao.cac.system.service.SiteService;
import com.shenhao.cac.system.vo.SiteVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author thyme
 * @ClassName SysLogRestController
 * @Description TODO
 * @Date 2020/1/13 10:46
 */
@RestController
@RequestMapping("/site")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SiteRestController {

    private final SiteService siteService;

    @GetMapping("/getSiteList")
    public ApiResponse getMenulist(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize){
        IPage<SiteConfig> sitePage = siteService.findSitePage(new Page(page, pageSize));
        JSONObject data = new JSONObject(8);
        data.put("total",sitePage.getTotal());
        data.put("siteList",sitePage.getRecords());
        data.put("page",sitePage.getCurrent());
        data.put("page_size",sitePage.getSize());
        return ApiResponse.ofSuccess(data);
    }


    @GetMapping("/deleteSite")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteSite(@RequestParam("id")Integer id,@RequestParam("delFlag")Integer delFlag){
        JSONObject jsonObject = new JSONObject();
        try{
            SiteConfig siteConfig = new SiteConfig();
            siteConfig.setId(id);
            siteConfig.setDelFlag(delFlag);
            siteService.updateById(siteConfig);
            jsonObject.put("code",200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/updateSite")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateSite(@RequestBody SiteVO siteVO){
        JSONObject jsonObject = new JSONObject();
        try{
            SiteConfig siteConfig = new SiteConfig(siteVO.getId(),siteVO.getSite(),siteVO.getSiteName(),siteVO.getSiteAddr(),0);
            siteService.updateById(siteConfig);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addSite")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addSite(@RequestBody SiteVO siteVO){
        JSONObject jsonObject = new JSONObject();
        try{
                List<SiteConfig> siteConfigs = siteService.findSiteIfExist(siteVO.getSite(),siteVO.getSiteName(),siteVO.getSiteAddr());
                if(!CollectionUtils.isEmpty(siteConfigs)){
                    jsonObject.put("code",501);
                }else {
                    SiteConfig siteConfig = new SiteConfig(siteVO.getSite(),siteVO.getSiteName(),siteVO.getSiteAddr());
                    siteService.insert(siteConfig);
                    jsonObject.put("code",200);
                }

        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

}
