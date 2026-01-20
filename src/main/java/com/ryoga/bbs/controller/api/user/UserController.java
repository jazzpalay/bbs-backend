package com.ryoga.bbs.controller.api.user;

import com.ryoga.bbs.controller.api.user.form.SignInForm;
import com.ryoga.bbs.controller.api.user.form.SignUpForm;
import com.ryoga.bbs.controller.api.user.response.SignInResponse;
import com.ryoga.bbs.scenario.SignUpScenario;
import com.ryoga.bbs.scenario.command.SignInCommand;
import com.ryoga.bbs.scenario.command.SignUpCommand;
import com.ryoga.bbs.scenario.exception.DuplicateMailAddressScenarioException;
import com.ryoga.bbs.scenario.exception.DuplicateUserNameScenarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final SignUpScenario signUpScenario;

    public UserController(SignUpScenario signUpScenario) {
        this.signUpScenario = signUpScenario;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpForm form) throws DuplicateMailAddressScenarioException, DuplicateUserNameScenarioException {
        SignUpCommand command = SignUpCommand.toCommand(form);
        signUpScenario.signUp(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInForm form) {
        SignInCommand command = SignInCommand.toCommand(form);
        String jwt = signUpScenario.signIn(command);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .body(SignInResponse.of(jwt));
    }
}
