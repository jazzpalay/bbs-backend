package com.ryoga.bbs.scenario.auth;

import com.ryoga.bbs.domain.model.authentication.AuthenticationService;
import com.ryoga.bbs.domain.model.authentication.RefreshToken;
import com.ryoga.bbs.scenario.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenScenario {

    private final AuthenticationService authenticationService;

    public RefreshTokenScenario(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String refreshToken(String strRefreshToken) {

        if(strRefreshToken == null){
            throw new UnauthorizedException("サインインしてください。");
        }

        //リフレッシュトークン検索
         RefreshToken refreshToken = authenticationService.findByRefreshToken(strRefreshToken);

        //リフレッシュトークンが切れていたら削除
         if(refreshToken.isExpired()){
             authenticationService.deleteRefreshToken(refreshToken.getUserId(), refreshToken.getDeviceId());
             throw new UnauthorizedException("サインインしてください。");
         }

        return authenticationService.authenticate(refreshToken.getUserId().value());
    }
}
