package gr.aueb.cf.medicalapp.repository;

import gr.aueb.cf.medicalapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * DoctorRepository is a repository interface for managing Doctor entities.
 * It provides methods for CRUD operations and custom queries related to Doctor objects.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    /**
     * Retrieves a Doctor entity by its doctorID.
     *
     * @param doctorID the doctorID of the Doctor entity
     * @return the Doctor entity with the specified doctorID.
     */
    @Query("SELECT d FROM Doctor d WHERE d.doctorID = ?1")

    public Doctor findByDoctorID(String doctorID);

    /**
     * Retrieves a Doctor entity based on the email.
     *
     * @param email the email
     * @return the Doctor entity associated with the specified email
     */
    @Query("SELECT d FROM Doctor d WHERE d.email = ?1")
    Doctor findDoctorByEmail(String email);
}