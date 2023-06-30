package gr.aueb.cf.medicalapp.model;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * The HealthTicket class represents a health ticket in the clinic management system.
 * It contains information about the symptoms, diagnosis, services, patient, doctor, and submission date.
 */
@Entity
@Table(name = "healthticket")
public class HealthTicket {
    
    @Id
    @Column(name = "ticketID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;
    
    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patientID", referencedColumnName = "patientID")
    public Patient patientID;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "doctorID", referencedColumnName = "doctorID")
    public Doctor doctorID;

    @Column(nullable = true, length = 300, name = "symptoms")
    private String symptoms;

    @Column(nullable = true, length = 300, name = "services")
    private String services;

    @Column(nullable = true, length = 300, name = "diagnosis")
    private String diagnosis;

    @Column(name = "date_submitted")
    private LocalDate dateSubmitted;

    public HealthTicket() {
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatientID() {
        return patientID;
    }

    public void setPatientID(Patient patientID) {
        this.patientID = patientID;
    }

    public Doctor getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Doctor doctorID) {
        this.doctorID = doctorID;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate localdate) {
        this.dateSubmitted = localdate;
    }

    @Override
    public String toString() {
        return "HealthTicket [ticketID=" + ticketID + ", patientID=" + patientID + ", doctorID=" + doctorID
                + ", symptoms=" + symptoms + ", diagnosis=" + diagnosis + ", dateSubmitted=" + dateSubmitted + "]";
    }


    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
