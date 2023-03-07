package org.glory.kakao.model.receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartReceiveVO {

    private String auth_key;
    private Long problem;
    private Long day;

}
