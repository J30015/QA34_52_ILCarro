package dto;
import lombok.*;
import utils.enums.Fuel;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Car {
//    private String manufacture;
//    private String model;
//    private String year;
//    private String[] fuel_types;
//    private String seats;
//    private String carClass;
//    private String number;
//    private String price;
//    private String textAbout;


private String serialNumber ;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;




}
