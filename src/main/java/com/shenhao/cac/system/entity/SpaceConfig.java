package com.shenhao.cac.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thyme
 * @ClassName SysLog
 * @Description TODO
 * @Date 2020/1/9 16:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceConfig implements Serializable {

    static final long serialVersionUID = 23L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 区域ID
     */
    private String spaceId;

    /**
     * 区域名称
     */
    private String spaceName;

    /**
     * 电压等级
     */
    private String objectVoltage;

    /**
     * 区域标签
     */
    private Integer spaceTag;

    /**
     * 启用状态
     */
    private Integer delFlag;

    public SpaceConfig(Integer id, String spaceId, String spaceName, String objectVoltage, Integer spaceTag) {
        this.id = id;
        this.spaceId = spaceId;
        this.spaceName = spaceName;
        this.objectVoltage = objectVoltage;
        this.spaceTag = spaceTag;
    }

    public SpaceConfig(String spaceId, String spaceName, String objectVoltage, Integer spaceTag) {
        this.spaceId = spaceId;
        this.spaceName = spaceName;
        this.objectVoltage = objectVoltage;
        this.spaceTag = spaceTag;
    }
}
