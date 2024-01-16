package exam5;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test04_ParameterLike {

	public static void main(String[] args) {
		
		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			 TypedQuery<Member5> query = em.createQuery(
					"select m from Member5 m "+
					"where m.email like :email " + 
					"order by m.name"
					, Member5.class
					).setParameter("email", "%t%");
	
			 List<Member5> result = query.getResultList(); //결과를 리스트 형태로 얻어옴
			 
			em.getTransaction().commit();
			
			if(result.isEmpty()) {
				System.out.println("테이블이 비어있습니다");
			} else {
				result.forEach(user -> System.out.printf("| %s | %s | %tY-%<tm-%<td |\n",
														user.getEmail(), user.getName(), user.getCreateDate()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} 
		em.close();
		emf.close();
	}
}
