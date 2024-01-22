package com.study.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor // @Nonnull이 하나라도 있으면 무조건 달자
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {
	@Id
	private String id;
	@NonNull // db에 넘어가기전에 예외 // @Column(nullable=false) db에 넘어가고 나서 예외
	private String password;
	@NonNull
	private String name;
	private String email;
	private LocalDate birthday; // LocalDate: yyyy-MM-dd 형식으로 작성하기
	
	
	private String gender;
	private String phone;
	private String address;
	
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	/* 
	 * @CreatedDate와 @LastModifiedDate를 사용할 때 반드시 
	 * Entity에 @EntityListeners(AuditingEntityListener.class) 어노테이션 달기
	 * main()메소드가 있는 클래스에도 @EnableJpaAuditing 어노테이션 달기
	 * (이 프로젝트에서는 Ex09AssociativeMappingApplication.java)
	 */ 
}
