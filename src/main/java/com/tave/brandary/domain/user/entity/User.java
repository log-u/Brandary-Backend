package com.tave.brandary.domain.user.entity;

import com.tave.brandary.global.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider socialProvider;

    @Column(nullable = false, unique = true)
    private String socialId;

    @Email
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20)
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Builder
    public User(SocialProvider socialProvider, String socialId, String email,
                String nickname, String profileImageUrl) {
        this.socialProvider = socialProvider;
        this.socialId = socialId;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
