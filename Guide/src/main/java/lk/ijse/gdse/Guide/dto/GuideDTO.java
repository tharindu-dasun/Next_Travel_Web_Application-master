package lk.ijse.gdse.Guide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GuideDTO {
    private Long guideId;
    private String fullName;
    private String address;
    private int age;
    private int contactNumber;
    private String gender;
    private String experience;
    private double price;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
}
