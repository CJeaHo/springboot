package com.study.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name="BOARDAM")
@EntityListeners(AuditingEntityListener.class)
public class Board {
	@Id
	@GeneratedValue
	private Long bno;
	private String title;
	private String content;
	
	@ManyToOne // N:1 관계
	@JoinColumn(name="writer")
	private Member member; // Member객체의 member를 writer라는 이름으로 사용하겠다
	
	
}
