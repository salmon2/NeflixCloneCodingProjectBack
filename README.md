# CloneCodingPJT

## 프로젝트 소개

![netfilx](./readMe_img/netfliix.png)

> **넷플릭스 페이지 클론코딩**

- 클론코딩 주차에 넷플릭스를 선정해서 클론 코딩을 했음

#### &#128198;**프로젝트 기간**

2021년 10월 18일 ~ 2021년 10월 22일

#### 👥개발인원

- **박기남**(Spring)
- **김선겸**(Spring)

## 사용 기술 및 환경

#### 🛠기술스텍

- Spring
    - Java 8
    - SpringBoot 2.5.2
    - Gradle 7.0.2
    - JPA
    - MySQL 8.0
    - H2 Database (for test)

- AWS EC2 (ubuntu 18.04 LTS)

- AWS RDS (MySQL 8.0)

- The Movie DataBase API 사용 (TMDB)


## 프로젝트의 주요 관심사 

### 📋중복 데이터 제거

###  📊 스케줄러
- data를 요청할 때 마다 api를 사용한다면 시간이 길어지므로 최초에 data를 저장하고 일정 시간에 따라 DB  
업데이트 할 수 있도록 스케줄러 기능을 사용함.

```java

@Component                   // @Component 를 붙혀줘야 한다 !
@RequiredArgsConstructor
public class Schedule {
.
.
.
 private final static int time = 1000 * 60 * 60 * 24; // 시간 지정

    @Scheduled(fixedDelay = time) // scheduler 끝나는 시간 기준으로 1000 간격으로 실행
    @Transactional
    public void scheduleDelayTask() throws Exception {
        count = count +1;
        System.out.println(count + "번째 스케쥴링 시작");
        videoRepository.deleteAll();
        
        ....
        ....
    }
```

