package com.tave.brandary.global;


import com.tave.brandary.global.oauth.GoogleOauthProperties;
import com.tave.brandary.global.oauth.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginPageController {

    private final KakaoOauthProperties kakaoOauthProperties;
    private final GoogleOauthProperties googleOauthProperties;

    @GetMapping("/login")
    public String loginPage(Model model) {
        String kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code"
                + "&client_id=" + kakaoOauthProperties.getClientId()
                + "&redirect_uri=" + kakaoOauthProperties.getRedirectUri();

        String googleLoginUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + googleOauthProperties.getClientId()
                + "&redirect_uri=" + googleOauthProperties.getRedirectUri()
                + "&response_type=code"
                + "&scope=openid%20email%20profile";

        model.addAttribute("kakaoLoginUrl", kakaoLoginUrl);
        model.addAttribute("googleLoginUrl", googleLoginUrl);

        return "login";
    }
}