package com.example.skeleton.domain.district.service;

import com.example.skeleton.domain.client.entity.Location;
import com.example.skeleton.domain.district.entity.District;
import com.example.skeleton.domain.district.repository.DistrictRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    /**
     * 시군구 위도, 경도 데이터를 담은 csv 업로드
     * @param file - MultipartFile : csv file
     * */
    @Transactional
    public void uploadSggCsv(MultipartFile file) throws IOException {

        List<District> districtList = new ArrayList<>();

        // csv 파일 데이터 전처리
        try {
            // 파일의 내용을 바이트 배열로 가져온 후, 이를 문자열로 변환합니다. 그 뒤 줄바꿈으로 나누어 각 행을 얻습니다.
            String[] rows = new String(file.getBytes()).split("\\n");

            for(int i = 1; i < rows.length; i++) {
                // csv 파일이었기 때문에 쉼표로 나누어 각 열을 얻습니다.
                String[] columns = rows[i].split(",");

                // District 객체를 생성해 리스트에 추가합니다.
                districtList.add(
                        District.of((long) i, columns[0], columns[1], Location.of(columns[3], columns[2]))
                );
            }
        } catch (IOException e) {
            log.error("IOException during reading file", e);
        }

        // 데이터 저장 전 삭제
        districtRepository.deleteAll();

        // 데이터 DB에 저장
        districtRepository.saveAll(districtList);

    }
}
