package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 待审核应用数据
* */
@Entity
@Data
@AllArgsConstructor
public class AppApply {
    @Id
    @GeneratedValue
    // 应用id
    private Long id;
    // 类型 0-5
    private int type;
    // 应用名称
    private String appname;
    // 创建用户
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    // 应用申请时间
    private LocalDateTime applyTime;
    // 应用图标地址
    private String imgUrl;
    @Column(columnDefinition ="TEXT")
    private String introduction;
    // 审核状态,默认为0
    private int isRefused;
    public AppApply(){

    }
}
