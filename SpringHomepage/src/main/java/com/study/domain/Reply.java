package com.study.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reply {
	@Id
	@SequenceGenerator (
			name = "myReplySEQ",
			sequenceName = "Reply_SEQ",
			allocationSize = 1
		)
		@GeneratedValue(generator = "myReplySEQ")
	private Long rNo;
	@NonNull
	private String rWriter;
	@NonNull
	private String rContent;
	
	private Long refBno;
	
	@CreatedDate
	private LocalDate rCreateDate;
	
	@LastModifiedDate
	private LocalDate rUpdateDate;
}
