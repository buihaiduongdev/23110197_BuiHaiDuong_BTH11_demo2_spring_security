package com.example.bth11.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bth11.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	Optional<UserInfo> findByName(String username);
}
