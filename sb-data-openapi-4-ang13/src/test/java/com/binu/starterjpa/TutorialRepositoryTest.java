package com.binu.starterjpa;



import com.binu.starterjpa.entity.Tutorial;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SbDataRest4Ang13Application.class)
public class TutorialRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById() {
        Tutorial tutorial = entityManager.find(Tutorial.class, 1001L);

        assertEquals(1001L, tutorial.getId());
    }

    @Test
    @Transactional
    @DirtiesContext
    public void insertTest() {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(1001L);
        tutorial.setTitle("Tutorial 1001");
        tutorial.setDescription("Tutorial 1001 Description");
        tutorial.setPublished(true);

        entityManager.persist(tutorial);

        assertNotNull(tutorial.getId());
    }


    @Test
    @Transactional
    public void updateTest() {
        Tutorial tutorial = entityManager.find(Tutorial.class,1001L);
        tutorial.setTitle("Tutorial 11001 Mod");
        entityManager.merge(tutorial);

        Tutorial tutorialUpdated = entityManager.find(Tutorial.class,1001L);
        assertEquals("Tutorial 11001 Mod", tutorialUpdated.getTitle());
    }


    @Test
    @Transactional
    public void persist_merge_flush() {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(1002L);
        tutorial.setTitle("Tutorial 1002");

        entityManager.persist(tutorial);

        tutorial.setDescription("Tutorial 1002 Description Updated");
        entityManager.merge(tutorial);
        entityManager.flush();

        Tutorial tutorialUpdated = entityManager.find(Tutorial.class,1002L);
        assertEquals("Tutorial 1002 Description Updated", tutorialUpdated.getDescription());
    }
}
