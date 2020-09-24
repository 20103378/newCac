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
public class SiteConfig implements Serializable {

    static final long serialVersionUID = 23L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 站点
     */
    private String site;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点地址
     */
    private String siteAddr;

    /**
     * 启用状态
     */
    private Integer delFlag;

    public SiteConfig(String site, String siteName, String siteAddr) {
        this.site = site;
        this.siteName = siteName;
        this.siteAddr = siteAddr;
    }
}
