package com.AngButter.dialysisLounge.Profile;

import com.AngButter.dialysisLounge.User.SiteUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // 프로필 조회
    @GetMapping("/profile")
    public String getProfile(Model model) {
        SiteUser currentUser = profileService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "profile"; // profile.html 반환
    }

    // 프로필 업데이트
    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String email,
                                @RequestParam String customerPhone,
                                @RequestParam String customerAddress,
                                @RequestParam String detailAddress,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String confirmPassword,
                                Model model) {
        if (password != null && !password.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("user", profileService.getCurrentUser());
                model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
                return "profile";
            }
            profileService.updatePassword(password);
        }

        SiteUser updatedUser = profileService.updateUser(email, customerPhone, customerAddress, detailAddress); // 상세 주소 추가
        model.addAttribute("user", updatedUser);
        model.addAttribute("message", "프로필이 성공적으로 수정되었습니다.");
        return "profile"; // profile.html 반환
    }
}
