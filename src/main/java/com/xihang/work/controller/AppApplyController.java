package com.xihang.work.controller;
import com.xihang.work.entity.App;
import com.xihang.work.entity.AppApply;
import com.xihang.work.repositories.AppApplyRepository;
import com.xihang.work.repositories.AppRepository;
import com.xihang.work.utils.GenerCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    /**
     * 查看所有待审核应用
    * */
    @RequestMapping("/getAllExaminingApp")
    public ResponseEntity getAllExaminingApp(){
        return ResponseEntity.ok(appApplyRepository.getAllExaminingApp());
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
            appRepository.save(app);
            appApplyRepository.delete(appApply);
            return ResponseEntity.ok(app);
        }else {
            return ResponseEntity.ok("审核不通过");
        }
    }
}
