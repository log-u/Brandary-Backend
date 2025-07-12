package com.tave.brandary.domain.auth.service;

import com.tave.brandary.domain.auth.client.KakaoApiClient;
import com.tave.brandary.domain.auth.dto.LoginResDto;
import com.tave.brandary.domain.auth.dto.SocialLoginReqDto;
import com.tave.brandary.domain.auth.dto.SocialUserInfo;
import com.tave.brandary.domain.user.entity.SocialProvider;
import com.tave.brandary.domain.user.entity.User;
import com.tave.brandary.domain.user.repository.UserRepository;
import com.tave.brandary.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoApiClient kakaoApiClient;
//    private final NaverApiClient naverApiClient;
//    private final GoogleApiClient googleApiClient;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResDto loginOrSignUp(SocialLoginReqDto request) {
        SocialUserInfo userInfo = switch (request.provider()) {
            case KAKAO -> kakaoApiClient.getUserInfoByCode(request.code());
//            case NAVER -> naverApiClient.getUserInfoByCode(request.getCode());
//            case GOOGLE -> googleApiClient.getUserInfoByCode(request.getCode());
            default -> throw new IllegalStateException("Unexpected value: " + request.provider());
        };

        User user = userRepository.findBySocialIdAndSocialProvider(userInfo.socialId(), request.provider())
                .orElseGet(() -> registerUser(userInfo, request.provider()));

        String jwt = jwtService.issueToken(user);
        boolean isNewUser = user.getCreatedTime().isAfter(LocalDateTime.now().minusMinutes(1));

        return new LoginResDto(jwt, isNewUser);
    }

    private User registerUser(SocialUserInfo info, SocialProvider provider) {
        User user = new User(
                provider,
                info.socialId(),
                info.email(),
                info.nickname(),
                info.profileImageUrl()
        );

        return userRepository.save(user);
    }
}