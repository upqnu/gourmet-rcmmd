package com.example.socialmediafeed.domain.statistics.repository;

import com.example.socialmediafeed.domain.statistics.dto.StatisticsResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.socialmediafeed.domain.post.entity.QPost.post;
import static com.example.socialmediafeed.domain.posthashtag.entity.QPostHashtag.postHashtag;

@RequiredArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<StatisticsResponseDto> getStatistics(String hashtag, Map<LocalDateTime, LocalDateTime> dateTimeMap, String value) {

        List<StatisticsResponseDto> statisticsResponseDtoList = new ArrayList<>();

        for (Map.Entry<LocalDateTime, LocalDateTime> dateTime : dateTimeMap.entrySet()) {
            Integer dateCount = 0;

            // 조회수 기준
            if (Objects.equals(value, "view_count")) {
                dateCount = queryFactory
                        .select(post.viewCount.sum())
                        .from(post)
                        .join(postHashtag).on(postHashtag.hashtag.name.eq(hashtag), post.id.eq(postHashtag.post.id))
                        .where(post.createdAt.between(dateTime.getKey(), dateTime.getValue()))
                        .fetchFirst();
            }

            // 좋아요 기준
            else if (Objects.equals(value, "like_count")) {
                dateCount = queryFactory
                        .select(post.likeCount.sum())
                        .from(post)
                        .join(postHashtag).on(postHashtag.hashtag.name.eq(hashtag), post.id.eq(postHashtag.post.id))
                        .where(post.createdAt.between(dateTime.getKey(), dateTime.getValue()))
                        .fetchFirst();
            }

            // 공유수 기준
            else if (Objects.equals(value, "share_count")) {
                dateCount = queryFactory
                        .select(post.shareCount.sum())
                        .from(post)
                        .join(postHashtag).on(postHashtag.hashtag.name.eq(hashtag), post.id.eq(postHashtag.post.id))
                        .where(post.createdAt.between(dateTime.getKey(), dateTime.getValue()))
                        .fetchFirst();
            }

            // 게시글 수 기준
            else {
                dateCount = Math.toIntExact(queryFactory
                        .select(post.count())
                        .from(post)
                        .join(postHashtag).on(postHashtag.hashtag.name.eq(hashtag), post.id.eq(postHashtag.post.id))
                        .where(post.createdAt.between(dateTime.getKey(), dateTime.getValue()))
                        .fetchFirst());
            }

            // count 값 더하기
            statisticsResponseDtoList.add(StatisticsResponseDto.builder()
                    .time(dateTime.getKey())
                    .count(dateCount == null ? 0 : dateCount)
                    .build());
        }
        return statisticsResponseDtoList;
    }
}
