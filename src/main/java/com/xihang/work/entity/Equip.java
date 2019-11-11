package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * 装备实体
* */
@Entity
@Data
@AllArgsConstructor
public class Equip {
    @Id
    @GeneratedValue
    // 任务序号
    private Long id;
    // 装备名称
    private String equipName;
    // 装备型号
    private String equipType;
    // 装备介绍
    private String equipIntroduct;
    // 领取装备的人员
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private Equip(){

    }
}
