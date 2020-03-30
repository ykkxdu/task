package com.xihang.work.entity.projection;

import com.xihang.work.entity.AppApply;
import com.xihang.work.entity.App;
import com.xihang.work.entity.User;
import org.springframework.data.rest.core.config.Projection;

/**
 * @Author:Yankaikai
 * @Description:为Web查询提供投影接口
 * @Date:Created in 2019/1/11
 */
@Projection(name = "all",types = App.class)
public interface InspectionTaskAll {
    Long getId();
    String getTaskName();
    String getTaskContent();
    String getFinishTime();
    String getTaskStatus();
    String getTaskPerson();
    User getUser();
    AppApply getArmy();
}
