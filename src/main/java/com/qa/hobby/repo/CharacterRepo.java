package com.qa.hobby.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hobby.domain.Characters;
import com.qa.hobby.domain.Player;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Long>{

	Character save(Characters character);

}
