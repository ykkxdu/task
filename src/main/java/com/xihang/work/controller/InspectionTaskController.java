package com.xihang.work.controller;
import com.xihang.work.entity.InspectionTask;
import com.xihang.work.entity.projection.InspectionTaskAll;
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

    @Autowired
    private ProjectionFactory projectionFactory;
    @GetMapping("/fuzzy-all")
    public ResponseEntity<PagedResources> fuzzySearchWithProject(InspectionTask task, Pageable pageable){
        Page<InspectionTask> data=repository.findAll(Example.of(task,defaultExampleMatcher),pageable);
        Page<InspectionTaskAll> projectInlineData=data.map(
                inspectionTask -> projectionFactory.createProjection(InspectionTaskAll.class,inspectionTask));
        PagedResources.PageMetadata pageMetadata=new PagedResources.PageMetadata(
                data.getSize(),
                data.getNumber(),
                data.getTotalElements(),
                data.getTotalPages());
        return ResponseEntity.ok(PagedResources.wrap(projectInlineData,pageMetadata));
    }
    @GetMapping("/getEquip")
    public ResponseEntity getEquip(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity result=restTemplate.getForEntity("http://localhost:31380/api/equips",String.class,String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getVersion")
    public ResponseEntity getVersion(){
        return ResponseEntity.ok("v1");
    }

}
