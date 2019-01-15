package com.xihang.work.repositories;
import com.xihang.work.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ZhaoChao
 * @Description:用户仓库初始化
 * @Date:Created in 2018/11/2
 */
@Service
public class RepositoriesInitializer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Object lock = new Object();

    private boolean initialized = false;

    public void initialize() {
        if (!initialized) {
            synchronized (lock) {
                if (!initialized) {
                    initializeAll();
                    initialized = true;
                }
            }
        }
    }

    /**
     * 初始化全部表
     */
    private void initializeAll() {
        initializeUsers();
    }

    /**
     * 初始化管理员登录用户名和密码
     */
    private void initializeUsers() {
        if (userRepository.count() > 0) return;
        User user=new User();
        user.setUserName("admin");
        user.setPassword(bCryptPasswordEncoder.encode("pass"));
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        userRepository.save(user);
    }
}
