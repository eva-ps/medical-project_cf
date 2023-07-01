package gr.aueb.cf.medicalapp.service.interfaces;

import gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.model.HealthTicket;
import gr.aueb.cf.medicalapp.model.Patient;

import java.util.List;

public interface IHealthTicketService {

    /**
     * Retrieves a list of all health tickets.
     */
    List<HealthTicket> findAll();

    /**
     * Retrieves a health ticket by its ID.
     *
     * @param ticketID The ID of the health ticket.
     * @return The health ticket with the specified ID.
     */
    HealthTicket get(int ticketID);

    /**
     * Retrieves a list of health tickets associated with a patient.
     *
     * @param patient The patient associated with the health tickets.
     * @return A list of health tickets associated with the patient.
     */
    List<HealthTicket> getByPatient(Patient patient);

    /**
     * Saves a health ticket.
     *
     * @param healthTicket The health ticket to save.
     */
    void save(HealthTicket healthTicket);

    /**
     * Deletes a health ticket by its ID.
     *
     * @param ticketID The ID of the health ticket to delete.
     */
    void delete(int ticketID);

    /**
     * Deletes all health tickets associated with a patient.
     *
     * @param patient The patient associated with the health tickets.
     * @return A list of deleted health tickets associated with the patient.
     */
    List<HealthTicket> deleteAllByPatientID(Patient patient);

    /**
     * Deletes all health tickets associated with a doctor.
     *
     * @param doctor The doctor associated with the health tickets.
     * @return A list of deleted health tickets associated with the doctor.
     */
    List<HealthTicket> deleteAllByDoctorID(Doctor doctor);

    /**
     * Updates a health ticket.
     *
     * @param healthTicket The health ticket to update.
     */
    void update(HealthTicket healthTicket);
}
