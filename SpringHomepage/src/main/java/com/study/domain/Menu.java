package com.study.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Menu {
	
	@Id
	@SequenceGenerator(
			name="myMenuSEQ",
			sequenceName="Menu_SEQ",
			allocationSize=1
			)
	@GeneratedValue(generator = "myMenuSEQ")
	private Long id;
	private String restaurant;
	private String name;
	private Long price;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
	private Taste taste;
}
