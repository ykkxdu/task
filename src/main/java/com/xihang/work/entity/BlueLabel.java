package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author:ZhaoChao
 * @Description:蓝牙实体类
 * @Date:Created in 2019/1/11
 */
@Entity
@Data
@AllArgsConstructor
public class BlueLabel {
    @Id
    @GeneratedValue
    private Long id;
    // 蓝牙标签
    private String blueToothId;
    // 蓝牙名字
    private String blueToothName;
    public BlueLabel(){}
}
