package com.example.skeleton.domain.gourmet.repository;

import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.mapper.GourmetMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.skeleton.domain.gourmet.entity.QGourmet.gourmet;


@RequiredArgsConstructor
public class GourmetRepositoryImpl implements GourmetRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final GourmetMapper gourmetMapper;

    @Override
    public List<GourmetDistanceResponseDto> getGourmetDtoByLocation(String lat, String lon, Double range, String sort) {

        List<Gourmet> gourmetList = queryFactory.select(gourmet)
                .from(gourmet)
                .fetch();

        gourmetList = gourmetList.stream().filter(g -> range >= latLongToKm(g.getPoint().getLatitude(), g.getPoint().getLatitude(), lat, lon)).collect(Collectors.toList());
        List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList = new ArrayList<>();
        for (Gourmet gourmet : gourmetList) {
            Double distance = latLongToKm(gourmet.getPoint().getLatitude(), gourmet.getPoint().getLatitude(), lat, lon);
            if (distance <= range) {
                gourmetDistanceResponseDtoList.add(GourmetDistanceResponseDto.builder()
                        .distance(distance)
                        .gourmet(gourmetMapper.gourmetToGourmetResponseDto(gourmet))
                        .build());
            }
        }
        return gourmetDistanceResponseDtoList;
    }

    private static double latLongToKm(String slat1, String slon1, String slat2, String slon2) {
        // double[] point1 = { 127.07596008849987, 37.2040 };
        // double[] point2 = { 127.08726833239848, 37.19497222488765 };

        double lat1 = Double.parseDouble(slat1);
        double lon1 = Double.parseDouble(slon1);
        double lat2 = Double.parseDouble(slat2);
        double lon2 = Double.parseDouble(slon2);

        double R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}