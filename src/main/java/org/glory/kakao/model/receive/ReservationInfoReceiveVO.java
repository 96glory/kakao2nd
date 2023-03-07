package org.glory.kakao.model.receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoReceiveVO {

    private Long id;
    private Long amount;
    private Long check_in_date;
    private Long check_out_date;

}
