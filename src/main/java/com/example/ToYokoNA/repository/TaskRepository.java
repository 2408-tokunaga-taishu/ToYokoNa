package com.example.ToYokoNA.repository;

import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
//    絞込情報なし
    @Query("SELECT t FROM Task t Where limitDate BETWEEN :startDate AND :endDate ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByOrderByLimitDateAscLimit(@Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate")String endDate);
//    絞込情報すべてあり
    @Query("SELECT t FROM Task t Where limitDate BETWEEN :startDate AND :endDate AND content = :SelectContent AND status = :SelectStatus ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByWHEREALLOrderByLimitDateAsc(@Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate")String endDate, @Param("SelectContent") String selectContent, @Param("SelectStatus") String selectStatus);


}