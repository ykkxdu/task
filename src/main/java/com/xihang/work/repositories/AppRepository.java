package com.xihang.work.repositories;

import com.xihang.work.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description:应用数据仓库
 */
public interface AppRepository extends JpaRepository<App,Long> {
    List<App> findByUserId(@Param("userId")Long userId);
}
