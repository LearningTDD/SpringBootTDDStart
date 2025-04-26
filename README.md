# 스프링 부트 TDD - 입문부터 실전까지 정확하게

"스프링 부트 TDD - 입문부터 실전까지 정확하게" 인프런 강의의 실습 코드 저장소입니다.

## 응용프로그램 빌드

```bash
./gradlew build
```

## 응용프로그램 실행

```bash
./gradlew bootRun
```

## API 목록

### 판매자 회원 가입

요청
- 메서드: POST
- 경로: /seller/signUp
- 헤더
  ```
  Content-Type: application/json
  ```
- 본문
  ```
  CreateSellerCommand {
    email: string,
    username: string,
    password: string
  }
  ```
- curl 명령 예시
  ```bash
  curl -i -X POST 'http://localhost:8080/seller/signUp' \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "seller1@example.com",
    "username": "seller1",
    "password": "seller1-password"
  }'
  ```

성공 응답
- 상태코드: 204 No Content

정책
- 이메일 주소는 유일해야 한다
- 사용자이름은 유일해야 한다
- 사용자이름은 3자 이상의 영문자, 숫자, 하이픈, 밑줄 문자로 구성되어야 한다
- 비밀번호는 8자 이상의 문자로 구성되어야 한다

테스트
- [x] 올바르게 요청하면 204 No Content 상태코드를 반환한다
- [x] email 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 올바른 형식을 따르면 204 No Content 상태코드를 반환한다
- [x] password 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] email 속성에 이미 존재하는 이메일 주소가 지정되면 400 Bad Request 상태코드를 반환한다
- [x] username 속성에 이미 존재하는 사용자이름이 지정되면 400 Bad Request 상태코드를 반환한다
- [x] 비밀번호를 올바르게 암호화한다

### 판매자 접근 토큰 발행

요청
- 메서드: POST
- 경로: /seller/issueToken
- 본문
  ```
  IssueSellerToken {
    email: string,
    password: string
  }
  ```
- curl 명령 예시
  ```bash
  curl -i -X POST 'http://localhost:8080/seller/issueToken' \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "seller1@example.com",
    "password": "seller1-password"
  }'
  ```

성공 응답
- 상태코드: 200 OK

  본문
  ```
  AccessTokenCarrier {
    accessToken: string
  }
  ```

테스트
- [x] 올바르게 요청하면 200 OK 상태코드를 반환한다
- [x] 올바르게 요청하면 접근 토큰을 반환한다
- [x] 접근 토큰은 JWT 형식을 따른다
- [x] 존재하지 않는 이메일 주소가 사용되면 400 Bad Request 상태코드를 반환한다
- [x] 잘못된 비밀번호가 사용되면 400 Bad Request 상태코드를 반환한다

### 구매자 회원 가입

요청
- 메서드: POST
- 경로: /shopper/signUp
- 본문
  ```
  CreateShopperCommand {
    email: string,
    username: string,
    password: string
  }
  ```
- curl 명령 예시
  ```bash
  curl -i -X POST 'http://localhost:8080/shopper/signUp' \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "shopper1@example.com",
    "username": "shopper1",
    "password": "shopper1-password"
  }'
  ```

성공 응답
- 상태코드: 204 No Content

정책
- 이메일 주소는 유일해야 한다
- 사용자이름은 유일해야 한다
- 사용자이름은 3자 이상의 영문자, 숫자, 하이픈, 밑줄 문자로 구성되어야 한다
- 비밀번호는 8자 이상의 문자로 구성되어야 한다

테스트
- [x] 올바르게 요청하면 204 No Content 상태코드를 반환한다
- [x] email 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 올바른 형식을 따르면 204 No Content 상태코드를 반환한다
- [x] password 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] email 속성에 이미 존재하는 이메일 주소가 지정되면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 이미 존재하는 사용자이름이 지정되면 400 Bad Request 상태코드를 반환한다
- [x] 비밀번호를 올바르게 암호화한다

### 구매자 접근 토큰 발행

요청
- 메서드: POST
- 경로: /shopper/issueToken
- 본문
  ```
  IssueShopperToken {
    email: string,
    password: string
  }
  ```
- curl 명령 예시
  ```bash
  curl -i -X POST 'http://localhost:8080/shopper/issueToken' \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "shopper1@example.com",
    "password": "shopper1-password"
  }'
  ```

성공 응답
- 상태코드: 200 OK
- 본문
  ```
  AccessTokenCarrier {
    accessToken: string
  }
  ```

