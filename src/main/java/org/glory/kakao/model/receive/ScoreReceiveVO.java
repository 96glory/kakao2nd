package org.glory.kakao.model.receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreReceiveVO {

    private Double accuracy_score;
    private Double efficiency_score;
    private Double penalty_score;
    private Double score;

}
