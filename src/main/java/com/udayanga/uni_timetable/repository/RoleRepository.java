package com.udayanga.uni_timetable.repository;

import com.udayanga.uni_timetable.model.ERole;
import com.udayanga.uni_timetable.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}