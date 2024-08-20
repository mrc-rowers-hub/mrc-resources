package com.codeaddi.mrc_resources.controller.db;

import com.codeaddi.mrc_resources.model.BoatsRepository;
import com.codeaddi.mrc_resources.model.entity.Boat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BoatService {
    @Autowired
    BoatsRepository boatsRepository;

    public List<Boat> getAllBoats(){
        return boatsRepository.findAll();
    }
}
