package org.glory.kakao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glory.kakao.model.inner.RoomVO;
import org.glory.kakao.model.receive.SimulationReceiveVO;
import org.glory.kakao.model.send.SimulationSendVO;

public class RoomService {

    private static final ApiService apiService = new ApiService();

    public void initRoomStatus(Map<Long, RoomVO[][]> roomStatus, int day, int floor, int roomCountByFloor) {
        for (int i = 1; i <= day; i++) {
            RoomVO[][] tempRoomVOs = new RoomVO[floor + 1][roomCountByFloor + 1];

            for (int j = 1; j <= floor; j++) {
                for (int k = 1; k <= roomCountByFloor; k++) {
                    tempRoomVOs[j][k] = RoomVO.builder().floor((long) j).number((long) k).build();
                }
            }
            roomStatus.put((long) i, tempRoomVOs);
        }
    }

    public Long simulation(Map<Long, RoomVO[][]> roomStatus, Set<Long> alreadySimulated, Long day, int floor, int roomCountByFloor) {
        List<SimulationSendVO> simulationSendVos = new ArrayList<>();

        RoomVO[][] rooms = roomStatus.get(day);

        for (int i = 1; i <= floor; i++) {
            for (int j = 1; j <= roomCountByFloor; j++) {
                if (rooms[i][j].isReserved() && !alreadySimulated.contains(rooms[i][j].getId())) {
                    simulationSendVos.add(SimulationSendVO.builder().id(rooms[i][j].getId())
                        .room_number(rooms[i][j].getRoom_number()).build());
                    alreadySimulated.add(rooms[i][j].getId());
                }
            }
        }

        SimulationReceiveVO simulationReceiveVO = apiService.callSimulateApi(simulationSendVos);
        return simulationReceiveVO.getDay();
    }

    public void printRoomStatusInSpecificDay(Map<Long, RoomVO[][]> roomStatus, Long day, int floor, int roomCountByFloor) {
        RoomVO[][] rooms = roomStatus.get(day);

        for (int i = 1; i <= floor; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= roomCountByFloor; j++) {
                System.out.print(rooms[i][j].isReserved() ? rooms[i][j].getId() + "\t" : "0\t");
            }
            System.out.println("");
        }
    }

}
