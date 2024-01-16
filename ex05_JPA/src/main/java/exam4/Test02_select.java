package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test02_select {

	public static void main(String[] args) {

		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
		
		/*
		검색시 find() 메소드 사용
		get 메소드를 해줘야한다
		- 아무 지정을 하지 않으면 @Id가 붙은 primary key를 이용해 검색
		*/
		Member4 user = em.find(Member4.class, "test@test");
		System.out.println(user);
		
		if(user != null) {
			System.out.println("이름: " + user.getName());
			System.out.printf("생성일자: %ty-%<tm-%<td\n", user.getCreateDate());
			/*'
			 * t' or 'T': 날짜, 시간(날짜 및 시간 서식 문자 앞에 지정)
				ex) %tH, %tM, %tS, ...
				'Y': 년(4자리)
				'y': 년(2자리)
				'm': 월(01~12)
				'd': 일(01~31)
				'e': 일(1~31)
				'H': 24시간 형식의 시(00~23)
				'I': 12시간 형식의 시(01~12)
				'M': 분(00~60)
				'S': 초(00~60)
				*/
		} else {
			System.out.println("존재하지 않습니다");
		}
	}

}
