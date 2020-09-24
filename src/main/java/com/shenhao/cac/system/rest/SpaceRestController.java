package com.shenhao.cac.system.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.common.base.ApiResponse;
import com.shenhao.cac.system.entity.SpaceConfig;
import com.shenhao.cac.system.service.SpaceService;
import com.shenhao.cac.system.vo.SpaceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**/

/**
 * @author thyme
 * @ClassName SysLogRestController
 * @Description TODO
 * @Date 2020/1/13 10:46
 */
@RestController
@RequestMapping("/space")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpaceRestController {

    private final SpaceService spaceService;

    @GetMapping("/getSpaceList")
    public ApiResponse getSpaceList(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        IPage<SpaceConfig> spacePage = spaceService.findSpacePage(new Page(page, pageSize));
        JSONObject data = new JSONObject(8);
        data.put("total", spacePage.getTotal());
        data.put("spaceList", spacePage.getRecords());
        data.put("page", spacePage.getCurrent());
        data.put("page_size", spacePage.getSize());
        return ApiResponse.ofSuccess(data);
    }


    @GetMapping("/deleteSpace")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public ApiResponse deleteSpace(@RequestParam("id") Integer id, @RequestParam("delFlag") Integer delFlag) {
        JSONObject jsonObject = new JSONObject();
        try {
            SpaceConfig spaceConfig = new SpaceConfig();
            spaceConfig.setId(id);
            spaceConfig.setDelFlag(delFlag);
            spaceService.updateById(spaceConfig);
            jsonObject.put("code", 200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/updateSpace")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public ApiResponse updateSite(@RequestBody SpaceVO spaceVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            SpaceConfig spaceConfig = new SpaceConfig(spaceVO.getId(),spaceVO.getSpaceId(),spaceVO.getSpaceName(),spaceVO.getObjectVoltage(),spaceVO.getSpaceTag());
            spaceService.updateById(spaceConfig);
            jsonObject.put("code", 200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addSpace")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public ApiResponse addSite(@RequestBody SpaceVO spaceVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<SpaceConfig> siteConfigs = spaceService.findSpaceIfExist(spaceVO.getSpaceId(),spaceVO.getSpaceName(),spaceVO.getObjectVoltage(),spaceVO.getSpaceTag());
            if (!CollectionUtils.isEmpty(siteConfigs)) {
                jsonObject.put("code", 501);
            } else {
                SpaceConfig spaceConfig = new SpaceConfig(spaceVO.getSpaceId(),spaceVO.getSpaceName(),spaceVO.getObjectVoltage(),spaceVO.getSpaceTag());
                spaceService.insert(spaceConfig);
                jsonObject.put("code", 200);
            }

        } catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

}
