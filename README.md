안녕하세요, 지원자 송영광입니다.

본 프로젝트를 어떻게 구성하였는지 설명드리기 위해 작성한 글입니다.

### 사용된 라이브러리

- rest api, json parser를 위해, `com.google.code.gson`, `org.apache.httpcomponents`, `org.json` 을 사용하였습니다.
- `gson` 테스트를 위해, `junit`을 추가하였습니다.
- 보다 편리한 개발을 위해 `lombok`을 추가하였습니다.

### project package

- `/src/main/java/util` : rest api 호출, gson 사용하기 위해 공통된 유틸입니다.
- `/src/main/java/org/glory/kakao/KakaoMain_problem1.java` : 시나리오 1번 풀이
- `/src/main/java/org/glory/kakao/KakaoMain_problem2.java` : 시나리오 2번 풀이