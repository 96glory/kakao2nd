package org.glory.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressTestVO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoTestVO geo;

}
