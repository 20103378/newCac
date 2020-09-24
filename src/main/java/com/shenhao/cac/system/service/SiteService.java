package com.shenhao.cac.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.system.entity.SiteConfig;

import java.util.List;

/**
 * @author thyme
 * @ClassName SysLogService
 * @Description TODO
 * @Date 2020/1/9 16:23
 */
public interface SiteService {
    IPage<SiteConfig> findSitePage(Page page);

    int updateById(SiteConfig siteConfig);

    int deleteById(String id);

    int insert(SiteConfig siteConfig);

    List<SiteConfig> findSiteIfExist(String site, String siteName, String siteAddr);
}
