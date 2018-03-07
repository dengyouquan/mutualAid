package com.dyq.demo.Repository;

import com.dyq.demo.entity.User;
import com.dyq.demo.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
