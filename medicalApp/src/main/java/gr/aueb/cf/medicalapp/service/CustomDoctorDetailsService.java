package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.CustomDoctorDetails;
import gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * CustomDoctorDetailsService is the service class that implements UserDetailsService
 * for loading doctor user details.
 */
@Service
public class CustomDoctorDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Loads the doctor user details by username (doctorID).
     *
     * @param username the username (doctorID)
     * @return the UserDetails of the doctor
     * @throws UsernameNotFoundException if the doctor is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findDoctorByEmail(username);
        if (doctor == null) {
            throw new UsernameNotFoundException("Doctor not found");
        }
        return new CustomDoctorDetails(doctor);
    }
}
