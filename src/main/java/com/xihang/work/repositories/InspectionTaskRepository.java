package com.xihang.work.repositories;

import com.xihang.work.entity.InspectionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author:ZhaoChao
 * @Description:检查任务仓库
 * @Date:Created in 2019/1/4
 */
public interface InspectionTaskRepository extends JpaRepository<InspectionTask,Long> {
    List<InspectionTask> findByBlueLabelId(@Param("blueLabelId") Long blueLabelId);
}
