package com.study.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	// jpql쿼리: from뒤에는 영속성에 있는 엔티티명(DB테이블이 아니어서 반드시 대문자로)
	@Query("select m from JPAPAGING m where m.name like :name1 order by m.id desc")
	// :name1은 @param으로 넘어온 이름과 동일하게
	List<Member> findMembers(@Param("name1") String name2);	
	// 엔티티의 필드를 가지고 findBy필드명Like 처럼 존재하는 함수를 사용하면 jpql을 안써도 되지만 
	//없는 함수를 쓰면 query를 사용해야한다 그러면 각 객체에 있는 필드를 가지고와야할때 @param을 사용하는것
	
	@Query("select m from JPAPAGING m where m.name like :name1")
	List<Member> findMembers(@Param("name1") String name2, Sort sort);

	@Query("select m from JPAPAGING m where m.name like :name1")
	Page<Member> findMembers(@Param("name1") String name2, Pageable pageable);

	// 일반 sql쿼리
	@Query(value="select * from jpapaging where name like :name1 order by id desc", nativeQuery=true) // nativeQuery=false가 기본값으로 안써주면 jpql형식으로 간주
	List<Member> findMembersNative(@Param("name1") String name2);	
	

}
