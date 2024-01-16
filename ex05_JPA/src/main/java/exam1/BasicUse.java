package exam1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BasicUse {

	public static void main(String[] args) {
		
		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin(); // 지금 이 코드서 부터 
			Member1 user = new Member1("홍길동", LocalDate.now());
			entityManager.persist(user); // .persist는 영속성으로 객체에 데이터를 입력(메모리에 insert해주는 부분)
			
			// 실제 DB에 insert
			transaction.commit(); // 이 코드까지 저장
 		} catch(Exception e) {
			e.printStackTrace();
			transaction.rollback(); // try에서 오류나면 catch문으로 와서 rollback할 수 있도록
		} finally {
			entityManager.close();
		}
		emf.close();
	}
}
