# VanillaSky
방장이 그림을 그리면 다른 사람들이 무엇을 그렸는지 맞추는 게임 ( 캐치마인드 )
무엇인지 맞춘 사람이 방장 권한을 획득, 다시 게임이 진행됨

## 개발 환경, 사용 도구
* Java, Tomcat
* Spring, WebSocket
* HTML, CSS, JavaScript, Bootstrap
* Redis DB

## 구현 기능
### 유저 로그인
* 따로 가입절차는 없음, 닉네임 입력후 접속하는 방식
* 닉네임과 아이피를 등록, 동일인 중복 로그인 방지

### 게임방 목록
* 현재 등록된 방 목록 보여줌
* 게임 진행 중이거나, 인원수가 다 차면 접속 불가

### 게임방 폼
* 소켓통신, 유저 목록 동적 갱신
* 방장을 제외한 나머지 멤버들은 채팅 가능
* 방장은 그림 관련 기능과 게임 시작버튼만 조작 가능
* 게임 시작 시 전체 채팅으로 알람이 가며, 방장에게만 답이 전송됨
* 채팅에 "정답!"을 붙여서 정답 맞추기 도전 가능 Ex) 정답!포도
* 정답을 맞춘 사람에게 방장 권한이 부여됨, 다시 게임 진행

### [로그인]  방 만들기]
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518243-cbae7280-31d3-11eb-8053-4a74580c4ef4.png">  
### [게임 목록]
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518247-ccdf9f80-31d3-11eb-9cb0-8d40458bed8d.png">
### [방 만들기]
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518249-cd783600-31d3-11eb-9fdd-384fa8473ac6.png">
### [그림 그리기] 
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518251-cd783600-31d3-11eb-8b1f-03f0cd4dd476.png">
### [정답 맞췄을 시]
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518252-ce10cc80-31d3-11eb-8cf5-fe944937f8c5.png">
### [게임 목록(진행중인 방)]
<img width="700" src="https://user-images.githubusercontent.com/59993347/100518253-cea96300-31d3-11eb-8674-924eb18da665.png">