package exam5;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test02_TypedQuery {

	public static void main(String[] args) {
		
		//필수
		//<persistence-unit name="jpaEx01" transaction-type="RESOURCE_LOCAL">의 "jpaEx01"
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaEx01");  
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			/*
			 * TypedQuery클래스: sql문을 직접 작성하고자 할 때(영속성에 없는 sql문을 작성)
			 * - 일반 sql문이 아니고 영속성의 객체에 넣을 구문 jpa문법
			 * 	select * => 사용불가(사용할려면 테이블에 별칭을 넣어서 사용)
			 */// 기존 sql문: select * from Member5 order by name (x)
			 TypedQuery<Member5> query = em.createQuery("select m from Member5 m order by m.name", Member5.class);
	
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
