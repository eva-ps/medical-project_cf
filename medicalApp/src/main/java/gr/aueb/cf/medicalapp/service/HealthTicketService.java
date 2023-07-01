package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.model.HealthTicket;
import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.repository.HealthTicketRepository;
import gr.aueb.cf.medicalapp.service.interfaces.IHealthTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * HealthTicketService is the service class for performing CRUD operations on health tickets.
 */
@Service
@Transactional
public class HealthTicketService implements IHealthTicketService {

    @Autowired
    private HealthTicketRepository healthRepository;

    /**
     * Retrieves all health tickets.
     *
     * @return a list of all health tickets
     */
    public List<HealthTicket> findAll() {
        return healthRepository.findAll();
    }

    /**
     * Retrieves a health ticket by its ID.
     *
     * @param ticketID the ID of the health ticket
     * @return the health ticket with the specified ID
     */
    public HealthTicket get(int ticketID) {
        return healthRepository.findById(ticketID).get();
    }

    /**
     * Retrieves all health tickets for a specific patient.
     *
     * @param patient the patient for whom to retrieve the health tickets
     * @return a list of health tickets for the specified patient
     */
    public List<HealthTicket> getByPatient(Patient patient) {
        return healthRepository.findByPatientID(patient);
    }


    /**
     * Saves a health ticket.
     *
     * @param healthTicket the health ticket to be saved
     */
    public void save(HealthTicket healthTicket) {
        healthRepository.save(healthTicket);
    }


    /**
     * Deletes a health ticket by its ID.
     *
     * @param ticketID the ID of the health ticket to be deleted
     */
    public void delete(int ticketID) {
        healthRepository.deleteById(ticketID);
    }


    /**
     * Deletes all health tickets for a specific patient.
     *
     * @param patient the patient for whom to delete the health tickets
     * @return a list of deleted health tickets for the specified patient
     */
    public List<HealthTicket> deleteAllByPatientID(Patient patient) {
        return healthRepository.deleteAllByPatientID(patient);
    }


    /**
     * Deletes all health tickets for a specific doctor.
     *
     * @param doctor the doctor for whom to delete the health tickets
     * @return a list of deleted health tickets for the specified doctor
     */
    public List<HealthTicket> deleteAllByDoctorID(Doctor doctor) {
        return healthRepository.deleteAllByDoctorID(doctor);
    }


    /**
     * Updates a health ticket.
     *
     * @param healthTicket the health ticket to be updated
     */
    public void update(HealthTicket healthTicket) {
        healthTicket.setTicketID(healthTicket.getTicketID());
        healthRepository.save(healthTicket);
    }
}
