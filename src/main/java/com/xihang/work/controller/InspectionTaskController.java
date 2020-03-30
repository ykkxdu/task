package com.xihang.work.controller;
import com.xihang.work.entity.InspectionTask;
import com.xihang.work.entity.projection.InspectionTaskAll;
import com.xihang.work.repositories.ArmyRepository;
import com.xihang.work.repositories.InspectionTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:ZhangChao
 * @Description:
 * @Date:Created in 2019/1/15
 */
@RepositoryRestController
@RequestMapping("/inspectionTasks")
public class InspectionTaskController extends FuzzySearchController<InspectionTask> {

}
