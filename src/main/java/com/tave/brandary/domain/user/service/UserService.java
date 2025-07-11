package com.tave.brandary.domain.user.service;

import com.tave.brandary.domain.user.dto.UserResDto;
import com.tave.brandary.domain.user.dto.UserSignUpReqDto;
import com.tave.brandary.domain.user.entity.OAuthProvider;
import com.tave.brandary.domain.user.entity.User;
import com.tave.brandary.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 이메일을 새로 받으려면 이메일 인증 구현 필요
    public UserResDto signUp(UserSignUpReqDto dto) {
        User user = User.builder()
                .oauthProvider(OAuthProvider.KAKAO)
                .oauthId(dto.oauthId())
                .email(dto.email())
                .nickname(dto.nickname())
                .profileImageUrl(dto.profileImageUrl())
                .thumbnailImageUrl(dto.thumbnailImageUrl())
                .build();

        userRepository.save(user);
        return new UserResDto(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileImageUrl(),
                user.getThumbnailImageUrl());
    }

    public Optional<User> findByOauthId(String oauthId) {
        return userRepository.findByOauthId(oauthId);
    }
}