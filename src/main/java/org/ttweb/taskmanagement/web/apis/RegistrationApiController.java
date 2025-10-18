package org.ttweb.taskmanagement.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.model.user.EmailAddressExistsException;
import org.ttweb.taskmanagement.domain.model.user.RegistrationException;
import org.ttweb.taskmanagement.domain.model.user.UsernameExistsException;
import org.ttweb.taskmanagement.web.payload.RegistrationPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.Result;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationApiController extends AbstractBaseController {
    private UserService service;

    public RegistrationApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload,
                                              HttpServletRequest request) {
        try {
            RegistrationCommand command = payload.toCommand();
            addTriggeredBy(command, request);

            service.register(command);
            return Result.created();
        } catch (RegistrationException e) {
            String errorMessage = "Registration failed";
            if (e instanceof UsernameExistsException) {
                errorMessage = "Username already exists";
            } else if (e instanceof EmailAddressExistsException) {
                errorMessage = "Email address already exists";
            }
            return Result.failure(errorMessage);
        }
    }
}
