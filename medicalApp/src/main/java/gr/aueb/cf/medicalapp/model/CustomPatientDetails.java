package gr.aueb.cf.medicalapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * CustomPatientDetails is a custom implementation of Spring Security's UserDetails interface
 * specifically designed for the Patient class.
 * It provides details and authentication information for a Patient user.
 */
public class CustomPatientDetails implements UserDetails {

    private Patient patient;


    public CustomPatientDetails(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return patient.getPassword();
    }

    @Override
    public String getUsername() {
        return patient.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the full name of the Patient.
     *
     * @return the full name of the Patient
     */
    public String getFullName() {
        return patient.getFirstName() + patient.getLastName();
    }

    /**
     * Returns the ID of the Patient.
     *
     * @return the ID of the Patient
     */
    public String getPatientID() {
        return patient.getPatientID();
    }
}