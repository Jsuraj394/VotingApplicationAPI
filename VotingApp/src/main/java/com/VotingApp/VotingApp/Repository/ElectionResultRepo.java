package com.VotingApp.VotingApp.Repository;

import com.VotingApp.VotingApp.Entity.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionResultRepo extends JpaRepository<ElectionResult,Long> {
    Optional<ElectionResult> findByElectionName(String electionName);

}
