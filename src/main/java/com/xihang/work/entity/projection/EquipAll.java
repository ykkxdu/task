package com.xihang.work.entity.projection;

import com.xihang.work.entity.Equip;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all",types = Equip.class)
public interface EquipAll {
    Long getId();
    String getEquipName();
    String getEquipType();
    String getEquipIntroduct();
}
