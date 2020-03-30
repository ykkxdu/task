package com.xihang.work.repositories;

import com.xihang.work.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:应用数据仓库
 */
public interface AppRepository extends JpaRepository<App,Long> {
}
