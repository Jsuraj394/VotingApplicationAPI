package com.VotingApp.VotingApp.Service;


import com.VotingApp.VotingApp.DTO.VoteResponseDTO;
import com.VotingApp.VotingApp.Entity.Candidate;
import com.VotingApp.VotingApp.Entity.Vote;
import com.VotingApp.VotingApp.Entity.Voter;
import com.VotingApp.VotingApp.Exception.ResourceNotFoundException;
import com.VotingApp.VotingApp.Repository.CandidateRepo;
import com.VotingApp.VotingApp.Repository.VoteRepo;
import com.VotingApp.VotingApp.Repository.VoterRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class VoteService {
    private VoterRepo voterRepo;
    private CandidateRepo candidateRepo;
    private VoteRepo voteRepo;

    @Autowired
    public VoteService(VoterRepo voterRepo, CandidateRepo candidateRepo, VoteRepo voteRepo) {
        this.voterRepo = voterRepo;
        this.candidateRepo = candidateRepo;
        this.voteRepo = voteRepo;
    }

    public Vote castVote(Long voterId, Long candidateId) {
        Voter voter = voterRepo.findById(voterId).orElse(null);
        Candidate candidate = candidateRepo.findById(candidateId).orElse(null);

        System.out.println(voterId);
        System.out.println(candidateId);

        if (voter == null ) {
            throw new ResourceNotFoundException("Voter with ID " + voterId + " not found.");
        }
        if(candidate == null){
            throw new ResourceNotFoundException("Candidate with ID " + candidateId + " not found.");
        }

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
        //voteRepo.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepo.save(candidate);

        voter.setHasVoted(true);
        voter.setVote(vote);
        voterRepo.save(voter);

//        VoteResponseDTO voteResponseDTO = new VoteResponseDTO();
//        voteResponseDTO.setMessage("Vote cast successfully");
//        voteResponseDTO.setVoteId(vote.getVoteId());
//        voteResponseDTO.setCandidateId(candidate.getCandidateId());
//        voteResponseDTO.setSuccess(true);

        return vote;
    }

    public List<Vote> getAllVotes() {
        return voteRepo.findAll();
    }
}
