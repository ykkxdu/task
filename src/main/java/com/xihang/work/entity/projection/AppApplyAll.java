package com.xihang.work.entity.projection;

import com.xihang.work.entity.AppApply;
import com.xihang.work.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.List;

@Projection(name = "all",types = AppApply.class)
public interface AppApplyAll {
    Long getId();
    Integer getType();
    String getAppname();
    User getUser();
    String getArmyIntroduct();
    String getApplyTime();
    String getIntroduction();
    Integer getIsRefused();
}
