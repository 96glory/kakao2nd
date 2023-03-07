package org.glory.kakao.service;

import static org.glory.util.GsonUtils.getJsonStringFromObject;
import static org.glory.util.GsonUtils.getObjectFromJsonString;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glory.kakao.model.receive.NewRequestReceiveVO;
import org.glory.kakao.model.receive.ScoreReceiveVO;
import org.glory.kakao.model.receive.SimulationReceiveVO;
import org.glory.kakao.model.receive.StartReceiveVO;
import org.glory.kakao.model.send.ReplySendVO;
import org.glory.kakao.model.send.SimulationSendVO;
import org.glory.util.HttpUtils;

public class ApiService {

    private static final String BASE_URL = "https://68ecj67379.execute-api.ap-northeast-2.amazonaws.com/api";
    private static final String X_AUTH_TOKEN = "5697109e3c70dee7f1cf488f3dfbe103";
    private static String AUTH_KEY = "";

    public StartReceiveVO callStartApi(Long problem) {
        StartReceiveVO startApiOutput = null;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("problem", problem);

        // Start Simulation (Start API)
        Map<String, String> postHeader = new HashMap<>();
        postHeader.put("X-Auth-Token", X_AUTH_TOKEN);

        try {
            String jsonString = HttpUtils.post(BASE_URL + "/start",
                getJsonStringFromObject(jsonObject), postHeader);
            startApiOutput = getObjectFromJsonString(jsonString, StartReceiveVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert startApiOutput != null;
        this.setAUTH_KEY(startApiOutput.getAuth_key());

        return startApiOutput;
    }

    public List<NewRequestReceiveVO> callNewRequestsApi(Long day) {
        List<NewRequestReceiveVO> newRequestReceiveVOs = new ArrayList<>();

        Map<String, String> getHeader = new HashMap<>();
        getHeader.put("Authorization", AUTH_KEY);

        try {
            String jsonString = HttpUtils.get(BASE_URL + "/new_requests", getHeader);

            Type type = new TypeToken<Map<String, List<NewRequestReceiveVO>>>(){}.getType();
            Map<String, List<NewRequestReceiveVO>> desMap = new Gson().fromJson(jsonString, type);
            newRequestReceiveVOs = desMap.get("reservations_info");
        } catch (IOException e) {
            e.printStackTrace();
        }

        newRequestReceiveVOs.forEach(row -> row.setDay(day));

        return newRequestReceiveVOs;
    }

    public void callReplyApi(NewRequestReceiveVO request, String reply) {
        Map<String, String> putHeader = new HashMap<>();
        putHeader.put("Authorization", AUTH_KEY);

        Map<String, List<ReplySendVO>> toSend = new HashMap<>();
        toSend.put("replies", List.of(ReplySendVO.builder().id(request.getId()).reply(reply).build()));
        String jsonStringToSend = getJsonStringFromObject(toSend);

        try {
            String jsonString = HttpUtils.put(BASE_URL + "/reply", jsonStringToSend, putHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void callBulkReplyApi(List<ReplySendVO> requests) {
        Map<String, String> putHeader = new HashMap<>();
        putHeader.put("Authorization", AUTH_KEY);

        Map<String, List<ReplySendVO>> toSend = new HashMap<>();
        toSend.put("replies", requests);
        String jsonStringToSend = getJsonStringFromObject(toSend);

        try {
            String jsonString = HttpUtils.put(BASE_URL + "/reply", jsonStringToSend, putHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SimulationReceiveVO callSimulateApi(List<SimulationSendVO> simulationSendVos) {
        SimulationReceiveVO simulationReceiveVO = null;

        Map<String, String> putHeader = new HashMap<>();
        putHeader.put("Authorization", AUTH_KEY);

        Map<String, List<SimulationSendVO>> toSend = new HashMap<>();
        toSend.put("room_assign", simulationSendVos);
        String jsonStringToSend = getJsonStringFromObject(toSend);

        try {
            String jsonString = HttpUtils.put(BASE_URL + "/simulate", jsonStringToSend, putHeader);
            simulationReceiveVO = getObjectFromJsonString(jsonString, SimulationReceiveVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return simulationReceiveVO;
    }

    public ScoreReceiveVO callScoreApi() {
        ScoreReceiveVO scoreReceiveVO = null;

        Map<String, String> getHeader = new HashMap<>();
        getHeader.put("Authorization", AUTH_KEY);

        try {
            String jsonString = HttpUtils.get(BASE_URL + "/score", getHeader);

            scoreReceiveVO = getObjectFromJsonString(jsonString, ScoreReceiveVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scoreReceiveVO;
    }

    private void setAUTH_KEY(String AUTH_KEY) {
        this.AUTH_KEY = AUTH_KEY;
    }

}
