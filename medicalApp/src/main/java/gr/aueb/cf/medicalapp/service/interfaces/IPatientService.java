package gr.aueb.cf.medicalapp.service.interfaces;

import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.repository.PatientRepository;

import java.util.List;

public interface IPatientService {

    /**
     * Retrieves a list of all patients.
     */
    List<Patient> findAll();

    /**
     * Retrieves the currently logged-in patient by their ID.
     *
     * @param patientID The ID of the patient.
     * @return The currently logged-in patient with the specified ID.
     */
    Patient getLoggedInPatient(String patientID);

    /**
     * Retrieves a patient by their ID.
     *
     * @param patientID The ID of the patient.
     * @return The patient with the specified ID.
     */
    Patient get(String patientID);

    /**
     * Saves a patient.
     *
     * @param patient The patient to save.
     */
    void save(Patient patient);

    /**
     * Deletes a patient by their ID.
     *
     * @param patientID The ID of the patient to delete.
     */
    void delete(String patientID);

    /**
     * Checks if a patient with the specified ID exists.
     *
     * @param patientID The ID of the patient.
     * @return True if a patient with the specified ID exists, false otherwise.
     */
    boolean patientExists(String patientID);
}
