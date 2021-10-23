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
## 시연영상
![넷플클론코딩움짤2](https://user-images.githubusercontent.com/23234577/138534787-6264295f-4ea5-4c80-9112-c72409eab7b1.gif)

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
- DB에서 responseDto를 제작 시 추가 된 모든 비디오들은 중복체크를 도와주는 리스트에 이름을 추가로 저장한다.
- 모든 비디오들은 responseDto에 넣기 전에 이 리스트에 포함여부를 체크한다. 
- 리스트에 들어가지 않는 비디오만을 responseDto에 담는다.
```java
    private boolean checkEffectiveness(List<String> duplicateCheck, Video video, String largeCategory) {
        //중복체크하기
        boolean contains = duplicateCheck.contains(video.getTitle());
        //movie, tvShow 체크
        boolean nameEquals = largeCategory.equals(video.getLargeCategory().getLargeCategoryName());


        if(contains == true){
            return false;
        }
        else{
            if(nameEquals == true || largeCategory == "random")
                return true;
            else
                return false;
        }

    }
```

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
### The Movie DB API, 외부 api 사용
- 넷플릭스 클론 코딩에 필요한 tv show 혹은 movie 데이터는 위의 theMovieDbApi를 통해서 가져왔다.
- https://www.themoviedb.org/documentation/api
- spring에서 외부 api 사용하기
```java
public  TheMovieApiResponseDto TheMovieDBSearchByGenre(int genreId) throws Exception{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        
        String genreStringId = Integer.toString(genreId);

        //Object Mapper를 통한 JSON 바인딩1
        HttpEntity httpEntity = new HttpEntity(httpHeaders);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(
                "https://api.themoviedb.org/3/discover/movie?api_key="+api_key+
                        "&language=ko-KR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1" +
                        "&primary_release_date.gte=2000-01-01&primary_release_date.lte=2100-12-31&vote_average.gte=6" +
                        "&with_genres="+genreStringId+"\n",
                HttpMethod.GET, httpEntity, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

        Gson gson = new Gson();
        TheMovieApiResponseDto theMovieApiResponseDto = gson.fromJson(jsonObject.toString(), TheMovieApiResponseDto.class);

        System.out.println("theMovieApiResponseDto = " + theMovieApiResponseDto.getResults());

        return theMovieApiResponseDto;
    }
```
