package com.example.skeleton.domain.rating.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.repository.ClientRepository;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import com.example.skeleton.domain.rating.dto.RatingRequestDto;
import com.example.skeleton.domain.rating.dto.RatingResponseDto;
import com.example.skeleton.domain.rating.entity.Rating;
import com.example.skeleton.domain.rating.repository.RatingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class RatingServiceImpl implements RatingService {

    private final ClientRepository clientRepo;
    private final GourmetRepository gourmetRepo;
    private final RatingRepository ratingRepo;

    @Override
    public RatingResponseDto createRating(RatingRequestDto dto) {
        /* 새로운 평가 저장 */
        Client client = clientRepo.findByClientId(dto.getClientId()).get();
        Gourmet gourmet = gourmetRepo.findByGourmetCode(dto.getGourmetCode()).get();

        ratingRepo.save(Rating.builder()
                .client(client)
                .gourmet(gourmet)
                .content(dto.getContent())
                .score(dto.getScore())
                .build());

        /* 맛집 평점 업데이트 */
        Long numOfRatings = ratingRepo.countByGourmet(gourmet);
        int newScore = dto.getScore();
        double curScore = gourmet.getRating();
        double updatedScore = (curScore + newScore) / (numOfRatings + 1);

        gourmet.updateRating(updatedScore);
        gourmetRepo.save(gourmet);

        return RatingResponseDto.builder()
                .status(HttpStatus.CREATED.value())
                .message("평가 저장 성공")
                .build();
    }

}
