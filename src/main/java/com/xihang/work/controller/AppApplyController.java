package com.xihang.work.controller;
import com.xihang.work.entity.App;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:待审核应用的增删改查
 */
@RepositoryRestController
@RequestMapping("/appApply")
public class AppApplyController extends FuzzySearchController<App> {

}
