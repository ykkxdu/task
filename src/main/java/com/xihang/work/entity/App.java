package com.xihang.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description:应用数据实体
 */
@Entity
@Data
@AllArgsConstructor
public class App {
    @Id
    @GeneratedValue
    // 应用Id
    private Long id;
    // 应用类型
    private int  type;
    // 应用名称
    private String appname;
    // 创建时间
    private String createTime;
    // 应用标识APPID
    private String appid;
    // 应用秘钥APPKEY
    private String appkey;
    // 创建用户Id
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    // 应用图标地址
    private String imgUrl;
    //应用描述
    @Column(columnDefinition ="TEXT")
    private String introduction;

    public App() {
    }
}
