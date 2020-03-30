package com.xihang.work.repositories;


import com.xihang.work.entity.AppApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 待审核应用仓库
* */
public interface AppApplyRepository extends JpaRepository<AppApply,Long> {
    // 根据用户id查找待审核任务
    List<AppApply> findByUserId(@Param("userId")Long userId);
    //  查看所有待审核任务
    @Query(value = "select * from app_apply where is_refused=0",nativeQuery = true)
    List<AppApply> getAllExaminingApp();

}
