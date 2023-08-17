package com.module.order.stockproject.repository;

import com.module.order.stockproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
