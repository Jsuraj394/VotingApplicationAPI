package com.VotingApp.VotingApp.Service;


import com.VotingApp.VotingApp.Entity.Candidate;
import com.VotingApp.VotingApp.Entity.ElectionResult;
import com.VotingApp.VotingApp.Exception.DuplicateResourceException;
import com.VotingApp.VotingApp.Exception.ResourceNotFoundException;
import com.VotingApp.VotingApp.Repository.CandidateRepo;
import com.VotingApp.VotingApp.Repository.ElectionResultRepo;
import com.VotingApp.VotingApp.Repository.VoteRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ElectionResultService {
    private ElectionResultRepo electionResultRepo;
    private VoteRepo voteRepo;
    private CandidateRepo candidateRepo;

    @Autowired
    public ElectionResultService(ElectionResultRepo electionResultRepo, VoteRepo voteRepo, CandidateRepo candidateRepo) {
        this.electionResultRepo = electionResultRepo;
        this.voteRepo=voteRepo;
        this.candidateRepo=candidateRepo;
    }

    public ElectionResult declareElectionResult(String electionName) {
        ElectionResult electionResult = electionResultRepo.findByElectionName(electionName).orElse(null);

        if(electionResult != null) {
             return electionResult; // Return existing result if already declared
        }
        if(voteRepo.count() == 0) {
            throw new DuplicateResourceException("No votes have been cast. Cannot declare election result.");
        }
        List<Candidate> candidates = candidateRepo.findAllByOrderByVoteCountDesc();
        if(candidates.isEmpty()) {
            throw new ResourceNotFoundException("No candidates found. Cannot declare election result.");
        }



        int totalVotes=0;
        for(Candidate candidate: candidates) {
            totalVotes += candidate.getVoteCount();
        }
        System.out.println("Total Votes : " + totalVotes);


        ElectionResult newElectionResult = new ElectionResult();
        newElectionResult.setElectionName(electionName);
        newElectionResult.setWinner(candidates.get(0));
        newElectionResult.setTotalVotes((long) totalVotes);
        return electionResultRepo.save(newElectionResult);


    }

    public List<ElectionResult> getAllElectionResults() {
        return electionResultRepo.findAll();
    }
}
