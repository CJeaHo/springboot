package com.study.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* @Data에 해당되는 것
@Getter @Setter
@RequiredArgsConstructor // @NunNull설정해 놓은 필드값만 포함한 생성자(NonNull설정된 필드를 매개변수로)
@ToString
@EqualsAndHashCode
*/

@Data // @RequiredArgsConstructor 생성자만 생성됨, 만약 NonNull이 하나도 없다면 필드값을 포함하지 않은 생성자가 생성

/* @Data에 해당안되는 것들
@NoArgsConstructor // 필드값을 포함하지 않은 기본 생성자(매개변수 x)
@AllArgsConstructor // 필드값을 모두 포함한 생성자(매개변수 o)
*/

@NoArgsConstructor
public class Board {
	
	@NonNull // null값을 받지않는다
	private int no;
	private String title;
	private String writer;
	
	@NonNull // null값이 들어오면 오류
	private String content;
}
