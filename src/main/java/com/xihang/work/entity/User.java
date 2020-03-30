package com.xihang.work.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:用户实体
 */
@Entity
@Data
@AllArgsConstructor()
public class User {
    // 用户Id
    @Id
    @GeneratedValue
    private Long id;
    // 用户名
    @Column(unique = true)
    private String userName;
    // 电话
    private String phone;
    // 注册时间
    private LocalDateTime userRegistered;
    // 密码
    // 此字段不加入 json 序列化（避免密码泄露不安全）
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 角色,ADMIN 和USER
    private String  role;
    public User(){
    }
}
