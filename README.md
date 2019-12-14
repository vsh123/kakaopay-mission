# 카카오페이 미션

# 지자체 협약 지원 API 개발

## 개발 프레임워크

- Spring Boot
- JPA
- H2 hibernate

## 빌드 및 실행 방법

- 해당 Repository clone 및 폴더 접속

    > git clone --single-branch -b master https://github.com/vsh123/kakaopay-mission.git
    > cd kakaopay-mission

### MAC/Linux에서 실행 방법

- 해당 폴더 안에 있는 [deploy.sh](http://deploy.sh) 실행

    > sh deploy.sh

### Windows에서 실행 방법

- cmd에서 현재 8080으로 작동되고 있는 포트 확인 후 만약 존재한다면 해당 PID kill

    > netstat -ano | findstr 8080
    > taskkill /pid {확인한PID} /F

- MAC/Linux와 동일하게 deploy.sh실행

    > sh deploy.sh

## 문제 해결방법

## 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발

- opencsv를 이용하여 해결

```gradle
    implementation 'net.sf.opencsv:opencsv:2.0'
```    

- `/api/upload` 로 들어온 MultiPartFile을 받아 OpenCsv의 `CsvReader` 를 이용하여 row별로 Region 및 RegionSupportInformation 엔티티에 저장

### [Request]

    POST /api/upload HTTP/1.1
    Content-Type: application/x-www-form-urlencoded
    Authorization: Bearer 인증받은JWT

> 아래 파라미터를 입력할 수 있습니다.

| 키   | 설명               | 필수 |
|------|--------------------|------|
| file | 저장하려는 csv파일 |  o    |

### [Response]

    [
        {
            "region": "강릉시",
            "target": "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
            "usage": "운전",
            "limit": "추천금액 이내",
            "rate": "3%",
            "institute": "강릉시",
            "mgmt": "강릉지점",
            "reception": "강릉시 소재 영업점"
        },
        {
            "region": "강원도",
            "target": "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
            "usage": "운전",
            "limit": "8억원 이내",
            "rate": "3%~5%",
            "institute": "강원도",
            "mgmt": "춘천지점",
            "reception": "강원도 소재 영업점"
        },
    .......
    }

## 지원하는 지자체 목록 검색 API 개발

- Jpa의 findAll()을 이용

```java
    public RegionSupInfoInternalService(RegionSupportInformationRepository regionSupportInformationRepository) {
            this.regionSupportInformationRepository = regionSupportInformationRepository;
        }
```

### [Request]

    GET /api/regionsupinfos HTTP/1.1
    Authorization: Bearer 인증받은JWT

### [Response]

    [
        {
            "region": "강릉시",
            "target": "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
            "usage": "운전",
            "limit": "추천금액 이내",
            "rate": "3%",
            "institute": "강릉시",
            "mgmt": "강릉지점",
            "reception": "강릉시 소재 영업점"
        },
        {
            "region": "강원도",
            "target": "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
            "usage": "운전",
            "limit": "8억원 이내",
            "rate": "3%~5%",
            "institute": "강원도",
            "mgmt": "춘천지점",
            "reception": "강원도 소재 영업점"
        },
    .......
    }

## 지원하는 지자체명을 입력 받아 해당 지자체의 지원정보를 출력하는 API 개발

- Region엔티티를 통해 해당 정보 출력

```java
    public RegionSupportInfoResponseDto findByRegionName(String regionName) {
            Region region = regionInternalService.findByName(regionName);
            return RegionSupportInfoConverter
                    .toRegionSupportInfoResponseDto(regionSupInfoInternalService.findByRegionCode(region.getCode()));
        }
```

### [Request]

    GET /api/regionsupinfos/search?region={region이름} HTTP/1.1
    Authorization: Bearer 인증받은JWT

> 아래 파라미터를 입력할 수 있습니다.

| 키   | 설명               | 필수 |
|------|--------------------|------|
| region | 검색하려는 지자체명 |  o    |

### [Response]

    {
        "region": "부천시",
        "target": "부천시 소재 중소기업으로서 부천시장의 추천을 받은 자",
        "usage": "운전 및 시설",
        "limit": "5억원 이내",
        "rate": "1.5%~2.5%",
        "institute": "부천시",
        "mgmt": "강서제주지역본부",
        "reception": "전 영업점"
    }

## 지원하는 지자체 정보 수정 기능 API 개발

- 다음과 같은 로직으로 수행이 됩니다.

    1. updateDto를 통해 요청을 받습니다.
    2. 해당 dto의 컬럼을 검사하며 null이 아닌 컬럼에 대해서만 업데이트를 진행합니다.

### [Request]

    PUT /api/regionsupinfos HTTP/1.1
    Content-Type: application/x-www-form-urlencoded
    Authorization: Bearer 인증받은JWT

> 아래 파라미터를 입력할 수 있습니다.

| 키        | 설명                | 필수 |
|-----------|---------------------|------|
| region    | 수정하려는 지자체명 | o    |
| target    | 지원 대상           |      |
| usage     | 용도                |      |
| limit     | 지원한도            |      |
| rate      | 이차보전            |      |
| institute | 추천기관            |      |
| mgmt      | 관리점              |      |
| reception | 취급점              |      |

### [Response]

    {
        "region": "부천시",
        "target": "부천시 소재 중소기업으로서 부천시장의 추천을 받은 자",
        "usage": "운전 및 시설",
        "limit": "10억원 이내",
        "rate": "1.5%~2.5%",
        "institute": "부천시",
        "mgmt": "강서제주지역본부",
        "reception": "전 영업점"
    }

## 지원한도 컬럼에서 지원금액으로 내림차순 정렬(지원금액이 동일하면 이차보전 평균 비율이 적은 순서)로 특정 개수만 출력

- 지원한도를 받아 금액으로 변환해주는 LimitParser 구현

```java
    package kakaopay.utils;
    
    import kakaopay.exception.ParsingLimitException;
    
    public class LimitParser {
        private static final String RECOMMEND_REGEX = "추천금액\\s*이내";
        private static final String MILLION_REGEX = "[0-9]+백만원\\s*이내";
        private static final String BILLION_REGEX = "[0-9]+억원\\s*이내";
        private static final String NOT_NUMBER_REGEX = "[^0-9]";
        private static final Long MILLION = 1000000L;
        private static final Long BILLION = 100000000L;
    
    
        public static Long parse(String limitSupport) {
            if (limitSupport.matches(RECOMMEND_REGEX)) {
                return 0L;
            }
            if (limitSupport.matches(MILLION_REGEX)) {
                String limitPay = limitSupport.replaceAll(NOT_NUMBER_REGEX, "");
                return Long.parseLong(limitPay) * MILLION;
            }
            if (limitSupport.matches(BILLION_REGEX)) {
                String limitPay = limitSupport.replaceAll(NOT_NUMBER_REGEX, "");
                return Long.parseLong(limitPay) * BILLION;
            }
            throw new ParsingLimitException();
        }
    }
```

- 이차보전을 받아 금액을 반환해주는 RateParser 구현

```java
    package kakaopay.utils;
    
    import kakaopay.domain.Rate;
    import kakaopay.exception.ParsingRateException;
    
    public class RateParser {
        private static final String ALL_RATE = "대출이자\\s*전액";
        private static final String SINGLE_RATE_REGEX = "[0-9]+.?[0-9]*%?";
        private static final String RANGE_RATE_REGEX = "[0-9]+.?[0-9]*%?~[0-9]+.?[0-9]*%?";
    
        public static Rate parse(String rateInfo) {
            if (rateInfo.matches(ALL_RATE)) {
                return new Rate(rateInfo, 0);
            }
            if (rateInfo.matches(SINGLE_RATE_REGEX)) {
                double rate = Double.parseDouble(rateInfo.replace("%", ""));
                return new Rate(rateInfo, rate);
            }
            if (rateInfo.matches(RANGE_RATE_REGEX)) {
                String[] rates = rateInfo.replaceAll("%", "").split("~");
                double minRate = Double.parseDouble(rates[0]);
                double maxRate = Double.parseDouble(rates[1]);
    
                return new Rate(rateInfo, (minRate + maxRate) / 2);
            }
            throw new ParsingRateException();
        }
    }
```

- Jpa에서 제공하는 Paging을 이용하여 문제를 해결

```java
    @Transactional(readOnly = true)
        public List<RegionSupportInformation> findTopOf(int numberOfRegionSupInfos) {
            Sort sort = new Sort(Sort.Direction.DESC, "limitPay_pay");
            sort = sort.and(new Sort(Sort.Direction.ASC, "rate_averageRate"));
            Pageable pageable = PageRequest.of(0, numberOfRegionSupInfos, sort);
    
            return regionSupportInformationRepository.findAll(pageable).getContent();
        }
```

### [Request]

    GET /api/regionsupinfos/rank?k={numberOfInfos} HTTP/1.1
    Authorization: Bearer 인증받은JWT

> 아래 파라미터를 입력할 수 있습니다.

| 키   | 설명               | 필수 |
|------|--------------------|------|
| k | 출력 개수 |  o    |

### [Response]

    [
        {
            "region": "경기도"
        },
        {
            "region": "제주도"
        },
        {
            "region": "국토교통부"
        },
        {
            "region": "인천광역시"
        },
        {
            "region": "안양시"
        },
        {
            "region": "안산시"
        },
        {
            "region": "대구광역시"
        },
        {
            "region": "부천시"
        },
        {
            "region": "경상남도"
        },
        {
            "region": "춘천시"
        },
        {
            "region": "강원도"
        },
        {
            "region": "태백시"
        },
        {
            "region": "창원시"
        },
        {
            "region": "세종특별자치시"
        },
        {
            "region": "남양주시"
        },
        {
            "region": "의왕시"
        },
        {
            "region": "충청북도"
        },
        {
            "region": "성남시"
        },
        {
            "region": "평택시"
        },
        {
            "region": "중소기업진흥공단"
        }
    ]

## 이차보전 컬럼에서 보전 비율이 가장 작은 추천 기관명을 출력하는 API

- JPA의 `findFirstByOrderByRateAverageRateAsc()` 를 이용하여 문제 해결

### [Request]

    GET /api/regionsupinfos/min HTTP/1.1
    Authorization: Bearer 인증받은JWT

### [Response]

    {
        "region": "안산시"
    }

# 추가 구현기능(옵션)

## JWT를 이용한 인증 인가

- Spring Security를 사용하여 처리
- SingUp, Login은 Controller단에서 처리하며, refresh나 JWT에 대한 검증은 Spring Security의 별도 Filter에서 처리하도록 구성

## JwtConfig

- JWT발급에서 가장 중요한 signKey를 별도 yml파일로 관리(해당 미션에서는 git에 첨부하였습니다.)

### JwtFactory

- JwtConfig를 의존하고 있으며 JWT생성과 decode를 담당

## 회원 가입

### [Request]

    POST /api/signup HTTP/1.1

> 아래 파라미터를 입력할 수 있습니다.

| 키   | 설명               | 필수 |
|------|--------------------|------|
| userId | 아이디 |  o    |
| password | 패스워드 |  o    |

### [Response]

    {새로발급된 JWT}

## 로그인

### [Request]

    POST /api/login HTTP/1.1

> 아래 파라미터를 입력할 수 있습니다.

| 키   | 설명               | 필수 |
|------|--------------------|------|
| userId | 아이디 |  o    |
| password | 패스워드 |  o    |

### [Response]

    {새로발급된 JWT}

## 토큰 재발급

- Spring Security의 `JwtRefreshFilter`, `JwtRefreshProvider`에서 수행
- `JwtRefreshProvider` 에서 현재 넘어온 토큰의 유효성을 검증 후 새로운 토큰을 발급한다.

### [Request]

    GET /api/refresh HTTP/1.1
    Authorization: Bearer 인증받은JWT

### [Response]

    {새로발급된 JWT}

## Spring Security를 이용한 인가

- SecurityConfig를 통해 접근 제한 설정

```java
    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors().disable()
                    .csrf().disable();
            http
                    .authorizeRequests()
                    .antMatchers("/api/signup", "/api/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(jwtRefreshFilter(), JwtAuthenticationFilter.class);
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
```

- `JwtAuthenticationFilter`,  `JwtAuthenticationProvider`에서 토큰 유효성 검증 및 인가 수행

```java    
    @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            JwtPreAuthorizeToken jwtPreAuthorizeToken = (JwtPreAuthorizeToken) authentication;
            String token = (String) jwtPreAuthorizeToken.getPrincipal();
            DecodedJWT decode = jwtFactory.decode(token);
    
            String userId = decode.getClaim("userId").asString();
    
            return new JwtPostAuthorizeToken(userId, "ROLE_USER");
        }
        
