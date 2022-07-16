package com.star.ttc.coeus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.ttc.coeus.models.SelfRoles;

@Repository
public interface SelfRolesRepository extends JpaRepository<SelfRoles, Long> {

}