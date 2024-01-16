package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test03_update {

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
			user.changeName("이나은"); // 자바객체를 통해 DB가 아닌 영속성 컨테이너에서 변경
						
			
//			이렇게 하면 "이름 변경"은 뜨나 실제 DB에 반영이 안된다 => find 메소드를 안썼기 때문
//			Member4 user = new Member4();
//			user.changeName("이가은");
			
			// 실제 DB에 update
			em.getTransaction().commit(); // 이 코드까지 저장
			System.out.println("이름 변경");
			
 		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); // try에서 오류나면 catch문으로 와서 rollback할 수 있도록
		} 
		em.close();
		emf.close();
	}

}
