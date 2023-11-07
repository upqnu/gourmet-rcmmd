package com.example.skeleton.domain.jwt.controller;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.client.dto.ClientSignInRequestDto;
import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.repository.ClientRepository;
import com.example.skeleton.global.config.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RefreshTokenControllerTest extends IntegrationTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void renewAccessTokenByRefreshToken_succeed() throws Exception {
        // given
        // 1. 회원가입
        Client client = Client.builder()
                .clientId("refreshedClient")
                .password(passwordEncoder.encode("vcxz4567"))
                .build();

        clientRepository.save(client);

        // 2. 로그인 시뮬레이션
        String clientId = client.getClientId();
        String password = "vcxz4567";

        ClientSignInRequestDto clientSignInRequestDto = ClientSignInRequestDto.of(clientId, password);

        MvcResult resultOfSignIn = mvc.perform(
                post("/api/clients/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientSignInRequestDto))
        )
                .andExpect(status().isOk())
                .andReturn();

        // 3. 액세스 토큰 발급 및 확보
        String responseContentOfSignIn = resultOfSignIn.getResponse().getContentAsString();
        JsonNode responseJsonOfSignIn = objectMapper.readTree(responseContentOfSignIn);
        String accessToken = responseJsonOfSignIn.get("accessToken").asText();

        // when
        // 사용자의 리프레시 토큰을 활용하여 액세스 토큰 갱신
        String renewedAccessToken = jwtTokenProvider.issueToken(client, "refresh");

        // then
        // 최초 발급된 액세스 토큰, 갱신되 액세스 토큰의 헤더, 페이로드, 서명을 비교하여
        // 헤더는 완전히 동일, 페이로드는 일부가 동일, 서명은 서로 다를 경우 ; 테스트 성공
        // (아래 코드는 조금 더 테스트다운 방법으로 성공할 수 있도록 수정이 필요함)
        System.out.println(accessToken);
        System.out.println(renewedAccessToken);

        String[] original = accessToken.split("\\.");
        String[] renewed = renewedAccessToken.split("\\.");

        int orgLen = original[1].length();
        int rnwLen = renewed[1].length();

        if (original[0].equals(renewed[0]) && !original[2].equals(renewed[2]) &&
                original[1].substring(0,20).equals(renewed[1].substring(0,20)) &&
                original[1].substring(orgLen-20, orgLen).equals(renewed[1].substring(rnwLen-20, rnwLen))
        ) {
            System.out.println("액세스 토큰 갱신 성공!");
        } else {
            System.out.println("액세스 토큰 갱신 실패!");
        }
    }
}