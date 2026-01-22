package com.VotingApp.VotingApp.Repository;

import com.VotingApp.VotingApp.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate,Long> {
    List<Candidate> findAllByOrderByVoteCountDesc();
}
