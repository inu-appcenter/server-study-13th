# 2주차 과제

---

## 회원 TO-DO List API 구현하기

**Dependencies**

- Spring DevTools, Lombok, Spring Web, Spring Data JPA, H2 DB

![2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%202eff876d5f42434f83880f6e2cac3a41/Untitled.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%202eff876d5f42434f83880f6e2cac3a41/Untitled.png)

- 회원은 N개의 TODO를 가질 수 있다.
- 회원 → TODO 도 조회가 가능해야하고 TODO → 회원의 조회도 가능해야 한다.

    → 스터디 했던 팀 - 회원 관계를 회원 - TODO에 대입해서 생각하면 편하다. ⇒ 회원 1 - TODO N

### 요구사항

- 회원 생성 기능
    - 회원 이메일 중복 체크 및 에러처리
- 회원 수정 기능
    - 회원의 나이 및 이름만 변경
- 회원 리스트 조회 기능 - 회원만
- 회원 단건 조회
    - **회원의 TODO 리스트까지 같이 조회 ( 일대다 조인 )**
- todo 생성 기능
    - 어떤 회원의 TODO인지 구별이 가능해야 한다

        **→ TODO에 외래키로 회원의 Id 가 필요**

- todo 수정 기능 - 완료여부만 변경
- todo 삭제 기능
- **todo 를 식별자를 이용하여 조회 기능 ( 회원까지 같이 조회 다대일** )
- **JPA Auditing 기능 사용하기**

[회원의 구성](https://www.notion.so/3ee4c7655fb547d38c507e17fafb5c26)

[To-Do 의 구성](https://www.notion.so/42c3cece39db4048a9b18bbc7af50496)

[API](https://www.notion.so/37582d52be26471992d3473a09bf7790)