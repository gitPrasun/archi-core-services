package com.prasun.archi.ext.core.pojo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.utility.pojo.models.User;

public interface UserRepository extends JpaRepository<User, String> {

}
