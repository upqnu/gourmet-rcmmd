package com.example.skeleton.domain.gourmet.mapper;

import com.example.skeleton.domain.gourmet.dto.GourmetResponseDto;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GourmetMapper {

    GourmetResponseDto gourmetToGourmetResponseDto(Gourmet gourmet);

    List<GourmetResponseDto> gourmetsToGourmetResponseDtos(List<Gourmet> gourmet);
}
