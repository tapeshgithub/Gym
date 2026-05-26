package com.demo.Gym.DTO;

import java.time.LocalDate;

public class MembershipDTO {

    private Long membershipId;
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long planId;
    private String planName;
    private Integer planDurationMonths;
    private Double planPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String paymentStatus;

    public MembershipDTO() {}

    public MembershipDTO(Long membershipId, Long memberId, String memberName,
                         String memberEmail, String memberPhone,
                         Long planId, String planName,
                         Integer planDurationMonths, Double planPrice,
                         LocalDate startDate, LocalDate endDate,
                         String paymentStatus) {
        this.membershipId = membershipId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.planId = planId;
        this.planName = planName;
        this.planDurationMonths = planDurationMonths;
        this.planPrice = planPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = paymentStatus;
    }

    public Long getMembershipId() { return membershipId; }
    public void setMembershipId(Long membershipId) { this.membershipId = membershipId; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getMemberEmail() { return memberEmail; }
    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

    public String getMemberPhone() { return memberPhone; }
    public void setMemberPhone(String memberPhone) { this.memberPhone = memberPhone; }

    public Long getPlanId() { return planId; }
    public void setPlanId(Long planId) { this.planId = planId; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public Integer getPlanDurationMonths() { return planDurationMonths; }
    public void setPlanDurationMonths(Integer planDurationMonths) { this.planDurationMonths = planDurationMonths; }

    public Double getPlanPrice() { return planPrice; }
    public void setPlanPrice(Double planPrice) { this.planPrice = planPrice; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
