package com.udayanga.uni_timetable.repository;

import com.udayanga.uni_timetable.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAll();
    List<Schedule> findAllByUserId(Long id);
    List<Schedule> findAllByClassroomId(Long id);
    List<Schedule> findAllByBatchesId(Long id);

}
