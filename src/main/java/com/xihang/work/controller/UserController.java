package com.xihang.work.controller;

import com.xihang.work.entity.ChangePasswordForm;
import com.xihang.work.entity.User;
import com.xihang.work.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang.StringUtils;
import java.security.Principal;
import java.util.Objects;

/**
 * @Author:ZhaoChao
 * @Description:用户接口
 * @Date:Created in 2019/1/8
 */
@RepositoryRestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 创建新用户接口
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("/registry")
    public ResponseEntity save(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(repository.save(user));
    }
    /**
     * 更改用户密码接口
     *
     * @param principal 登录凭证
     * @param form      表单信息
     * @return
     */
    @PostMapping("/change_password")
    public ResponseEntity changePassword(
            Principal principal,
            @RequestBody ChangePasswordForm form
    ) {
        User existed = repository.findByUserName(principal.getName());
        // 旧密码是否正确
        Boolean isRightPassword = bCryptPasswordEncoder.matches(
                form.getOldPassword(),
                existed.getPassword()
        );
        // 新密码和确认新密码是否相同
        Boolean isNewPasswordSame = Objects.equals(
                form.getNewPassword(),
                form.getConfirmPassword()
        );
        // 新密码是否为空
        Boolean isNewPasswordNotEmpty = StringUtils.isNotEmpty(form.getNewPassword());
        if (isRightPassword && isNewPasswordSame && isNewPasswordNotEmpty) {
            // 设置新密码（需再加密）
            existed.setPassword(bCryptPasswordEncoder.encode(form.getNewPassword()));
            repository.save(existed);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
