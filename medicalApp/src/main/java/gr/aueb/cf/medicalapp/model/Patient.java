package gr.aueb.cf.medicalapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The `Patient` class represents a patient in the medical system.
 * It stores information about the patient's personal details and medical history.
 */

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @NotBlank(message = "ID is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$", message = "Must be exactly 4 characters long")
    @Column(name = "patientID")
    private String patientID;

    @Column(nullable = false, length = 64, name = "password")
    private String password;

    @Transient
    private String plainPassword;

    @NotBlank(message = "First Name is required.")
    @Size(max = 25, message = "First Name cannot be more than 25 characters long.")
    @Column(nullable = false, length = 25, name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(max = 25)
    @Column(nullable = false, length = 25, name = "last_name")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false, length = 50, name = "email")
    private String email;

    @Column(nullable = false, name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "F-")
    private Date dob;

    @NotBlank(message = "Sex is required.")
    @Size(max = 6)
    @Column(nullable = false, length = 6, name = "sex")
    private String sex;

    @Size(max = 300)
    @Column(nullable = true, length = 300, name = "medical_Conditions")
    private String medicalConditions;

    public Patient() {
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    @Override
    public String toString() {
        return patientID;
    }

}
