package com.hospitals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitals.model.Admin;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
