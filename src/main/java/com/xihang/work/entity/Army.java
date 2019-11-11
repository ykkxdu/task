package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 军队的实体
* */
@Entity
@Data
@AllArgsConstructor
public class Army {
    @Id
    @GeneratedValue
    // 军队序号
    private Long id;
    // 军队名称
    private String armyName;
    // 军队类型a
    private String armyType;
    // 军队介绍
    private String armyIntroduct;
    //军队下对应的装备
    @ManyToMany
    private List<Equip> equips;
    public Army(){

    }
}
