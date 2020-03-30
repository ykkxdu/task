package com.xihang.work.entity.projection;

import com.xihang.work.entity.User;
import org.springframework.data.rest.core.config.Projection;
import java.time.LocalDateTime;

@Projection(name = "all",types = User.class)
public interface UserAll {
    Long getId();
    String getUserName();
    String getPhone();
    LocalDateTime getUserRegistered();
    String getRole();
}
