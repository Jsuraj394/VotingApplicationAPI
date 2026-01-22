package com.VotingApp.VotingApp.VoteController;


import com.VotingApp.VotingApp.DTO.VoterResponseDTO;
import com.VotingApp.VotingApp.Entity.Voter;
import com.VotingApp.VotingApp.Service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/voter/")
@CrossOrigin(origins = "http://localhost:8080")
public class VoterController {

    private VoterService voterService;
    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("register")
    public ResponseEntity<VoterResponseDTO> registerVoter(@Valid @RequestBody Voter voter){
        Voter response_voter = voterService.registerVoter(voter);
        VoterResponseDTO voterResponseDTO = new VoterResponseDTO();
        voterResponseDTO.setVoterId(response_voter.getVoterId());
        voterResponseDTO.setVoterName(response_voter.getVoterName());
        voterResponseDTO.setVoterEmail(response_voter.getVoterEmail());
        voterResponseDTO.setHasVoted(response_voter.getHasVoted());

         return new ResponseEntity<>(voterResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("all")
    public ResponseEntity<List<VoterResponseDTO>> getAllVoters(){
        List<Voter> voters = voterService.getAllVoters();
        List<VoterResponseDTO> voterResponseDTOs = new ArrayList<>();
        for(Voter voter : voters){
            VoterResponseDTO voterResponseDTO = new VoterResponseDTO();
            voterResponseDTO.setVoterId(voter.getVoterId());
            voterResponseDTO.setVoterName(voter.getVoterName());
            voterResponseDTO.setVoterEmail(voter.getVoterEmail());
            voterResponseDTO.setHasVoted(voter.getHasVoted());
            voterResponseDTOs.add(voterResponseDTO);
        }

        return new ResponseEntity<>(voterResponseDTOs,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VoterResponseDTO> getVoterById(@PathVariable("id") Long id){
        Voter response_voter = voterService.getVoterById(id);
        VoterResponseDTO voterResponseDTO = new VoterResponseDTO();
        voterResponseDTO.setVoterId(response_voter.getVoterId());
        voterResponseDTO.setVoterName(response_voter.getVoterName());
        voterResponseDTO.setVoterEmail(response_voter.getVoterEmail());
        voterResponseDTO.setHasVoted(response_voter.getHasVoted());
        return new ResponseEntity<>(voterResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable("id") Long id) {
        return new ResponseEntity<>(voterService.deleteVoter(id), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<VoterResponseDTO> updateVoter(@PathVariable("id") Long Id, @Valid @RequestBody Voter voter){
        voter.setVoterId(Id);
        Voter response_voter = voterService.updateVoter(voter, Id);
        VoterResponseDTO voterResponseDTO = new VoterResponseDTO();
        voterResponseDTO.setVoterId(response_voter.getVoterId());
        voterResponseDTO.setVoterName(response_voter.getVoterName());
        voterResponseDTO.setVoterEmail(response_voter.getVoterEmail());
        voterResponseDTO.setHasVoted(response_voter.getHasVoted());
        return new ResponseEntity<>(voterResponseDTO,HttpStatus.OK);
    }



}
