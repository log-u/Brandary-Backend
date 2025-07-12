package com.tave.brandary.domain.user.repository;

import com.tave.brandary.domain.user.entity.SocialProvider;
import com.tave.brandary.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndSocialProvider(String socialId, SocialProvider provider);
}
