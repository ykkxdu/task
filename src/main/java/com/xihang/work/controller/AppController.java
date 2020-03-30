package com.xihang.work.controller;

import com.xihang.work.entity.App;
import com.xihang.work.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

/**
 * 应用数据接口
* */
@RepositoryRestController
@RequestMapping("/api/app")
public class AppController {

}
