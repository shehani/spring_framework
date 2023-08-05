package com.example.rest;

import com.example.model.Idea;
import com.example.model.Response;
import com.example.repository.IdeaRepository;
import com.example.util.Constant;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://ui.com:8798")
@RequestMapping(path = "/api/idea" , produces = {MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
public class IdeaRestController {

    @Autowired
    private IdeaRepository ideaRepository;

    @GetMapping("/getIdea")
    //@ResponseBody
    public List<Idea> getIdeaByStatus(@RequestParam(value = "status") String status){
        List<Idea> ideaList= ideaRepository.findByStatus(status);
        return ideaList;
    }

    @GetMapping("/getAllIdea")
    //@ResponseBody
    public List<Idea> getAllIdeaByStatus(@RequestBody Idea idea){
        if(idea != null && idea.getStatus() != null){
            List<Idea> list = ideaRepository.findByStatus(idea.getStatus());
            return list;
        }else {
            return List.of();
        }
    }

    @PostMapping("/saveIdea")
    public ResponseEntity<Response> saveIdea(@Valid @RequestBody Idea idea , @RequestHeader("invocationFrom") String invFrom){
        log.info("service caller is "+invFrom);
        ideaRepository.save(idea);
        Response response = new Response("200" , "Idea is saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).
                    header("isIdeaSaved" , "true").body(response);

    }

    @DeleteMapping("/deleteIdea")
    public ResponseEntity<Response> deleteIdea(RequestEntity<Idea> requestEntity){
        HttpHeaders requestHeader = requestEntity.getHeaders();
        requestHeader.forEach((key,value) ->{
            log.info(String.format("Header '%s' = %s", key , value.stream().collect(Collectors.joining("|"))));
        });
        Idea idea = requestEntity.getBody();
        Optional<Idea> optionalIdea = ideaRepository.findById(idea.getId());
        if(optionalIdea.isPresent()){
            ideaRepository.delete(optionalIdea.get());
            Response response = new Response("200", "Idea has been deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
            Response response = new Response("400", "Invalid Idea ID, received.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


    }

    @PatchMapping("/closeStatus")
    public ResponseEntity<Response> updateStatusToClose(@RequestBody Idea idea){
        if(idea != null && idea.getId() > 0) {
            Optional<Idea> optionalIdea = ideaRepository.findById(idea.getId());
            if(optionalIdea.isPresent()) {
                optionalIdea.get().setStatus(Constant.closeStatus);
                ideaRepository.save(optionalIdea.get());
                Response response = new Response("200","status has been updated to CLOSE successfully!!");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                Response response = new Response("400","Invalid Idea ID");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        }else {
            Response response = new Response("400","Idea ID is null or invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
