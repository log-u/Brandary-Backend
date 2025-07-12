package com.tave.brandary.domain.auth.client;

import com.tave.brandary.domain.auth.dto.SocialUserInfo;

public interface SocialApiClient {
    SocialUserInfo getUserInfoByCode(String code);
}
