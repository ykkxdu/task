package com.xihang.work.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author:ZhaoChao
 * @Description:用户实体
 * @Date:Created in 2019/1/4
 */
@Entity
@Data
@AllArgsConstructor
public class User {
    // 用户Id
    @Id
    @GeneratedValue
    private Long id;
    // 用户名
    @Column(unique = true)
    private String userName;
    // 姓名
    private String name;
    // 性别
    private String sex;
    // 年龄
    private Integer age;
    // 军衔
    private String armyLevel;
    // 所属军队
    @OneToOne
    private Army army;
    @OneToMany
    private List<Equip> equips;
    // 密码
    // 此字段不加入 json 序列化（避免密码泄露不安全）
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 角色
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    public User(){
    }
}
