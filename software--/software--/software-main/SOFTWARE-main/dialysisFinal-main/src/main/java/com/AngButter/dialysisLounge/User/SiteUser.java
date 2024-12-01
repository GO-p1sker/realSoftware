package com.AngButter.dialysisLounge.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String customerPhone;

    // 기존 주소 필드
    @Column(unique = true)
    private String customerAddress;

    // 상세 주소 필드 추가
    @Column(unique = true)
    private String detailAddress; // 상세 주소를 저장하는 필드

    // UserRole 필드
    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장
    private UserRole role = UserRole.USER; // 기본값을 USER로 설정
}
