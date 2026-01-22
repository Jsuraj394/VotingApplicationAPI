package com.VotingApp.VotingApp.Service;

import com.VotingApp.VotingApp.DTO.CandidateRequestDTO;
import com.VotingApp.VotingApp.Entity.Candidate;
import com.VotingApp.VotingApp.Entity.Vote;
import com.VotingApp.VotingApp.Exception.ResourceNotFoundException;
import com.VotingApp.VotingApp.Repository.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private CandidateRepo candidateRepo;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    public Candidate registerCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate==null){
            throw new ResourceNotFoundException("Candidate with ID " + id + " not found.");
        }
        return candidate;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepo.findAll();
    }

    public Candidate updateCandidate(CandidateRequestDTO candidate, Long id) {
        Candidate existingCandidate = candidateRepo.findById(id).orElse(null);
        if (existingCandidate != null) {
            existingCandidate.setCandidateName(candidate.getCandidateName());
            existingCandidate.setParty(candidate.getParty());
            return candidateRepo.save(existingCandidate);
        }
        return null;
    }

    public String deleteCandidate(Long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if (candidateRepo.existsById(id)) {
            List<Vote> vote =  candidate.getVote();
            vote.forEach(v -> {
                v.setCandidate(null);

            });
            candidate.getVote().clear();

        }
        candidateRepo.delete(candidate);
        return "Candidate with ID " + id + " has been deleted.";
    }
}
