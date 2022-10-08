package org.glory.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data = Getter + Setter + ToString + EqualsAndHashCode + RequiredArgsConstructor
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTestVO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressTestVO address;

}
