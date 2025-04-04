### Members

| **Method** | **Endpoint** | **Description** | **Parameters** | **Request Body** | **Response** | **Status Code** |
| --- | --- | --- | --- | --- | --- | --- |
| POST | /members | 회원 생성 | - | { "name": string, "email": string, "password": string } | { "id": long, "name": string, "email": string, "password": string, "createdAt": string, "updatedAt": string} | 200 OK |
| GET | /members/{id} | 회원 조회(단건) | Path: id | - | { "id": long, "name": string, "email": string, "password": string, "createdAt": string, "updatedAt": string} | 200 OK |
| PATCH | /members/{id} | 비밀번호수정 | Path: id | { "oldPassword": string, "newPassword": string} |  | 200 OK |
| DELETE | /members/{id} | 회원 삭제 | Path : id ,Query : password |  |  | 200 OK |

### Schedule

| **Method** | **Endpoint** | **Description** | **Parameters** | **Request Body** | **Response** | **Status Code** |
| --- | --- | --- | --- | --- | --- | --- |
| POST | /schedule | 일정 생성 | - | { "task": string, "place": string,"password": string } | {"id": long, "task": string, "place": string,"createdAt": string, "updatedAt": string } | 200 OK |
| GET | /schedule/{id} | 일정 조회(전체) | Query :updatedDatememberName,memberId | - | {"id": long, "task": string, "place": string,"createdAt": string, "updatedAt": string } | 200 OK |
| GET | /schedule/{id} | 일정 조회(단건) | Path: id |  | {"id": long, "task": string, "place": string,"createdAt": string, "updatedAt": string } | 200 OK |
| DELETE | /schedule/{id} | 회원 삭제 | Path : id ,Query : memberName, password |  |  | 200 OK |

```sql
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL
);

CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          member_id BIGINT NOT NULL,
                          task VARCHAR(255) NOT NULL,
                          place VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL,
                          CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES MEMBER (id)
);
```


![image](https://github.com/user-attachments/assets/3a3d4b3a-5fb6-45dd-9653-efcca2255248)
