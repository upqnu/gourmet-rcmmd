package com.example.skeleton.domain.gourmet.repository;

import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.mapper.GourmetMapper;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.skeleton.domain.gourmet.entity.QGourmet.gourmet;


@RequiredArgsConstructor
public class GourmetRepositoryImpl implements GourmetRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final GourmetMapper gourmetMapper;

    @Override
    public List<GourmetDistanceResponseDto> getGourmetDtoByLocation(String lat, String lon, Double range, String search) {

        List<Gourmet> gourmetList = queryFactory.select(gourmet)
                .from(gourmet)
                .orderBy(gourmet.rating.desc())
                .where(gourmet.name.contains(search))
                .fetch();

        List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList = new ArrayList<>();
        for (Gourmet gourmet : gourmetList) {
            Double distance = distance(gourmet.getPoint().getLatitude(), gourmet.getPoint().getLongitude(), lat, lon);
            if (distance <= range) {
                gourmetDistanceResponseDtoList.add(GourmetDistanceResponseDto.builder()
                        .distance(distance)
                        .gourmet(gourmetMapper.gourmetToGourmetResponseDto(gourmet))
                        .build());
            }
        }
        return gourmetDistanceResponseDtoList;
    }

    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1S 지점 1 위도
     * @param lon1S 지점 1 경도
     * @param lat2S 지점 2 위도
     * @param lon2S 지점 2 경도
     * @param unit  거리 표출단위
     * @return
     */
    private static double distance(String lat1S, String lon1S, String lat2S, String lon2S) {

        double lat1 = Double.parseDouble(lat1S);
        double lon1 = Double.parseDouble(lon1S);
        double lat2 = Double.parseDouble(lat2S);
        double lon2 = Double.parseDouble(lon2S);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}