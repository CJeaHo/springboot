package exam3;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember3")
public class Member3 {
	
	@Id
	@SequenceGenerator(
			name="mySequence01", // 시퀀스의 고유한 이름
			sequenceName="JpaMember3_seq", // 실제 DB에서의 시퀀스명
			initialValue=1, // 초기값
			allocationSize=1 // 1씩 증가
			)
	@GeneratedValue(generator="mySequence01")
	private long id;
	
	@Access(AccessType.FIELD) // 필드를 통해 데이터 접근(기본값)
	private String username;
	
	@Access(AccessType.PROPERTY) // get/set메소드를 통해 데이터 접근
	private String passowrd;
	
	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	@Transient // 영속 대상에서 제외(DB에 없으므로 제외)
	private String addr1;
	// transient private String addr1; // 지시자를 이용해도 됨

	public Member3() {

	}

	public Member3(String username, String passowrd) { // addr1은 DB에 제외시켰으므로 매개변수에 포함시키지 않는다
		this.username = username;
		this.passowrd = passowrd;
	}
	
	
}
