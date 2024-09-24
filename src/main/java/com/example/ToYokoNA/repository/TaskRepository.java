package com.example.ToYokoNA.repository;

import com.example.ToYokoNA.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
//    絞込情報なし
    @Query("SELECT t FROM Task t WHERE limitDate BETWEEN :start AND :end ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByOrderByLimitDateAscLimit(@Param("limit") int limit, @Param("start") Date start, @Param("end")Date end);
//    絞込情報すべてあり
    @Query("SELECT t FROM Task t Where limitDate BETWEEN :start AND :end AND content = :SelectContent AND status = :SelectStatus ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByWHEREALLOrderByLimitDateAsc(@Param("limit") int limit, @Param("start") Date start, @Param("end")Date end, @Param("SelectContent") String selectContent, @Param("SelectStatus") String selectStatus);
//    タスク内容あり、ステータス情報なし
    @Query("SELECT t FROM Task t Where limitDate BETWEEN :start AND :end AND content = :SelectContent ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByWHEREContentOrderByLimitDateAsc(@Param("limit") int limit, @Param("start") Date start, @Param("end")Date end, @Param("SelectContent") String selectContent);
//    タスク内容なし、ステータス情報あり
    @Query("SELECT t FROM Task t Where limitDate BETWEEN :start AND :end  AND status = :SelectStatus ORDER BY limitDate ASC LIMIT :limit")
    public List<Task> findAllByWHEREStatusOrderByLimitDateAsc(@Param("limit") int limit, @Param("start") Date start, @Param("end")Date end, @Param("SelectStatus") String selectStatus);
}