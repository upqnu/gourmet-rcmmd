package com.example.skeleton.domain.client.controller;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.client.dto.ClientRequestDto;
import com.example.skeleton.domain.client.dto.ClientSignInRequestDto;
import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends IntegrationTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void signUp_succeed() throws Exception {
        // given
        String clientId = "testingClient";
        String password = "1a2s3d4f";

        ClientRequestDto signUpRequestDto = ClientRequestDto.of(clientId, password);

        // when
//        getSignUpResultAction(signUpRequestDto)
        mvc.perform(
                        post("/api/clients/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signUpRequestDto))
                )
                // then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())

        ;
    }

    @Test
    void signIn_succeed() throws Exception {
        // given
        Client client = Client.builder()
                .clientId("signInClient")
                .password(passwordEncoder.encode("rewq7890"))
                .build();

        clientRepository.save(client);

        ClientSignInRequestDto clientSignInRequestDto = ClientSignInRequestDto.of(client.getClientId(), "rewq7890");

        // when
        mvc.perform(
                        post("/api/clients/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clientSignInRequestDto))
                )
                // then
                .andDo(print())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

}