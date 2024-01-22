package com.study.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
//@AllArgsConstructor하면 @Data로 인해 만들어진 기본 생성자가 사라지므로 @NoArgsConstructor를 다시 걸어줘야한다
@Builder
@Entity(name="memberjpa01") //= @table(name="") 
public class Member {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	@Column(name="create_date")
	private LocalDate createDate;
}
