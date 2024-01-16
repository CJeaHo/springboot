package exam5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Member5TableCreate {

	public static void main(String[] args) {

		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();
	}

}
