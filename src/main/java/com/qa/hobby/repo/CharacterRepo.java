package com.qa.hobby.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobby.domain.Characters;

@Repository
public interface CharacterRepo extends JpaRepository<Characters, Long>{

	List<Characters> findByPlayerId(Long id);

}
