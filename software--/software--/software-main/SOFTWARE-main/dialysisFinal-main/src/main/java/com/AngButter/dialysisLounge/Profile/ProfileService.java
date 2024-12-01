package com.AngButter.dialysisLounge.Profile;

import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 현재 로그인된 사용자 가져오기
    public SiteUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByusername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    // 프로필 정보 업데이트
    public SiteUser updateUser(String email, String customerPhone, String customerAddress) {
        SiteUser currentUser = getCurrentUser();
        currentUser.setEmail(email);
        currentUser.setCustomerPhone(customerPhone);
        currentUser.setCustomerAddress(customerAddress);
        return userRepository.save(currentUser);
    }

    // 비밀번호 변경
    public void updatePassword(String newPassword) {
        SiteUser currentUser = getCurrentUser();
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(currentUser);
    }
}
