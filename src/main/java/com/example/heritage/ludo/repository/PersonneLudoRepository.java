package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.PersonneLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneLudoRepository extends JpaRepository<PersonneLudo, Long> {
    
    Optional<PersonneLudo> findByType(String type);
}
