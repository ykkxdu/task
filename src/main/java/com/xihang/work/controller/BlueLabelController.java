package com.xihang.work.controller;

import com.xihang.work.entity.BlueLabel;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:zhanchao
 * @Description:
 * @Date:Created in 2019/1/15
 */
@RepositoryRestController
@RequestMapping("/api/blueLabels")
public class BlueLabelController extends FuzzySearchController<BlueLabel> {
}
