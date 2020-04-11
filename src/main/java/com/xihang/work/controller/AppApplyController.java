package com.xihang.work.controller;
import com.xihang.work.entity.App;
import com.xihang.work.entity.AppApply;
import com.xihang.work.entity.User;
import com.xihang.work.repositories.AppApplyRepository;
import com.xihang.work.repositories.AppRepository;
import com.xihang.work.repositories.UserRepository;
import com.xihang.work.utils.GenerCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * @Description:待审核应用的增删改查
 */
@RepositoryRestController
@RequestMapping("/api/appApply")
public class AppApplyController extends FuzzySearchController<App> {
    @Autowired
    private AppApplyRepository appApplyRepository;
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * 查看所有待审核应用
    * */
    @RequestMapping("/getAllExaminingApp")
    public ResponseEntity getAllExaminingApp(Principal principal){
        if(principal==null){
            return ResponseEntity.ok("未登录");
        }
        User user=userRepository.findByUserName(principal.getName());
        if(user.getRole().equals("ADMIN")) {
            return ResponseEntity.ok(appApplyRepository.getAllExaminingApp());
        }else {
            return ResponseEntity.ok("不是系统管理员");
        }
    }

    /**
     * 应用申请的通过与拒绝
    * */
    @RequestMapping("/updateAppState")
    public ResponseEntity updateAppState(@RequestParam(value = "isRefuse")int isRefuse,
                                         @RequestParam(value = "id")Long id){
        AppApply appApply=appApplyRepository.getOne(id);
        if(appApply==null){
            return ResponseEntity.ok("应用不存在");
        }else if (isRefuse==1){
            appApply.setIsRefused(isRefuse);
            App app=new App();
            app.setAppname(appApply.getAppname());
            app.setImgUrl(appApply.getImgUrl());
            app.setCreateTime(appApply.getApplyTime());
            app.setIntroduction(appApply.getIntroduction());
            app.setAppkey(GenerCode.toSerialCode(id));
            app.setAppid("000000"+id);
            app.setUser(appApply.getUser());
            appRepository.save(app);
            appApply.setUser(null);
            appApplyRepository.saveAndFlush(appApply);
            appApplyRepository.delete(appApply);
            return ResponseEntity.ok(app);
        }else {
            return ResponseEntity.ok("审核不通过");
        }
    }
    /**
     * 删除待审核应用
    * */
    @RequestMapping("/deleteAppApply")
    public ResponseEntity updateAppState(@RequestParam(value = "id")Long id){
        AppApply appApply=appApplyRepository.getOne(id);
        appApply.setUser(null);
        appApplyRepository.saveAndFlush(appApply);
        appApplyRepository.delete(appApply);
        return ResponseEntity.ok("删除成功!");
    }

}
