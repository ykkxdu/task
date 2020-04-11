package com.xihang.work.entity.projection;

import com.xihang.work.entity.App;
import com.xihang.work.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "all",types = App.class)
public interface AppAll {
    Long getId();
    Integer getType();
    String getAppname();
    String getAppid();
    String getAppkey();
    String getImgUrl();
    String getIntroduction();
    User getUser();

    String getcreateTime();
}
