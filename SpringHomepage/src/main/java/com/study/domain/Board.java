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
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // 데이터베이스 테이블의 변경 시간, 변경한 사용자 등을 기록 즉, @CreatedDate 및 @LastModifiedDate과 같이 사용
public class Board {
	@Id
	@SequenceGenerator (
		name = "myBoardSEQ",
		sequenceName = "Board_SEQ",
		allocationSize = 1
	)
	@GeneratedValue(generator = "myBoardSEQ")
	private Long bNo;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;
	// insert가 안되고 디폴트값을 0으로
	@Column(insertable = false, columnDefinition = "NUMBER DEFAULT 0") 
	private Long count;
	
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
}
