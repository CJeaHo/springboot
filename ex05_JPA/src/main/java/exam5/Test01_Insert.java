package exam5;

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
		
		try {
			em.getTransaction().begin();
			Member5 user;
			user = new Member5("test1@test", "일순신", LocalDate.now());
			em.persist(user);
			user = new Member5("test2@test", "이순신", LocalDate.now());
			em.persist(user);
			user = new Member5("test3@test", "삼순신", LocalDate.now());
			em.persist(user);

			em.getTransaction().commit();
			System.out.println("단체가입이 되었습니다");
			
 		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} 
		em.close();
		emf.close();
		
	}

}
