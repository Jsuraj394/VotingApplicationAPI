package com.VotingApp.VotingApp.Service;

import com.VotingApp.VotingApp.Entity.Candidate;
import com.VotingApp.VotingApp.Entity.Vote;
import com.VotingApp.VotingApp.Entity.Voter;
import com.VotingApp.VotingApp.Exception.DuplicateResourceException;
import com.VotingApp.VotingApp.Exception.ResourceNotFoundException;
import com.VotingApp.VotingApp.Repository.CandidateRepo;
import com.VotingApp.VotingApp.Repository.VoterRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {

    private VoterRepo voterRepo;
    private CandidateRepo candidateRepo;

    @Autowired
    public VoterService(VoterRepo voterRepo, CandidateRepo candidateRepo) {
        this.voterRepo = voterRepo;
        this.candidateRepo = candidateRepo;
    }

    public Voter registerVoter(Voter voter) {
        if (voterRepo.existsByVoterEmail(voter.getVoterEmail())) {
            throw new DuplicateResourceException("Voter with email " + voter.getVoterEmail() + " already exists.");
        }
        return voterRepo.save(voter);
    }

    public List<Voter> getAllVoters() {
        return voterRepo.findAll();
    }

    public Voter getVoterById(Long id) {
        return voterRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voter with ID " + id + " not found."));
    }
    @Transactional
    public String deleteVoter(Long id) {
        Voter voter = voterRepo.findById(id).orElse(null);
        if (!voterRepo.existsById(id)) {
            throw new ResourceNotFoundException("Voter with ID " + id + " not found.");
        }
        Vote vote = voter.getVote();
        if(vote!=null){
            Candidate candidate =  vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepo.save(candidate);
        }
        voterRepo.deleteById(id);
        return "Voter with ID " + id + " has been deleted.";
    }

    public Voter updateVoter(Voter voter, Long id) {
        if (!voterRepo.existsById(voter.getVoterId())) {
            throw new ResourceNotFoundException("Voter with ID " + id + " not found.");
        }

        Voter existingVoter = voterRepo.findById(id).orElse(null);
        if(voter.getVoterName() != null) {
            existingVoter.setVoterName(voter.getVoterName());
        }
        if(voter.getVoterEmail() != null) {
            existingVoter.setVoterEmail(voter.getVoterEmail());
        }
        if(voter.getHasVoted() != null) {
            existingVoter.setHasVoted(voter.getHasVoted());
        }
        voterRepo.save(existingVoter);
        return existingVoter;
    }


}
