package com.alimberdi.reservehub.repository;

import com.alimberdi.reservehub.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {

}
