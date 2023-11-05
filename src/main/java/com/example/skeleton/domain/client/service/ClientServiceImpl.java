package com.example.skeleton.domain.client.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.skeleton.domain.client.dto.ClientInfo;
import com.example.skeleton.domain.client.dto.ClientRequestDto;
import com.example.skeleton.domain.client.dto.ClientResponseDto;
import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.entity.Location;
import com.example.skeleton.domain.client.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

        private final ClientRepository clientRepo;
        private final PasswordEncoder passwordEncoder;

        @Override
        public ClientResponseDto signUp(ClientRequestDto dto) {

                // 아이디 중복 체크
                if (clientRepo.existsByClientId(dto.getClientId())) {
                        return ClientResponseDto.builder()
                                        .message("이미 존재하는 아이디입니다.")
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .build();
                }

                // 아이디 글자 수 체크
                if (dto.getClientId().length() > 20) {
                        return ClientResponseDto.builder()
                                        .message("아이디는 20자를 초과할 수 없습니다.")
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .build();
                }

                clientRepo.save(Client.builder()
                                .clientId(dto.getClientId())
                                .password(passwordEncoder.encode(dto.getPassword()))
                                .build());

                return ClientResponseDto.builder()
                                .message("회원가입 성공")
                                .status(HttpStatus.CREATED.value())
                                .build();
        }

        @Override
        public ClientResponseDto setClientInfo(ClientRequestDto dto) {
                Client client = clientRepo.findByClientId(dto.getClientId()).get();

                client.setLocation(Location.builder()
                                .latitude(dto.getLatitude())
                                .longitude(dto.getLongitude())
                                .build());

                client.setPermission(dto.getPermission());

                clientRepo.save(client);

                return ClientResponseDto.builder()
                                .message("회원 정보 수정 성공")
                                .status(HttpStatus.OK.value())
                                .build();
        }

        @Override
        public ClientResponseDto getClientInfo(String clientId) {
                Client client = clientRepo.findByClientId(clientId).get();

                return ClientResponseDto.builder()
                                .data(ClientInfo.builder()
                                                .clientId(client.getClientId())
                                                .location(client.getLocation())
                                                .permission(client.getPermission())
                                                .build())
                                .message("회원 정보 조회 성공")
                                .status(HttpStatus.OK.value())
                                .build();
        }
}
