package com.esi.support.repository;

import com.esi.support.model.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Integer> {
}
