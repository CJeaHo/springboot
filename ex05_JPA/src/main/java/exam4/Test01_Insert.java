package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test01_Insert {

	public static void main(String[] args) {

		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
		
		try {
			em.getTransaction().begin(); // 지금 이 코드서 부터 
			Member4 user = new Member4("test@test", "홍길동", LocalDate.now());
			System.out.println("sql문 출력");
			
			em.persist(user); // .persist는 영속성으로 객체에 데이터를 입력(메모리에 insert해주는 부분)
			System.out.println("영속 컨텍스트에 반영");
			
			// 실제 DB에 insert
			em.getTransaction().commit(); // 이 코드까지 저장
			System.out.println("실제 DB에 sql문 처리");
			System.out.println("가입요청을 처리했습니다");
			
 		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); // try에서 오류나면 catch문으로 와서 rollback할 수 있도록
		} 
		em.close();
		emf.close();
		
	}

}
