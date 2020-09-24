package com.shenhao.cac.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.common.utils.SearchUtil;
import com.shenhao.cac.system.dao.SiteDao;
import com.shenhao.cac.system.entity.SiteConfig;
import com.shenhao.cac.system.service.SiteService;
import com.shenhao.cac.system.vo.ConditionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysLogServiceImpl
 * @Description TODO
 * @Date 2020/1/9 16:23
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SiteServiceImpl implements SiteService {

    private final SiteDao siteDao;
    @Override
    public IPage<SiteConfig> findSitePage(Page page) {
        return siteDao.findSitePage(page);    }

    @Override
    public int updateById(SiteConfig siteConfig) {
        return siteDao.updateById(siteConfig);
    }

    @Override
    public int deleteById(String id) {
        return siteDao.deleteById(id);
    }

    @Override
    public int insert(SiteConfig siteConfig) {
        return siteDao.insert(siteConfig);
    }

    @Override
    public List<SiteConfig> findSiteIfExist(String site, String siteName, String siteAddr) {
        ConditionVo conditionSiteVo  = new ConditionVo();
        conditionSiteVo.setColumn("site");
        conditionSiteVo.setValue(site);
        conditionSiteVo.setType("eq");
        ConditionVo conditionSiteNameVo  = new ConditionVo();
        conditionSiteNameVo.setColumn("site_name");
        conditionSiteNameVo.setValue(siteName);
        conditionSiteNameVo.setType("eq");
        ConditionVo conditionSiteAddrVo  = new ConditionVo();
        conditionSiteAddrVo.setColumn("site_addr");
        conditionSiteAddrVo.setValue(siteAddr);
        conditionSiteAddrVo.setType("eq");
        ConditionVo[] conditions = new ConditionVo[]{conditionSiteVo,conditionSiteNameVo,conditionSiteAddrVo};
        String conditionJson = JSONArray.fromObject(conditions).toString();
        QueryWrapper queryWrapper = SearchUtil.parseWhereSql(conditionJson);
        return siteDao.selectList(queryWrapper);
    }
}
