package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.repository.PatientRepository;
import gr.aueb.cf.medicalapp.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * PatientService is the service class for performing CRUD operations on patients.
 */
@Service
@Transactional
public class PatientService implements IPatientService {


    private final  PatientRepository patientRepository;


    @Autowired
    public PatientService(PatientRepository patientRepo) {
        this.patientRepository = patientRepo;
    }

    /**
     * Retrieves all patients.
     *
     * @return a list of all patients
     */
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves the logged-in patient by their ID.
     *
     * @param patientID the ID of the logged-in patient
     * @return the logged-in patient with the specified ID
     */
    public Patient getLoggedInPatient(String patientID) {

        return patientRepository.findByPatientID(patientID);
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param patientID the ID of the patient
     * @return the patient with the specified ID
     */
    public Patient get(String patientID) {
        return patientRepository.findById(patientID).get();
    }


    /**
     * Saves a patient.
     *
     * @param patient the patient to be saved
     */
    public void save(Patient patient) {
        patientRepository.save(patient);
    }


    /**
     * Deletes a patient by their ID.
     *
     * @param patientID the ID of the patient to be deleted
     */
    public void delete(String patientID) {
        patientRepository.deleteById(patientID);
    }

    /**
     * Checks if a patient with the specified ID exists.
     *
     * @param patientID the ID of the patient
     * @return true if the patient exists, false otherwise
     */
    public boolean patientExists(String patientID) {
        return patientRepository.existsById(patientID);
    }
}
