## 스터디
- 강의를 보면서 모르는 내용을 정리하는 파일

## GOAL
1. @Lob
2. CLOB, BLOB 차이
3. @Basic(fetch = FetchType.EAGER)

<br>

### 1. @Lob
- Large Object의 줄임말로써 보통 String으로 선언한 데이터는 DB에 매핑될때 varchar(255)로 매핑된다.
- 만약 대용량의 데이터의 양을 저장할 경우 String으로는 부족하다.
- Lob을 사용함으로써 최대 4기가 대용량 데이터를 저장할 수 있으며 일반 텍스트가 되거나 바이너리 데이터로 관리될 수 있다.
- @Lob annotation은  Clob 이나 Blob과 같이 데이터의 크기가 큰 자료형을 지정할 때 사용.
- 이 annotation을 필드에 붙이게 되면 자동으로 칼럼을 Blob으로 처리한다.
- LOB을 사용하는 이유는 무엇일까?
    - 대용량 데이터 저장과 일관성이다.
    - 데이터 용량이 크기 때문에 어떤 데이터는 DB에 어떤 DB는 OS차원에서 저장하면 일관성이 깨지기 때문에 DB에서 <br>
    대용량 데이터를 저장할 수 있도록 지원

<br>
  
### 2 CLOB vs BLOB
- CLOB : Character LOBs
    - 대용량 텍스트
- BLOB : Binary LOBs
    - Image, Sound, Video 등
 
<br>
 
### 3. @Basic(fetch = FetchType.EAGER)
- 인스턴스 필드를 언제 가져올지를 결정하는 annotation.
- @Lob annotation으로 지정한 필드는 보통 그 크기가 다른 필드보다 크기 때문에 사용하지도 않는 칼럼을 매번 테이블에서 꺼내오는 것은 <br>
 자원을 낭비하고 시스템의 성능을 떨어 뜨리게 한다. Clob 이나 Blob과 같이 데이터의 크기가 큰 칼럼의 정보를 가져오는 시점을 지정할 수 있게 해준다.
- @Basic annotaition은 fetch와 optional 두 가지 속성이 있다.
    - fetch : 언제 가져올지
        - EAGER : 해당 필드를 사용하던 안하던 무조건 컬럼의 값을 가져온다.
        - LAZY : 해당 필드를 사용하고자 하는 시점에 가져온다.
    - optinal : null이 가능한지 옵션으로 적용이 가능하다.
        - false : null을 허용하지 않는다. == Not Null
        - true : null을 허용
                
  
