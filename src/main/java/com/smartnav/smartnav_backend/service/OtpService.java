package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Otp;
import com.smartnav.smartnav_backend.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public String sendOtp(String mobile) {

        String otp = String.format("%04d",
                new Random().nextInt(10000));

        Otp otpEntity = new Otp();
        otpEntity.setMobile(mobile);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());

        otpRepository.save(otpEntity);

        System.out.println("OTP for " + mobile + " : " + otp);

        return "OTP Sent Successfully";
    }

    public boolean verifyOtp(String mobile, String otp) {

        Optional<Otp> latestOtp =
                otpRepository.findTopByMobileOrderByCreatedAtDesc(mobile);

        return latestOtp.isPresent()
                && latestOtp.get().getOtp().equals(otp);
    }
}