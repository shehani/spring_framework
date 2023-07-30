package com.example.service;

import com.example.model.Idea;
import com.example.repository.IdeaRepository;
import com.example.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Slf4j
@RequestScope
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    public boolean isSaved(Idea idea){
        boolean isSaved = false;
//        idea.setCreatedDate(LocalDateTime.now()); //JPA handling auditing now :)
        idea.setStatus(Constant.openStatus);
        Idea savedIdea = ideaRepository.save(idea);
        log.info(idea.toString());
        return isSaved;
    }

    /**
     * This method is supporting for getting Ideas page posted by users
     * @return Idea List
     */
    public Page<Idea> getIdeasWithOpenStatus(int pageNum , String sortField, String sortDir){
        int pageSize =2;
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Idea> ideaPage = ideaRepository.findByStatus(Constant.openStatus,pageable);
        return ideaPage;
    }

    /**
     * This method is supporting for get All Ideas posted by specific user
     * @param name
     * @return
     */
    public List<Idea> getIdeaByName(String name){
        List<Idea> ideaList = ideaRepository.findByFirstName(name);
        return ideaList;
    }

    /**
     * This method is supporting for updating Ideas to close
     * @param id
     * @return
     */
    public boolean closeIdea(int id,String updatedBy){
        boolean isUpdated = false;
        Optional<Idea> existingIdea = ideaRepository.findById(id);
        existingIdea.ifPresent(idea1 -> {
            idea1.setStatus(Constant.closeStatus);

        });
        Idea updatedIdea = ideaRepository.save(existingIdea.get());
        if(updatedIdea!=null && updatedIdea.getId()>0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
