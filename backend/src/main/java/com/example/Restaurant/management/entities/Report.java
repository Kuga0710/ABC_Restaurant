//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Report {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long reportID;
//
//    private String reportName;
//    private String reportType;
//    private String reportData;
//
//    @ManyToOne
//    @JoinColumn(name = "generatedBy", nullable = false)
//    private User generatedBy;
//}
