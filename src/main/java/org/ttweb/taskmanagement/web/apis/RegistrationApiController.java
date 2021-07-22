package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.model.user.EmailAddressExistsException;
import org.ttweb.taskmanagement.domain.model.user.RegistrationException;
import org.ttweb.taskmanagement.domain.model.user.UsernameExistsException;
import org.ttweb.taskmanagement.web.payload.RegistrationPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.Result;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {
    private UserService service;

    @Autowired
    public RegistrationApiController(UserService service){
        this.service = service;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload){
        try{
            service.register(payload.toCommand());
            return Result.created();
        }catch (RegistrationException e){
            String errorMessage = "Registration failed";
            if(e instanceof UsernameExistsException){
                errorMessage = "Username already exist";
            }else if(e instanceof EmailAddressExistsException){
                errorMessage = "Email address already exist";
            }

            return Result.failure(errorMessage);
        }
    }
}
