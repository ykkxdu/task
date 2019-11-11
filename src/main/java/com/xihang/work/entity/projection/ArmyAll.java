package com.xihang.work.entity.projection;

import com.xihang.work.entity.Army;
import com.xihang.work.entity.Equip;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "all",types = Army.class)
public interface ArmyAll {
    Long getId();
    String getArmyName();
    String getArmyType();
    String getArmyIntroduct();
    List<Equip> getEquips();
}
