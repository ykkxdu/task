package com.xihang.work.entity.projection;

import com.xihang.work.entity.Army;
import com.xihang.work.entity.Equip;
import com.xihang.work.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "all",types = User.class)
public interface UserAll {
    Long getId();
    String getUserName();
    String getName();
    String getSex();
    String getAge();
    String getArmyLevel();
    Army getArmy();
    List<Equip> getEquips();
}
