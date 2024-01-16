package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test04_delete {

	public static void main(String[] args) {

		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
//		EntityTransaction transaction = em.getTransaction();
		
		try {
			em.getTransaction().begin(); // 지금 이 코드서 부터 
			
			Member4 user = em.find(Member4.class, "test@test"); // update할려면 find부터 해야한다 영속성안에있는 테이블을 먼저 찾아야함
			if(user == null) {
				System.out.println("존재하지 않습니다");
				return;
			} 
			em.remove(user); // DB가 아닌 영속성 컨테이너에서 삭제
			
			// 실제 DB에 delete
			em.getTransaction().commit(); // 이 코드까지 저장
			System.out.println("삭제되었습니다");
			
 		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); // try에서 오류나면 catch문으로 와서 rollback할 수 있도록
		} 
		em.close();
		emf.close();
	}

}
