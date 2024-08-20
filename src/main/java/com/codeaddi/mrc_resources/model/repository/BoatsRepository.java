package com.codeaddi.mrc_resources.model.repository;

import com.codeaddi.mrc_resources.model.repository.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatsRepository extends JpaRepository<Boat, Long> {}
