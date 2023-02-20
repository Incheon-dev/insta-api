# insta-api
> Java Spring Boot를 활용하여 사람들이 많이 사용하고 있는 인스타그램을 클론하여 제작합니다. 개발자의 관점으로써는 객체지향적으로, 유저의 관점으로써는 UX가 좋게 남도록 설계하고 구성합니다. 프로젝트는 마무리되지 않고 지속적으로 업데이트될 예정입니다.

## 목차
1. [version](#1-version)</br>
2. [uri](#2-uri)</br>
3. [erd](#3-erd)</br>
4. [notion](#4-notion)</br>
5. [result]($5-result)</br>

## 1. version
<img src="https://img.shields.io/badge/Java 11-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot 2.5.5-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/Gradle 7.3.3-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=MariaDB&logoColor=white"> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white"> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"> </br>

## 2. uri
|domain|description|http protocol|
|:------:|:-----------:|:-------------:|
|/api/account/sign-up|회원가입|POST|
|/api/account/login|로그인|POST|
|/api/account/verify?email={email}|이메일 확인|GET|
|/api/account/verify|이메일 확인 인증번호 검증|POST|
|/api/account?email={email}|이메일 중복 검증|GET|
|/api/account/find|이메일 찾기|GET|
|/api/account|새로운 비밀번호 입력|PUT|
|/api/user/uploader|이미지 등록|POST|
|/api/user/search?email={email}|유저 검색|GET|
|/api/user/block?email={email}|유저 차단|POST|
|/api/user/block/list|유저 차단 목록|POST|
|/api/user/unblock?email={email}|유저 차단 해제|DELETE|
|/api/user/follow?email={email}|유저 팔로우|POST|
|/api/user/unfollow?email={email}|유저 언팔로우|DELETE|
|/api/user?userId={userId}|유저 목록 조회|GET|
|/api/user/post|글 등록|POST|
|/api/user/posts|전체 글 조회|GET|
|/api/user/posts/{email}|특정 유저 전체 글 조회|GET|
|/api/user/post/{id}|특정 글 조회|GET|
|/api/user/post|글 수정|PATCH|
|/api/user/post/{postId}|글 삭제|PATCH|
|/api/user/post/{postId}/comment|댓글 달기|POST|
|/api/user/post/{postId}/comment|댓글 조회|GET|
|/api/user/post/{postId}/comment|댓글 수정|PATCH|
|/api/user/post/{postId}/comment/{commmentId}|댓글 삭제|PATCH|
|/api/user/post/{postId}|게시글 좋아요|POST|
|/api/user/post/{postId}/comment/{commentId}|댓글 좋아요|POST|


</br>

## 3. erd
<img src="https://incheon-dev.s3.ap-northeast-2.amazonaws.com/personal/erd.PNG"/>
</br>

## 4. notion
[Notion Link](https://fan-yuzu-49b.notion.site/Instagram-Clone-b6d81942158442c596daa9dfe120c0b1)
</br>

## 5. result
[Result](https://fan-yuzu-49b.notion.site/fb1199c472f246f1bbc6a65b13e724c1)
</br>
