package gr.aueb.cf.medicalapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * CustomDoctorDetails is a custom implementation of Spring Security's UserDetails interface
 * specifically designed for the Doctor class.
 * It provides details and authentication information for a Doctor user.
 */

public class CustomDoctorDetails implements UserDetails {

    private Doctor doctor;

    /**
     * Constructs a new CustomDoctorDetails object with the specified Doctor.
     *
     * @param doctor the Doctor object for which to provide details and authentication information
     */
    public CustomDoctorDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword() {
        return doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getEmail();
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
     * Returns the full name of the Doctor.
     *
     * @return the full name of the Doctor
     */
    public String getName() {
        return doctor.getFirstName() + doctor.getLastName();
    }


    /**
     * Returns the ID of the Doctor.
     *
     * @return the ID of the Doctor
     */
    public String getDoctorID() {
        return doctor.getDoctorID();
    }
}