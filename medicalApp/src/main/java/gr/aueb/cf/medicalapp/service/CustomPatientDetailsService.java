package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.CustomPatientDetails;
import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomPatientDetailsService is the service class that implements UserDetailsService
 * for loading patient user details.
 */
@Service
public class CustomPatientDetailsService implements UserDetailsService {

    @Autowired
    private   PatientRepository patientRepository;


    /**
     * Loads the patient user details by username (email).
     *
     * @param username the username (email)
     * @return the UserDetails of the patient
     * @throws UsernameNotFoundException if the patient is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findPatientByEmail(username);
        if (patient == null) {
            throw new UsernameNotFoundException("Patient not found");
        }
        return new CustomPatientDetails(patient);
    }
}