테스트
- [x] 올바르게 요청하면 200 OK 상태코드와 접근 토큰을 반환한다
- [x] 접근 토큰은 JWT 형식을 따른다
- [x] 존재하지 않는 이메일 주소가 사용되면 400 Bad Request 상태코드를 반환한다
- [x] 잘못된 비밀번호가 사용되면 400 Bad Request 상태코드를 반환한다

### 판매자 정보 조회

요청
- 메서드: GET
- 경로: /seller/me
- 헤더
  ```
  Authorization: Bearer {accessToken}
  ```
- curl 명령 예시
  ```bash
  curl -i -X GET 'http://localhost:8080/seller/me' \
  -H 'Authorization: Bearer {accessToken}'
  ```

성공 응답
- 상태코드: 200 OK
- 본문
  ```
  SellerMeView {
    id: string(UUID),
    email: string,
    username: string
  }
  ```

테스트
- [x] 올바르게 요청하면 200 OK 상태코드를 반환한다
- [x] 접근 토큰을 사용하지 않으면 401 Unauthorized 상태코드를 반환한다
- [x] 서로 다른 판매자의 식별자는 서로 다르다
- [x] 같은 판매자의 식별자는 항상 같다
- [x] 판매자의 기본 정보가 올바르게 설정된다

### 구매자 정보 조회

요청
- 메서드: GET
- 경로: /shopper/me
- 헤더
  ```
  Authorization: Bearer {accessToken}
  ```
- curl 명령 예시
  ```bash
  curl -i -X GET 'http://localhost:8080/shopper/me' \
  -H 'Authorization: Bearer {accessToken}'
  ```

성공 응답
- 상태코드: 200 OK
- 본문
  ```
  ShopperMeView {
    id: string(UUID),
    email: string,
    username: string
  }
  ```

테스트
- [x] 올바르게 요청하면 200 OK 상태코드를 반환한다
- [x] 접근 토큰을 사용하지 않으면 401 Unauthorized 상태코드를 반환한다
- [x] 서로 다른 구매자의 식별자는 서로 다르다
- [x] 같은 구매자의 식별자는 항상 같다
- [x] 구매자의 기본 정보가 올바르게 설정된다

### 판매자 상품 등록

요청
- 메서드: POST
- 경로: /seller/products
- 헤더
  ```
  Authorization: Bearer {accessToken}
  ```
- 본문
  ```
  RegisterProductCommand {
    name: string,
    imageUri: string,
    description: string,
    priceAmount: number,
    stockQuantity: number
  }
  ```
- curl 명령 예시
  ```bash
  curl -i -X POST 'http://localhost:8080/seller/products' \
  -H 'Authorization: Bearer {accessToken}' \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Product 1",
    "imageUri": "http://example.com/product1.jpg",
    "description": "Product 1 description",
    "priceAmount": 1000,
    "stockQuantity": 10
  }'
  ```

성공 응답
- 상태코드: 201 Created
- 헤더
  ```
  Location: /seller/products/{id}
  ```

테스트
- [x] 올바르게 요청하면 201 Created 상태코드를 반환한다
- [x] 판매자가 아닌 사용자의 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다
- [x] imageUri 속성이 URI 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] 올바르게 요청하면 등록된 상품 정보에 접근하는 Location 헤더를 반환한다

### 판매자 상품 조회

요청
- 메서드: GET
- 경로: /seller/products/{id}
- 헤더
  ```
  Authorization: Bearer {accessToken}
  ```
- curl 명령 예시
  ```bash
  curl -i -X GET 'http://localhost:8080/seller/products/{id}' \
  -H 'Authorization: Bearer {accessToken}'
  ```

성공 응답
- 상태코드: 200 OK
- 본문
  ```
  SellerProductView {
    id: string(UUID),
    name: string,
    imageUri: string,
    description: string,
    priceAmount: number,
    stockQuantity: number,
    registeredTimeUtc: string(YYYY-MM-DDThh:mm:ss.sss)
  }
  ```

테스트
- [x] 올바르게 요청하면 200 OK 상태코드를 반환한다
- [x] 판매자가 아닌 사용자의 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다
- [x] 존재하지 않는 상품 식별자를 사용하면 404 Not Found 상태코드를 반환한다
- [x] 다른 판매자가 등록한 상품 식별자를 사용하면 404 Not Found 상태코드를 반환한다
- [ ] 상품 식별자를 올바르게 반환한다
- [ ] 상품 정보를 올바르게 반환한다
- [ ] 상품 등록 시각을 올바르게 반환한다
