package com.monocept.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transactions {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="transaction_id")
	private Long transactionId;
	
	
    @NotNull
    @Positive
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Positive
    @Column(name="serial_no")
    private Long serialNo;
    

	@Column(name = "transaction_date")
	private LocalDate transactionDate = LocalDate.now();
	
	
	@NotBlank
	@Column(name = "status")
	private String status;
	
	
	@Column(name = "late_charges")
	private Boolean lateCharges;
	
	@Column(name = "total_amount_paid")
	private Double totalAmountPaid;
	
	@Column(name = "transaction_paid_date")
	private LocalDateTime transactionPaidDate;
	
	
	@Column(name = "agent_commission")
	private Double agentCommission;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_account_id", nullable = false)
    @JsonBackReference
    private PolicyAccount policyAccount;
	
	@JoinColumn(name = "transaction_identification")
	private String transactionIdentification;
	
	
}
