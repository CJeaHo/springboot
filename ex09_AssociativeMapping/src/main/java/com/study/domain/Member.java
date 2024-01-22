package com.study.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Entity(name="MEMBERAM") //= @table(name="")
@EntityListeners(AuditingEntityListener.class)
public class Member {
	@Id
	private String id;
	private String pw;
	private String name;
	private String email;
	
	// entity에서 생성되는 시간을 자동으로 생성해줌
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	// entity가 수정될 때 수정시간을 넣어줌
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	/* 
	 * @CreatedDate와 @LastModifiedDate를 사용할 때 반드시 
	 * Entity에 @EntityListeners(AuditingEntityListener.class) 어노테이션 달기
	 * main()메소드가 있는 클래스에도 @EnableJpaAuditing 어노테이션 달기
	 * (이 프로젝트에서는 Ex09AssociativeMappingApplication.java)
	 */ 
	
	public Member(String email, String name) {

		this.email = email;
		this.name = name;
	}
}
