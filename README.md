## 디스코드 전설 알리미
```
전설의 포켓몬이 등장하면 디스코드로 메시지를 전송합니다.
```
![image](https://user-images.githubusercontent.com/79464411/182388718-9ab070a2-4a25-4d5f-97b7-7ffe5ff25aec.png)
## 콘피그

```yaml
#디스코드 웹후크 주소를 입력해주세요
webhook: ""
#디스코드 웹후크의 이름
name: "전설의 포켓몬 출현 [ %pokemon% ]"
#디스코드 웹후크 메시지
message: "**%pokemon%**이(가) **%biome%** 바이옴에 스폰되었습니다!"

#타이틀 사용 여부 [true / false]
use_title: true

#타이틀 하단
subtitle: "§a바이옴 §0: §6%biome%"
#타이틀 메인
title: "§f< §b전설 §f> §e%pokemon% 등장§o!"
```
