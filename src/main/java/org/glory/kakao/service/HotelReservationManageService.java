package org.glory.kakao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glory.kakao.model.inner.RoomVO;
import org.glory.kakao.model.receive.NewRequestReceiveVO;
import org.glory.kakao.model.receive.ScoreReceiveVO;
import org.glory.kakao.model.receive.StartReceiveVO;
import org.glory.kakao.model.send.ReplySendVO;

public class HotelReservationManageService {

    private static final ApiService apiService = new ApiService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService();

    public void startHotelReservationManage(Long problem, int endDay, int floor, int roomCountByFloor) {
        // start api 호출
        StartReceiveVO startReceiveVO = apiService.callStartApi(problem);

        Long day = startReceiveVO.getDay();

        // roomStatus 초기화
        // Key : 날짜, Value : 날짜에, 어떤 방이 공석인지 아닌 지 확인하는 객체 RoomVO (방에 대한 정보를 가지고 있다. 예약 여부, 방 번호 등)
        // Value 의 array size 는 [floor + 1][roomCountByFloor + 1] 이다.
        Map<Long, RoomVO[][]> roomStatus = new HashMap<>();
        roomService.initRoomStatus(roomStatus, endDay, floor, roomCountByFloor);

        // 들어온 요청을 담는 array
        List<NewRequestReceiveVO> requests = new ArrayList<>();
        List<ReplySendVO> requestApis = new ArrayList<>();

        // 이미 simulate api 로 호출된 예약 번호를 담는 Set
        Set<Long> alreadySimulated = new HashSet<>();

        while (day <= endDay) {
            // new_requests 호출하여 새로운 예약 요청을 받는다.
            requestApis.clear();
            requests.addAll(apiService.callNewRequestsApi(day));

            Iterator<NewRequestReceiveVO> itr = requests.iterator();
            while (itr.hasNext()) {
                NewRequestReceiveVO request = itr.next();

                /*
                 * 지연 처리 : 요청 수락/거절 날짜가 임박할 때 처리한다면?
                 * 즉각 처리 : 요청이 들어오자 마자 모두 처리한다면?
                 *
                 * [시나리오 1]
                 * 주석처리 X : 지연 처리 : {"accuracy_score":80.0,"efficiency_score":0.0,"penalty_score":192.6037769663299,"score":387.39622303367014}
                 * 주석처리 O : 즉각 처리 : {"accuracy_score":80.0,"efficiency_score":10.0,"penalty_score":191.24017115078743,"score":398.75982884921257}
                 *
                 * [시나리오 2]
                 * 주석처리 X : 지연 처리 : {"accuracy_score":80.0,"efficiency_score":10.0,"penalty_score":102.10380424120659,"score":487.8961957587934}
                 * 주석처리 O : 즉각 처리 : {"accuracy_score":80.0,"efficiency_score":10.0,"penalty_score":102.10380424120659,"score":487.8961957587934}
                 *
                 * 정확한 사유는 모르겠으나, 즉각처리가 더 나은 모습을 보인다.
                 */
//                if (!day.equals(request.getLimitDayToReservationApply())) continue;

                // 예약이 가능하면, accepted reply 호출을, 예약이 불가능하다면 refused reply 호출한다.
                String reply = reservationService.reservationFactory(roomStatus, request, floor,
                    roomCountByFloor);
                requestApis.add(ReplySendVO.builder().id(request.getId()).reply(reply).build());
                itr.remove();
            }

            apiService.callBulkReplyApi(requestApis);

            // day에 해당하는 RoomVO 출력.
            roomService.printRoomStatusInSpecificDay(roomStatus, day, floor, roomCountByFloor);

            // simulate api 호출.
            day = roomService.simulation(roomStatus, alreadySimulated, day, floor, roomCountByFloor);
        }

        ScoreReceiveVO scoreReceiveVO = apiService.callScoreApi();
    }

}
