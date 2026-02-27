package com.ryoga.bbs.controller.api.user;

import com.ryoga.bbs.controller.api.user.form.SignInForm;
import com.ryoga.bbs.controller.api.user.form.SignUpForm;
import com.ryoga.bbs.controller.api.user.response.RefreshTokenResponse;
import com.ryoga.bbs.controller.api.user.response.SignInResponse;
import com.ryoga.bbs.scenario.auth.RefreshTokenScenario;
import com.ryoga.bbs.scenario.auth.SignInScenario;
import com.ryoga.bbs.scenario.auth.SignOutScenario;
import com.ryoga.bbs.scenario.auth.SignUpScenario;
import com.ryoga.bbs.scenario.auth.command.SignInCommand;
import com.ryoga.bbs.scenario.auth.command.SignUpCommand;
import com.ryoga.bbs.scenario.exception.DuplicateMailAddressScenarioException;
import com.ryoga.bbs.scenario.exception.DuplicateUserNameScenarioException;
import com.ryoga.bbs.scenario.auth.result.SignInResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final SignUpScenario signUpScenario;
    private final SignInScenario signInScenario;
    private final SignOutScenario signOutScenario;
    private final RefreshTokenScenario refreshTokenScenario;

    public AuthController(SignUpScenario signUpScenario,
                          SignInScenario signInScenario,
                          SignOutScenario signOutScenario,
                          RefreshTokenScenario refreshTokenScenario
    ) {
        this.signUpScenario = signUpScenario;
        this.signInScenario = signInScenario;
        this.signOutScenario = signOutScenario;
        this.refreshTokenScenario = refreshTokenScenario;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpForm form) throws DuplicateMailAddressScenarioException, DuplicateUserNameScenarioException {
        SignUpCommand command = SignUpCommand.toCommand(form);
        signUpScenario.signUp(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestHeader("X-Device-Id") String deviceId, @RequestBody SignInForm form) {
        SignInCommand command = SignInCommand.toCommand(form, deviceId);
        SignInResult signInResult = signInScenario.signIn(command);

        ResponseCookie responseCookie = ResponseCookie
                .from("refreshToken", signInResult.refreshToken())
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/api/v1/users/auth")
                .maxAge(Duration.ofDays(14))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .header("Authorization", "Bearer " + signInResult.accessToken())
                .body(SignInResponse.of(signInResult.accessToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@CookieValue(value ="refreshToken", required = false) String refreshToken){
        String accessToken = refreshTokenScenario.refreshToken(refreshToken);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + accessToken)
                .body(RefreshTokenResponse.of(accessToken));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(@CookieValue(value = "refreshToken", required = false) String refreshToken){
        signOutScenario.signOut(refreshToken);

        ResponseCookie deleteCookie = ResponseCookie
                .from("refreshToken", "")
                .path("/api/v1/users/auth")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .build();
    }
}
