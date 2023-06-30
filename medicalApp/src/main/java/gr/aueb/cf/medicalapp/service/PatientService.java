package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * PatientService is the service class for performing CRUD operations on patients.
 */
@Service
@Transactional
public class PatientService {


    private final  PatientRepository patientRepo;


    @Autowired
    public PatientService(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    /**
     * Retrieves all patients.
     *
     * @return a list of all patients
     */
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    /**
     * Retrieves the logged-in patient by their ID.
     *
     * @param patientID the ID of the logged-in patient
     * @return the logged-in patient with the specified ID
     */
    public Patient getLoggedInPatient(String patientID) {

        return patientRepo.findByPatientID(patientID);
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param patientID the ID of the patient
     * @return the patient with the specified ID
     */
    public Patient get(String patientID) {
        return patientRepo.findById(patientID).get();
    }


    /**
     * Saves a patient.
     *
     * @param patient the patient to be saved
     */
    public void save(Patient patient) {
        patientRepo.save(patient);
    }


    /**
     * Deletes a patient by their ID.
     *
     * @param patientID the ID of the patient to be deleted
     */
    public void delete(String patientID) {
        patientRepo.deleteById(patientID);
    }

    /**
     * Checks if a patient with the specified ID exists.
     *
     * @param patientID the ID of the patient
     * @return true if the patient exists, false otherwise
     */
    public boolean patientExists(String patientID) {
        return patientRepo.existsById(patientID);
    }
}
