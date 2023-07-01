package gr.aueb.cf.medicalapp.service;

import gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.repository.DoctorRepository;
import gr.aueb.cf.medicalapp.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * DoctorService is the service class for performing CRUD operations on doctors.
 */
@Service
@Transactional
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Retrieves all doctors.
     *
     * @return a list of all doctors
     */
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorID the ID of the doctor
     * @return the doctor with the specified ID
     */
    public Doctor get(String doctorID) {
        return doctorRepository.findById(doctorID).get();
    }

    /**
     * Retrieves the logged-in doctor by their ID.
     *
     * @param doctorID the ID of the logged-in doctor
     * @return the logged-in doctor with the specified ID
     */
    public Doctor getLoggedInDoctor(String doctorID) {
        return doctorRepository.findByDoctorID(doctorID);
    }


    /**
     * Saves a doctor.
     *
     * @param doctor the doctor to be saved
     */
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }


    /**
     * Deletes a doctor by their ID.
     *
     * @param doctorID the ID of the doctor to be deleted
     */
    public void delete(String doctorID) {
        doctorRepository.deleteById(doctorID);
    }

    /**
     * Checks if a doctor with the specified ID exists.
     *
     * @param doctorID the ID of the doctor
     * @return true if the doctor exists, false otherwise
     */
    public boolean doctorExists(String doctorID) {

        return doctorRepository.existsById(doctorID);
    }
}
