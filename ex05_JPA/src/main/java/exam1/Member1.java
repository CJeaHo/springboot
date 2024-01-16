package exam1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity //필수
public class Member1 {

	@Id //필수(primary key), jakarta로 import
	@GeneratedValue // 자동증가, 시퀀스 사용하는 것과 동일
	private Long id; // int로 했을 시 null값이들어오면 0으로 오류가 안뜨지만 Long으로 하면 오류가 뜨기때문에 Long으로 해줘야한다
	private String username;
	
	/*
	@Column에서 사용하는 속성
	 -name: 컬럼이름 지정(생략시 변수명과 동일하게 매핑)
	 -unique: unique제약조건 추가(기본값 false)
	 -nullable: null상태 허용 여부(기본값 false)
	 -insertable: insert할 때 이 컬럼을 포함할 것인지 결정(기본값 true)
	 -updateable: update할 때 이 컬럼을 포함할 것인지 결정(기본값 true)
	 -length: 문자열 타입의 컬럼의 길이 지정(기본값 255=VARCHAR2(255))
	 -columnDefinition: 컬럼에 대한 ddl문을 직접 기술할 수 있음 ex) @@Column(columnDefinition="varchar2(200) default 'Y'")
	 
	 
	 */
	@Column(name="create_date") // 보통 DB에 넣을 때는 create_date로 넣어준다. 대소문자 안가리기때문에
	private LocalDate createDate;  
	/*
	LocalDateTime
	
	@Temporal(TemporalType.TIMESPAMT)
	private
	*/

	// bean 생성자 필수
	public Member1() {
	
	}
	
	public Member1(String username, LocalDate createDate) {

		this.username = username;
		this.createDate = createDate;
	}
}	
