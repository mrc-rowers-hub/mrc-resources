package com.codeaddi.mrc_resources.model.repository;

import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceInUseRepository extends JpaRepository<ResourceInUse, Long> {}