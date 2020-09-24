package com.shenhao.cac.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.system.entity.SpaceConfig;

import java.util.List;

public interface SpaceService {
    IPage<SpaceConfig> findSpacePage(Page page);

    List<SpaceConfig> findSpaceIfExist(String spaceId, String spaceName, String objectVoltage, Integer spaceTag);

    int insert(SpaceConfig spaceConfig);

    int updateById(SpaceConfig spaceConfig);
}
