package com.cloud.connector.repository;

import com.cloud.connector.model.CloudDataProcessView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CloudDataProcessViewRepository extends JpaRepository<CloudDataProcessView, Long> {

    Page<CloudDataProcessView> findAllByDestinationTypeAndPodId(String extractionFlag,Long podId, Pageable pageable);

    Optional<CloudDataProcessView> findByIdAndStatusId(Long id, Integer statusId);

    Page<CloudDataProcessView> findAllByPodId(Long PodId , Pageable pageable);

    List<CloudDataProcessView> findAllById(Long id);
}
