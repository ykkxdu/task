package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author:ZhaoChao
 * @Description:检查任务实体
 * @Date:Created in 2019/1/4
 */
@Entity
@Data
@AllArgsConstructor
public class InspectionTask {
    @Id
    @GeneratedValue
    // 任务序号
    private Long id;
    // 任务名字
    private String taskName;
    // 任务内容
    private String taskContent;
    // 完成时间
    private String finishTime;
    // 任务状态
    private String taskStatus;
    // 检查人
    private String taskPerson;
    // 关联蓝牙ID
    @ManyToOne(cascade = CascadeType.ALL)
    private BlueLabel blueLabel;
    // 关联的用户Id
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public InspectionTask() {
    }
}
