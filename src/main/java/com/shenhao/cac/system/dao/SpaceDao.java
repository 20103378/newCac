package com.shenhao.cac.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.system.entity.SpaceConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao extends BaseMapper<SpaceConfig> {
    @Select("SELECT * FROM space_config")
    IPage<SpaceConfig> findSpacePage(Page page);
}
