package com.monocept.app.repository;

import com.monocept.app.entity.Agent;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Agent a SET a.balance = a.balance + :agentCommission WHERE a.agentId = :agentId")
    void updateAgentCommission(@Param("agentId") Long agentId, @Param("agentCommission") Double agentCommission);


	Page<Agent> findByIsActiveTrue(Pageable pageable);

	Page<Agent> findByIsActiveFalse(Pageable pageable);

    Long countByIsActiveTrue();

    Long countByIsActiveFalse();

    Page<Agent> findAllByIsActiveFalse(Pageable pageable);

    Page<Agent> findAlByIsActiveTrue(Pageable pageable);

    Page<Agent> findAllByIsApprovedFalse(Pageable pageable);

    Page<Agent> findAllByIsApprovedTrue(Pageable pageable);

    Page<Agent> findAllByIsApprovedTrueAndIsActiveTrue(Pageable pageable);

    Page<Agent> findAllByIsApprovedTrueAndIsActiveFalse(Pageable pageable);

    Page<Agent> findAllByIsActiveTrue(Pageable pageable);
}
