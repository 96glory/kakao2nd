package org.glory.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glory.kakao.service.ApiService;
import org.glory.kakao.service.HotelReservationManageService;
import org.glory.kakao.service.ReservationService;
import org.glory.kakao.service.RoomService;
import org.glory.kakao.model.inner.RoomVO;
import org.glory.kakao.model.receive.NewRequestReceiveVO;
import org.glory.kakao.model.receive.ScoreReceiveVO;
import org.glory.kakao.model.receive.StartReceiveVO;

public class KakaoMain_problem1 {

    private static final Long PROBLEM = 1L;
    private static final int END_DAY = 200;
    private static final int FLOOR = 3;
    private static final int ROOM_COUNT_BY_FLOOR = 20;

    private static final HotelReservationManageService hotelReservationManageService = new HotelReservationManageService();

    public static void main(String[] args) {
        hotelReservationManageService.startHotelReservationManage(PROBLEM, END_DAY, FLOOR,
            ROOM_COUNT_BY_FLOOR);
    }

}
