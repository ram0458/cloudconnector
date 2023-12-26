package com.cloud.connector.repository;

import com.cloud.connector.model.CloudDataProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CloudRepository extends JpaRepository<CloudDataProcess,Integer> {
//    @Query("select tableName from CloudDataProcess c where c.tableName = :tableName")
//    String findByTableNameIgnoreCase(@Param("tableName") String tableName);

    CloudDataProcess findByTableNameAndPodId(String tableName, Long podId);

    List<CloudDataProcess> findAllBySqlQueryAndPodId(String sqlQuery, Long podId);

    Page<CloudDataProcess> findAllByPodId(Long podId, Pageable pageable);

    Page<CloudDataProcess> findAllByDestinationTypeAndPodId(String extractionFlag, Long podId, Pageable pageable);
}
