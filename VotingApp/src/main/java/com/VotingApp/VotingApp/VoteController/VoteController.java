package com.VotingApp.VotingApp.VoteController;


import com.VotingApp.VotingApp.DTO.VoteResponseDTO;
import com.VotingApp.VotingApp.DTO.VoterInputDTO;
import com.VotingApp.VotingApp.Entity.Vote;
import com.VotingApp.VotingApp.Service.VoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/votes/")
@CrossOrigin("http://localhost:8080")
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("getAllVotes")
    public ResponseEntity<List<VoteResponseDTO>> getAllVotes() {
        List<Vote> votes = voteService.getAllVotes();
        List<VoteResponseDTO> voteResponseDTO = new ArrayList<>();
        for(Vote vote : votes) {
            voteResponseDTO.add(new VoteResponseDTO("Vote cast successfully", vote.getVoterId(), vote.getCandidateId(), true));
        }
        return new ResponseEntity<>(voteResponseDTO,HttpStatus.FOUND);
    }

    @PostMapping("castVote")
    public ResponseEntity<VoteResponseDTO> castVote(@Valid @RequestBody VoterInputDTO voterInputDTO) {
        System.out.println("voter id "  + voterInputDTO.getVoterId());
        System.out.println( "Candidate id " + voterInputDTO.getCandidateId());

        Vote vote  = voteService.castVote(voterInputDTO.getVoterId(), voterInputDTO.getCandidateId());
        VoteResponseDTO responseDTO = new VoteResponseDTO("Vote cast successfully", voterInputDTO.getVoterId(), vote.getCandidateId(), true);

        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
}
