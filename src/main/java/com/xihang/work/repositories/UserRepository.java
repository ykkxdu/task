package com.xihang.work.repositories;

import com.xihang.work.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:ZhaoChao
 * @Description:用户仓库
 * @Date:Created in 2019/1/4
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(String username);
}
