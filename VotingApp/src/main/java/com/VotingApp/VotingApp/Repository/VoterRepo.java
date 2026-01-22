package com.VotingApp.VotingApp.Repository;

import com.VotingApp.VotingApp.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepo extends JpaRepository<Voter, Long> {
    boolean existsByVoterEmail(String email);
}
