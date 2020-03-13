# Give me a mask

주소를 검색해서 해당 주소에서 가까운 순으로 마스크 판매점을 보여줍니다.
서비스되는 재고 현황 정보는 데이터 처리 및 전송으로 인해 실제 현장 판매처의 현황과 5분~10분 정도의 차이가 있습니다.

https://give-me-a-mask.herokuapp.com/

## 사용 예
1. 우편번호 찾기 버튼 클릭
2. 주소 검색 후 선택
3. 결과 확인

![image](https://user-images.githubusercontent.com/20533433/76606906-6daf0180-6556-11ea-814d-2d7da4466c59.png)

## 업데이트 내역

* 1.0.0
    * 첫 출시
    
 ## 정보
 
1. 이번 주는 서비스의 안정적 운영을 지켜보기 위하여 베타서비스 입니다.
2. 서비스되는 재고 현황 정보는 데이터 처리 및 전송으로 인해 실제 현장 판매처의 현황과 5분~10분 정도의 차이가 있습니다.
3. 일부 약국에서는 번호표 배부 후 판매하는 약국도 있어 서비스되는 정보가 번호표 배부 현황을 반영하지는 못하고 있습니다.
4. 마스크 현황 정보는 성인용 마스크를 대상으로 합니다.
5. 마스크 재고 현황은 국민들의 혼돈을 방지하기 위해 4단계 구간 정보로 제공됩니다.
- 100개 이상 : 충분 * 녹색
- 100개 미만(99개~30개) : 보통 * 노랑색
- 30개 미만(29개~2개) : 부족 * 빨강색
- 1개~0개 : 없음 또는 판매전 : * 회색
6. 마스크 판매정보 수집DB 안정화 등 작업으로 인해 매일 갱신되는 정보는 08:00~23:00까지 운영됩니다.
7. 식약처 공적마스크 구매 안내 : http://blog.naver.com/kfdazzang/221839489769
8. 데이터 출처는 심평원‧정보화진흥원(공공데이터포털) 데이터 공개 문의는 한국정보화진흥원 : maskdata@nia.or.kr
9. 어려운 환경에서도 일선에서 공헌해 주시는 약사님, 우체국 종사자분, 하나로 마트(예정) 분들께도 감사와 응원 드립니다. 

