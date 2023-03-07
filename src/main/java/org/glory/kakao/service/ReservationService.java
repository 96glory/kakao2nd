package org.glory.kakao.service;

import java.util.Map;
import org.glory.kakao.model.inner.RoomVO;
import org.glory.kakao.model.receive.NewRequestReceiveVO;

public class ReservationService {

    private static final ApiService apiService = new ApiService();

    public String reservationFactory(Map<Long, RoomVO[][]> roomStatus, NewRequestReceiveVO request,
        int floor, int roomCountByFloor) {

        // 층의 방 개수보다 더 많은 amount 를 요청한다면, reject 처리.
        Long amount = request.getAmount();
        if (amount > roomCountByFloor) {
            refuseReservation(request);
            return "refused";
        }

        // check_in_date ~ check_out_date 의 RoomVO 를 모두 가져와서, 해당 범위의 날짜에 방이 하루라도 예약되어 있다면, true 로 바꾼다.
        Boolean[][] isFull = new Boolean[floor + 1][roomCountByFloor + 1];

        for (int i = 0; i <= floor; i++) {
            for (int j = 0; j <= roomCountByFloor; j++) {
                isFull[i][j] = false;
            }
        }

        for (Long day : roomStatus.keySet()) {
            if (day >= request.getCheck_in_date() && day < request.getCheck_out_date()) {
                RoomVO[][] room = roomStatus.get(day);

                for (int j = 1; j <= floor; j++) {
                    for (int k = 1; k <= roomCountByFloor; k++) {
                        if (room[j][k].isReserved()) {
                            isFull[j][k] = true;
                        }
                    }
                }
            }
        }

        // amount 에 알맞는 케이스가 있는 지 검사.
        boolean canApply = false;
        for (int i = 1; i <= floor; i++) {
            for (int j = 1; j <= roomCountByFloor - amount + 1; j++) {
                if (isFull[i][j]) {
                    continue;
                }

                boolean tempFlag = true;
                for (int k = j + 1; k <= j + amount - 1; k++) {
                    if (isFull[i][k]) {
                        tempFlag = false;
                        break;
                    }
                }

                if (tempFlag) {
                    canApply = true;
                    acceptReservation(roomStatus, request, i, j);
                }
                if (canApply) {
                    return "accepted";
                }
            }
        }

        refuseReservation(request);
        return "refused";
    }

    public void acceptReservation(Map<Long, RoomVO[][]> roomStatus, NewRequestReceiveVO request,
        int floor, int startRoom) {
        for (Long day : roomStatus.keySet()) {
            if (day >= request.getCheck_in_date() && day < request.getCheck_out_date()) {
                RoomVO[][] room = roomStatus.get(day);

                for (int j = startRoom; j <= startRoom + request.getAmount() - 1; j++) {
                    // 방의 예약 여부는 id의 유무에 갈린다.
                    room[floor][j].setId(request.getId());
                }

                roomStatus.put(day, room);
            }
        }
//        apiService.callReplyApi(request, "accepted");
    }

    public void refuseReservation(NewRequestReceiveVO request) {
//        apiService.callReplyApi(request, "refused");
    }

}
