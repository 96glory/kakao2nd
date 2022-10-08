package org.glory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressTestVO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoTestVO geo;

}
