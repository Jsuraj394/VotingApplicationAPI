package com.VotingApp.VotingApp.Repository;

import com.VotingApp.VotingApp.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote, Long> {
}
