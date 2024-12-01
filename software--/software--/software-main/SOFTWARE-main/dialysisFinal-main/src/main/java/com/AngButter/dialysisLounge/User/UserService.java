package com.AngButter.dialysisLounge.User;

import com.AngButter.dialysisLounge.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 생성 메서드 (상세 주소 추가)
    public SiteUser create(String username, String email, String password, String customerPhone, String customerAddress, String detailAddress) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCustomerPhone(customerPhone);
        user.setCustomerAddress(customerAddress);
        user.setDetailAddress(detailAddress); // 상세 주소 필드 설정
        user.setRole(UserRole.USER); // 기본 USER 권한 설정
        this.userRepository.save(user);
        return user;
    }

    // 관리자 생성 메서드
    public SiteUser createAdmin(String username, String email, String password) {
        SiteUser admin = new SiteUser();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole(UserRole.ADMIN); // ADMIN 권한 설정
        this.userRepository.save(admin);
        return admin;
    }

    // 사용자 조회 메서드 (username 기반)
    public SiteUser getUser(String username) {
        return this.userRepository.findByusername(username)
                .orElseThrow(() -> new DataNotFoundException("siteuser not found"));
    }

    // 사용자 찾기 (Optional로 반환)
    public Optional<SiteUser> findUser(String username) {
        return this.userRepository.findByusername(username);
    }
}
