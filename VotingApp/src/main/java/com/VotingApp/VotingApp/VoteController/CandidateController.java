package com.VotingApp.VotingApp.VoteController;


import com.VotingApp.VotingApp.DTO.CandidateRequestDTO;
import com.VotingApp.VotingApp.DTO.CandidateResponseDTO;
import com.VotingApp.VotingApp.Entity.Candidate;
import com.VotingApp.VotingApp.Service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/candidate/")
@CrossOrigin
public class CandidateController {
    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("register")
    public ResponseEntity<CandidateResponseDTO> registerCandidate(@Valid @RequestBody Candidate candidate){
        Candidate candidateResponse = candidateService.registerCandidate(candidate);
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();

        candidateResponseDTO.setCandidateId(candidateResponse.getCandidateId());
        candidateResponseDTO.setCandidateName(candidateResponse.getCandidateName());
        candidateResponseDTO.setParty(candidateResponse.getParty());

        return new ResponseEntity<>(candidateResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("getCandidateById/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable("id") Long id){
        Candidate candidateResponse = candidateService.getCandidateById(id);
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();

        candidateResponseDTO.setCandidateId(candidateResponse.getCandidateId());
        candidateResponseDTO.setCandidateName(candidateResponse.getCandidateName());
        candidateResponseDTO.setParty(candidateResponse.getParty());


        return new ResponseEntity<>(candidateResponseDTO, HttpStatus.FOUND);
    }

    @GetMapping("getAllCandidates")
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        List<Candidate> candidateResponse = candidateService.getAllCandidates();
        List<CandidateResponseDTO> candidateResponseDTOList = new ArrayList<>();
        for(Candidate candidate : candidateResponse) {
            candidateResponseDTOList.add(new CandidateResponseDTO(candidate.getCandidateId(),candidate.getCandidateName(),candidate.getParty()));
        }

        return new ResponseEntity<>(candidateResponseDTOList, HttpStatus.FOUND);
    }

    @PutMapping("updateCandidate/{id}")
    public ResponseEntity<CandidateResponseDTO> updateCandidate(@Valid @RequestBody CandidateRequestDTO candidateRequestDTO, @PathVariable("id") Long id){
        Candidate candidateResponse = candidateService.updateCandidate(candidateRequestDTO, id);
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();

        candidateResponseDTO.setCandidateId(candidateResponse.getCandidateId());
        candidateResponseDTO.setCandidateName(candidateResponse.getCandidateName());
        candidateResponseDTO.setParty(candidateResponse.getParty());

        return new ResponseEntity<>(candidateResponseDTO, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("deleteCandidate/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
        return new ResponseEntity<>(candidateService.deleteCandidate(id), HttpStatus.OK);
    }

}
