package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

//	void findAllByRefbnoOrderByRnoDesc(Long bNo);


}
