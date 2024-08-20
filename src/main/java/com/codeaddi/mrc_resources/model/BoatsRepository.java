package com.codeaddi.mrc_resources.model;

import com.codeaddi.mrc_resources.model.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatsRepository extends JpaRepository<Boat, Long> {}
