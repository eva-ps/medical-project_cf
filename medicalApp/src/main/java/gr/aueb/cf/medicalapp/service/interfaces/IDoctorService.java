package gr.aueb.cf.medicalapp.service.interfaces;

import gr.aueb.cf.medicalapp.model.Doctor;


import java.util.List;

public interface IDoctorService {

    /**
     * Retrieves a list of all doctors.
     */
    List<Doctor> findAll();

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorID The ID of the doctor.
     * @return The doctor with the specified ID.
     */

    Doctor get(String doctorID);

    /**
     * Retrieves the currently logged-in doctor by their ID.
     *
     * @param doctorID The ID of the doctor.
     * @return The currently logged-in doctor with the specified ID.
     */

    Doctor getLoggedInDoctor(String doctorID);

    /**
     * Saves a doctor.
     *
     * @param doctor The doctor to save.
     */

    void save(Doctor doctor);

    /**
     * Deletes a doctor by their ID.
     *
     * @param doctorID The ID of the doctor to delete.
     */

    void delete(String doctorID);

    /**
     * Checks if a doctor with the specified ID exists.
     *
     * @param doctorID The ID of the doctor.
     * @return True if a doctor with the specified ID exists, false otherwise.
     */
    boolean doctorExists(String doctorID);
}




