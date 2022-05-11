package com.binu.starterjpa.repo;

import com.binu.starterjpa.entity.Tutorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api")
@RepositoryRestResource(collectionResourceRel = "tutorials", path = "tutorial")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://example.com"}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, maxAge = 3600)
public interface TutorialRepo extends CrudRepository<Tutorial, Long> {
    @GetMapping(value = "/findByPublished")
    List<Tutorial> findByPublished(boolean published);
    @GetMapping(value = "/findByTitleContaining")
    List<Tutorial> findByTitleContaining(String title);
    @GetMapping(value = "/findByTitle")
    List<Tutorial> findByTitle(String title);
}
