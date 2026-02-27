package com.ryoga.bbs.scenario.auth;

import com.ryoga.bbs.domain.model.authentication.AuthenticationService;
import com.ryoga.bbs.domain.model.authentication.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class SignOutScenario {
    private final AuthenticationService authenticationService;

    public SignOutScenario(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void signOut(String strRefreshToken){
        if(strRefreshToken != null) {
            //リフレッシュトークン検索
            RefreshToken refreshToken = authenticationService.findByRefreshToken(strRefreshToken);
            //リフレッシュトークン削除
            authenticationService.deleteRefreshToken(refreshToken.getUserId(), refreshToken.getDeviceId());
        }
    }
}
