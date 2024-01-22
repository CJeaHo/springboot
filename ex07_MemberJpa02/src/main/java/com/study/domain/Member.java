package com.study.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
//@AllArgsConstructor하면 @Data로 인해 만들어진 기본 생성자가 사라지므로 @NoArgsConstructor를 다시 걸어줘야한다
//@Builder
@Entity(name="memberjpa02") //= @table(name="") 
public class Member {
	@Id
	@SequenceGenerator(
			name="mySequence01", // 시퀀스의 고유한 이름
			sequenceName="memberjpa02_seq", // 실제 DB에서의 시퀀스명
			initialValue=1, // 초기값
			allocationSize=1 // 1씩 증가
			)
	@GeneratedValue(generator="mySequence01")
	private Long id;
	private String name;
	private String email;
	
	public Member(String email, String name) {

		this.email = email;
		this.name = name;
	}
}
