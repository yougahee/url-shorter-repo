# gh_url_shortener

# 프로젝트 소개
- URL을 입력하면, URL의 길이를 줄여주고 Redirect까지 할 수 있도록 하는 간단한 웹사이트
- 참고 웹사이트 : https://bitly.com/

## Front
- Vue.js
- axios를 통해 통신

## Server
- Spring Boot

</br>

# 구현요소
## Input Url
1. 아무것도 작성하지 않고 Search를 눌렀을 경우, Alert가 뜨도록 구현했다. 
<img src = "https://github.com/yougahee/url-shorter-repo/blob/master/img/main_page.PNG">

## Shorter Url 
1. 같은 URL은 같은 결과값을 갖는다.

<img src = "https://github.com/yougahee/url-shorter-repo/blob/master/img/shortUrl.PNG">

## 유효하지 않은 URL일 경우
1. Bad Request로 Alert 처리

<img src = "https://github.com/yougahee/url-shorter-repo/blob/master/img/BadRequest.PNG">

## Redirect 버튼
1. 해당 URL이 가리키는 곳으로 Redirect한다.

<img src = "https://github.com/yougahee/url-shorter-repo/blob/master/img/shortUrl.PNG">


