package com.tecninf.commesse.config;

import com.tecninf.commesse.entity.Clienti;
import com.tecninf.commesse.entity.Skill;
import com.tecninf.commesse.model.ESkill;
import com.tecninf.commesse.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Autowired
    private SkillRepository skillRepository;
    //TODO: add configuration to create table skill
    @Bean
    public CommandLineRunner createTableSkill() {
        ESkill[] skills = ESkill.values();
        return args -> {
            for(ESkill skill : skills) {
                if(!skillRepository.existsBySkillName(skill)) {

                    Skill newskill = new Skill();
                    newskill.setSkillName(skill);
                    skillRepository.save(newskill);

                }

            }
        };
    }


}
