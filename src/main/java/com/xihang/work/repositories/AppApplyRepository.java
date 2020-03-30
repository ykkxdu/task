package com.xihang.work.repositories;

import com.xihang.work.entity.AppApply;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 待审核应用仓库
* */
public interface AppApplyRepository extends JpaRepository<AppApply,Long> {
}
