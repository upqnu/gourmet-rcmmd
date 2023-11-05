package com.example.skeleton.domain.gourmet.mapper;

import com.example.skeleton.domain.gourmet.dto.GourmetResponseDto;
import com.example.skeleton.domain.gourmet.dto.GourmetResponseDto.GourmetResponseDtoBuilder;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T02:29:12+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class GourmetMapperImpl implements GourmetMapper {

    @Override
    public GourmetResponseDto gourmetToGourmetResponseDto(Gourmet gourmet) {
        if ( gourmet == null ) {
            return null;
        }

        GourmetResponseDtoBuilder gourmetResponseDto = GourmetResponseDto.builder();

        gourmetResponseDto.id( gourmet.getId() );
        gourmetResponseDto.gourmetCode( gourmet.getGourmetCode() );
        gourmetResponseDto.name( gourmet.getName() );
        gourmetResponseDto.category( gourmet.getCategory() );
        gourmetResponseDto.point( gourmet.getPoint() );
        gourmetResponseDto.address( gourmet.getAddress() );
        gourmetResponseDto.isOpen( gourmet.getIsOpen() );
        gourmetResponseDto.rating( gourmet.getRating() );

        return gourmetResponseDto.build();
    }

    @Override
    public List<GourmetResponseDto> gourmetsToGourmetResponseDtos(List<Gourmet> gourmet) {
        if ( gourmet == null ) {
            return null;
        }

        List<GourmetResponseDto> list = new ArrayList<GourmetResponseDto>( gourmet.size() );
        for ( Gourmet gourmet1 : gourmet ) {
            list.add( gourmetToGourmetResponseDto( gourmet1 ) );
        }

        return list;
    }
}
