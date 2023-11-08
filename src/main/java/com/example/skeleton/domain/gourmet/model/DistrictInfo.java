package com.example.skeleton.domain.gourmet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictInfo {

    @Column(name = "si_gun_gu")
    private String sgg;

    @Column(name = "si_gun_gu_code")
    private String sggCode;

    public static DistrictInfo convertToDistrictInfo(JSONObject item) {
        String sgg = item.get("SIGUN_NM") == null ?
                "지역없음" : (String) item.get("SIGUN_NM");

        String sggCode = item.get("SIGUN_CD") == null ?
                "지역코드없음" : (String) item.get("SIGUN_CD");

        return new DistrictInfo(sgg, sggCode);
    }
}