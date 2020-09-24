package com.shenhao.cac.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.system.entity.SiteConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author thyme
 * @ClassName SysLogDao
 * @Description TODO
 * @Date 2020/1/9 16:22
 */
@Repository
public interface SiteDao extends BaseMapper<SiteConfig> {

    @Select("SELECT * FROM site_config")
    IPage<SiteConfig> findSitePage(Page page);

}
