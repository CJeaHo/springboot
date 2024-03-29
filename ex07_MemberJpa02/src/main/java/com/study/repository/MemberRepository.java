package com.study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByEmail(String email);

	Optional<Member> findByName(String name);

	List<Member> findByNameLike(String name);

	List<Member> findByNameLikeOrderByNameDesc(String name); // 이름 내림차순
//	List<Member> findByNameLikeOrderByNameAscEmailDesc(String name); // 이름 오름차순하고 이메일 내림차순
	
	List<Member> findByNameLike(String name, Sort sort); // 이름 내림차순하기 위한
	
	

}
