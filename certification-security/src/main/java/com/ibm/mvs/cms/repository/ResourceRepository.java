package com.ibm.mvs.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.mvs.cms.models.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	Optional<Resource> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
