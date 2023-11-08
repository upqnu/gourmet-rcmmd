package com.example.skeleton.domain.gourmet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.json.simple.JSONObject;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Employee {

    @Column(name = "male_employee")
    private Long male;

    @Column(name = "female_employee")
    private Long female;

    @Column(name = "total_employee")
    private Long total;

    //Json Object를 Employee 객체로 변환하는 정적 팩토리 메서드
    public static Employee convertToEmployee(JSONObject item) {
        Long male = item.get("MALE_ENFLPSN_CNT") == null ? 0L : (Long) item.get("MALE_ENFLPSN_CNT");
        Long female = item.get("FEMALE_ENFLPSN_CNT") == null ? 0L : (Long) item.get("FEMALE_ENFLPSN_CNT");

        String totalEmployeeCountKeyword = "TOT_EMPLY_CNT";

        Long total = item.get(totalEmployeeCountKeyword) == null || item.get(totalEmployeeCountKeyword).equals(0L)
                ? male + female : (Long) item.get(totalEmployeeCountKeyword);

        return new Employee(male, female, total);
    }

}
