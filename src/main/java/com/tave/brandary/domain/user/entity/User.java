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
    private OAuthProvider oauthProvider;

    @Column(nullable = false, unique = true)
    private String oauthId;

    @Email
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20)
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Builder
    public User(OAuthProvider oauthProvider, String oauthId, String email,String nickname,
                String profileImageUrl, String thumbnailImageUrl) {
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
