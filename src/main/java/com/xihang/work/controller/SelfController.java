package com.xihang.work.controller;

import com.xihang.work.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 鉴权接口
 */
@RestController
@RequestMapping("/api/self")
public class SelfController {

    @Autowired
    UserRepository repository;

    /**
     * 判断是否登录接口
     *
     * @param principal 登录凭证
     * @return
     */
    @GetMapping("")
    public ResponseEntity getSelf(Principal principal) {
        if (principal == null) { // 如未登录，响应401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 如已登录，返回登录用户信息
        return ResponseEntity.ok(repository.findByUserName(principal.getName()));
    }
}
