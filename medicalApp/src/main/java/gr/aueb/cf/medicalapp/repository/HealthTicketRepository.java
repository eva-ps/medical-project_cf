package gr.aueb.cf.medicalapp.repository;

import gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.model.HealthTicket;
import gr.aueb.cf.medicalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * HealthTicketRepository is a repository interface for managing HealthTicket entities.
 * It provides methods for CRUD operations and custom queries related to HealthTicket objects.
 */
@Repository
public interface HealthTicketRepository extends JpaRepository<HealthTicket, Integer>{

    /**
     * Retrieves a list of HealthTicket entities based on the patientID.
     *
     * @param patient the Patient entity
     * @return a list of HealthTicket entities associated with the specified patientID
     */@Query("SELECT h FROM HealthTicket h WHERE h.patientID = ?1")
    public List<HealthTicket> findByPatientID(Patient patient);

    /**
     * Deletes all HealthTicket entities associated with the patientID.
     *
     * @param patient the Patient entity
     * @return a list of HealthTicket entities that were deleted
     */
    public List<HealthTicket> deleteAllByPatientID(Patient patient);

    /**
     * Retrieves a list of HealthTicket entities based on the doctorID.
     *
     * @param doctor the Doctor entity
     * @return a list of HealthTicket entities associated with the specified doctorID
     */
    @Query("SELECT h FROM HealthTicket h WHERE h.doctorID = ?1")
    public List<HealthTicket> findByDoctorID(Doctor doctor);

    /**
     * Deletes all HealthTicket entities associated with the doctorID.
     *
     * @param doctor the Doctor entity
     * @return a list of HealthTicket entities that were deleted
     */
    public List<HealthTicket> deleteAllByDoctorID(Doctor doctor);
}
