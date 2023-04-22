package com.esi.checking.repository;

import com.esi.checking.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
