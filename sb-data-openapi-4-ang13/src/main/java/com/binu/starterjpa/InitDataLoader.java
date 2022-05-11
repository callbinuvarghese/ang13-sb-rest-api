package com.binu.starterjpa;


import com.binu.starterjpa.entity.Tutorial;
import com.binu.starterjpa.repo.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader {

    private TutorialRepo tutorialRepo;

    @Autowired
    public void DataLoader(TutorialRepo tutorialRepo) {
        this.tutorialRepo = tutorialRepo;
        LoadTestData();
    }

    private void LoadTestData() {
        tutorialRepo.save(new Tutorial(1L, "Tutorial 1", "Tutorial Desc 1", true));
        tutorialRepo.save(new Tutorial(2L, "Tutorial 2", "Tutorial Desc 2", false));
        tutorialRepo.save(new Tutorial(3L,"Tutorial 3", "Tutorial Desc 3",true));

    }
}