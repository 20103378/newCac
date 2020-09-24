package com.shenhao.cac.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenhao.cac.common.utils.SearchUtil;
import com.shenhao.cac.system.dao.SpaceDao;
import com.shenhao.cac.system.entity.SpaceConfig;
import com.shenhao.cac.system.service.SpaceService;
import com.shenhao.cac.system.vo.ConditionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpaceServiceImpl implements SpaceService {
    private final SpaceDao spaceDao;
    @Override
    public IPage<SpaceConfig> findSpacePage(Page page) {
        return spaceDao.findSpacePage(page);
    }

    @Override
    public List<SpaceConfig> findSpaceIfExist(String spaceId, String spaceName, String objectVoltage, Integer spaceTag) {
        ConditionVo conditionSpaceIdVo  = new ConditionVo();
        conditionSpaceIdVo.setColumn("space_id");
        conditionSpaceIdVo.setValue(spaceId);
        conditionSpaceIdVo.setType("eq");

        ConditionVo conditionSpaceNameVo  = new ConditionVo();
        conditionSpaceNameVo.setColumn("space_name");
        conditionSpaceNameVo.setValue(spaceName);
        conditionSpaceNameVo.setType("eq");

        ConditionVo conditionObjectVoltageVo  = new ConditionVo();
        conditionObjectVoltageVo.setColumn("object_voltage");
        conditionObjectVoltageVo.setValue(objectVoltage);
        conditionObjectVoltageVo.setType("eq");

        ConditionVo conditionSpaceTagVo  = new ConditionVo();
        conditionSpaceTagVo.setColumn("space_tag");
        conditionSpaceTagVo.setValue(String.valueOf(spaceTag));
        conditionSpaceTagVo.setType("eq");
        ConditionVo[] conditions = new ConditionVo[]{conditionSpaceIdVo,conditionSpaceNameVo,conditionObjectVoltageVo,conditionSpaceTagVo};
        String conditionJson = JSONArray.fromObject(conditions).toString();
        QueryWrapper queryWrapper = SearchUtil.parseWhereSql(conditionJson);
        return spaceDao.selectList(queryWrapper);
    }

    @Override
    public int insert(SpaceConfig spaceConfig) {
        return spaceDao.insert(spaceConfig);
    }

    @Override
    public int updateById(SpaceConfig spaceConfig) {
        return spaceDao.updateById(spaceConfig);
    }
}
