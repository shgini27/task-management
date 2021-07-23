package org.ttweb.taskmanagement.web.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.ttweb.taskmanagement.config.SecurityConfiguration;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.model.user.EmailAddressExistsException;
import org.ttweb.taskmanagement.domain.model.user.UsernameExistsException;
import org.ttweb.taskmanagement.utils.JsonUtils;
import org.ttweb.taskmanagement.web.apis.RegistrationApiController;
import org.ttweb.taskmanagement.web.payload.RegistrationPayload;

import java.util.Objects;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, RegistrationApiController.class})
@ActiveProfiles("test")
@WebMvcTest
public class RegistrationApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService serviceMock;

    @Test
    public void register_blankPayload_shouldFailAndReturn400() throws Exception {
        mvc.perform(post("/api/registrations")).andExpect(status().is(400));
    }

    @Test
    public void register_existedUsername_shouldFailAndReturn400() throws Exception{
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@ttweb.org");
        payload.setPassword("OldBlackMan68!");

        doThrow(UsernameExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(Objects.requireNonNull(JsonUtils.toJson(payload))))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username already exist"));
    }

    @Test
    public void register_existedEmail_shouldFailAndReturn400() throws Exception{
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("test");
        payload.setEmailAddress("exist@ttweb.org");
        payload.setPassword("OldBlackMan68!");

        doThrow(EmailAddressExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtils.toJson(payload))))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Email address already exist"));
    }

    @Test
    public void register_validPayload_shouldSucceedAndReturn201() throws Exception{
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@ttweb.org");
        payload.setPassword("OldBlackMan68!");

        doNothing().when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtils.toJson(payload))))
                .andExpect(status().is(201));
    }
}
