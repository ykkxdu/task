package com.xihang.work.controller;

import com.xihang.work.entity.App;
import com.xihang.work.entity.AppApply;
import com.xihang.work.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * 应用数据接口
* */
@RepositoryRestController
@RequestMapping("/api/app")
public class AppController {
    @Autowired
    private AppRepository appRepository;
    /**
     * 删除待审核应用
     * */
    @RequestMapping("/deleteApp")
    public ResponseEntity updateAppState(@RequestParam(value = "id")Long id){
        App app=appRepository.getOne(id);
        app.setUser(null);
        appRepository.saveAndFlush(app);
        appRepository.delete(app);
        return ResponseEntity.ok("删除成功!");
    }

}
