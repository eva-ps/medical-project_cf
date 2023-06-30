package gr.aueb.cf.medicalapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The Doctor class represents a doctor in the clinic management system.
 * It contains information about the doctor's ID, password, name, email, date of birth, and sex.
 */

@Entity
@Table(name = "doctor")
public class Doctor {
    
    @Id
    @NotBlank(message = "ID is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$", message="Must be exactly 4 characters long")
    @Column(nullable = false, name = "doctorID")
    private String doctorID;

    @Column(nullable = false, length = 64, name = "password")
    private String password;
    
    @Transient
    private String plainPassword;
    
    @NotBlank(message = "First Name is required.")
    @Size(max = 25)
    @Column(nullable = false, length = 25, name = "first_name")
    private String firstName;
    
    @NotBlank(message = "Last Name is required.")
    @Size(max = 25)
    @Column(nullable = false, length = 25, name = "last_name")
    private String lastName;
    
    @NotBlank(message = "Email is required.")
    @Email(message="Please enter a valid email address")
    @Column(nullable = false, length = 50, name = "email")
    private String email;

    @Column(nullable = false, name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    @NotBlank(message = "Sex is required.")
    @Size(max = 6)
    @Column(nullable = false, length = 6, name = "sex")
    private String sex;

    public Doctor() {
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
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

    @Override
    public String toString() {
        return doctorID;
    }

    
    
}