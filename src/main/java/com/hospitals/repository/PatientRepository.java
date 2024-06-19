package com.hospitals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospitals.model.Patient;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	@Query("SELECT SUM(p.paidAmount) FROM Patient p")
    Integer calculateTotalPaidAmount();

    @Query("SELECT SUM(CASE WHEN p.paidAmount > 0 THEN p.paidAmount ELSE 0 END) FROM Patient p")
    Integer calculateProfit();

    @Query("SELECT SUM(CASE WHEN p.paidAmount < 0 THEN p.paidAmount ELSE 0 END) FROM Patient p")
    Integer calculateLoss();

    @Query("SELECT AVG(p.paidAmount) FROM Patient p")
    Double calculateAveragePaidAmount();

}
