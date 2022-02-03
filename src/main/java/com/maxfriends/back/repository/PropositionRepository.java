package com.maxfriends.back.repository;

import com.maxfriends.back.entity.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
}
