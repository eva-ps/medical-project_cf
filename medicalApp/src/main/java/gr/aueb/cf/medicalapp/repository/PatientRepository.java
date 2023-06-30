package gr.aueb.cf.medicalapp.repository;

import gr.aueb.cf.medicalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * PatientRepository is a repository interface for managing Patient entities.
 * It provides methods for CRUD operations and custom queries related to Patient objects.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    /**
     * Retrieves a Patient entity based on the patientID.
     *
     * @param patientID the patientID
     * @return the Patient entity associated with the specified patientID
     */
    @Query("SELECT p FROM Patient p WHERE p.patientID = ?1")
     Patient findByPatientID(String patientID);

    /**
     * Retrieves a Patient entity based on the email.
     *
     * @param email the email
     * @return the Patient entity associated with the specified email
     */
    @Query("SELECT p FROM Patient p WHERE p.email = ?1")
    Patient findPatientByEmail(String email);
}