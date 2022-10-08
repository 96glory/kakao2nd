package org.glory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTestVO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressTestVO address;

}
