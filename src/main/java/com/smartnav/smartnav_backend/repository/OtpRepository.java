package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByMobileOrderByCreatedAtDesc(String mobile);
}