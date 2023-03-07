package org.glory.kakao.model.inner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomVO {

    private Long floor;
    private Long number;
    private Long id;

    public Boolean isReserved() {
        return id != null;
    }

    public Long getRoom_number() {
        String roomNumber = Long.toString(floor);
        if (number >= 100L) {
            roomNumber += Long.toString(number);
        } else if (number >= 10L) {
            roomNumber += "0" + Long.toString(number);
        } else {
            roomNumber += "00" + Long.toString(number);
        }
        return Long.valueOf(roomNumber);
    }

}
