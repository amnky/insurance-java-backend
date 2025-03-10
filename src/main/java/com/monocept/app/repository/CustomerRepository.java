package com.monocept.app.repository;

import com.monocept.app.entity.Customer;
import com.monocept.app.entity.PolicyAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Page<Customer> findAllByIsActiveTrue(Pageable pageable);

    Page<Customer> findAllByIsActiveFalse(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = false WHERE c.id = :customerId")
    int findByIdAndSetIsActiveFalse(@Param("customerId") Long customerId);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = true WHERE c.id = :customerId")
    int findByIdAndSetIsActiveTrue(@Param("customerId") Long customerId);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isApproved = true WHERE c.id = :customerId")
    int findByIdAndSetIsApprovedTrue(@Param("customerId") Long customerId);

	long countByIsActiveTrue();

	long countByIsActiveFalse();

    Long countByIsApprovedFalse();

    Page<Customer> findAllByIsApprovedFalse(Pageable pageable);

    Page<Customer> findAllByIsActiveTrueAndIsApprovedTrue(Pageable pageable);

    Page<Customer> findAllByIsActiveFalseAndIsApprovedTrue(Pageable pageable);

    Page<Customer> findAllByFirstName(Pageable pageable, String firstName);

    Page<Customer> findAllByIsApprovedTrue(Pageable pageable);

    Page<Customer> findAllByPolicyAccountsIn(Pageable pageable, List<PolicyAccount> policyAccounts);
}
