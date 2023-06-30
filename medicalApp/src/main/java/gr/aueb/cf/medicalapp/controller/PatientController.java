package gr.aueb.cf.medicalapp.controller;

import gr.aueb.cf.medicalapp.model.CustomPatientDetails;
import  gr.aueb.cf.medicalapp.model.Doctor;
import gr.aueb.cf.medicalapp.model.HealthTicket;
import gr.aueb.cf.medicalapp.model.Patient;
import gr.aueb.cf.medicalapp.service.DoctorService;
import gr.aueb.cf.medicalapp.service.HealthTicketService;
import gr.aueb.cf.medicalapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PatientController {

	/*---- SERVICES ----*/

	private final PatientService patientService;


	private final HealthTicketService healthTicketService;


	private final DoctorService doctorService;

	@Autowired
	public PatientController(PatientService patientService, HealthTicketService healthTicketService, DoctorService doctorService) {
		this.patientService = patientService;
		this.healthTicketService = healthTicketService;
		this.doctorService = doctorService;
	}
	/*-------------------------- PATIENT REGISTRATION --------------------------*/

	/*---- REGISTER PATIENT ----*/
	@GetMapping("/patient_register")
	public String showPatientRegistrationForm(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("pageTitle", "Register Patient");
		return "patient_register";
	}

	/*---- PROCESS REGISTER ----*/

	@PostMapping("/process_patient_register")
	public String processPatientRegister(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult, Model model) {
		boolean exists = patientService.patientExists(patient.getPatientID());
		if (exists) {
			model.addAttribute("pageTitle", "Registration Error");
			bindingResult.rejectValue("patientID", "patient.patientID", "ID already exists");
			return "patient_register";
		}

		if (patient.getDob() == null) {
			model.addAttribute("pageTitle", "Registration Error");
			bindingResult.rejectValue("dob", "patient.dob", "Birthday cannot be blank!");
			return "patient_register";
		}

		if (!patient.getPlainPassword().equals(patient.getPassword())) {
			model.addAttribute("pageTitle", "Registration Error");
			bindingResult.rejectValue("password", "patient.password", "Passwords do not match!");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", "Registration Error");
			return "patient_register";
		}

		String plainPassword = patient.getPlainPassword();
		patient.setPassword(plainPassword);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		patient.setPassword(encodedPassword);
		patientService.save(patient);


		return "redirect:/patient/login";
	}



	/*-------------------------- PATIENT (LOGIN && LOGOUT PAGE) --------------------------*/
	@RequestMapping("/patient/login")
	public String patientLogInPage(Patient patient, Model model) {
		model.addAttribute("pageTitle", "Patient Log In");
		return "patient_login";
	}

	@RequestMapping("/patient/logout")
	public String patientLogoutPage(Patient patient, Model model) {
		model.addAttribute("pageTitle", "Patient Logged Out");
		return "logged_out";
	}

	/*-------------------------- PATIENT (SIGNED IN) --------------------------*/
	/*---- VIEW PATIENT HOMEPAGE ----*/
	@RequestMapping("/patient/homepage")
	public String welcomePatient(Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());
		model.addAttribute("patient", patient);
		model.addAttribute("pageTitle", "Patient Homepage");
		return "patient_homepage";
	}

	/*---- EDIT PATIENT ACCOUNT INFO----*/
	@RequestMapping("/patient/edit/{patientID}")
	public ModelAndView showEditUserPage(@PathVariable(name = "patientID") String patientID, Model model) {
		ModelAndView mav = new ModelAndView("patient_edit");
		Patient patient = patientService.get(patientID);
		mav.addObject("patient", patient);
		model.addAttribute("pageTitle", "Edit Account");
		return mav;
	}

	/*---- PROCESS ACCOUNT EDIT ----*/
	@PostMapping("/process_patient_edit")
	public String processAccountUpdate(@Valid Patient patient, BindingResult bindingResult) {
		if (patient.getDob() == null) {
			bindingResult.rejectValue("dob", "patient.dob", "Date of birth required");
			return "patient_edit";
		}
		if (!(patient.getPlainPassword().contentEquals(patient.getPassword()))) {
			bindingResult.rejectValue("password", "patient.password", "Passwords do not match!");
		}
		if (bindingResult.hasErrors()) {
			return "patient_edit";
		} else {
			patient.setPassword(patient.getPlainPassword());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(patient.getPassword());
			patient.setPassword(encodedPassword);
			patientService.save(patient);
			return "redirect:/patient/homepage";
		}
	}

	/*---- DELETE ACCOUNT ----*/
	@RequestMapping("/patient/delete/{patientID}")
	public String deletePatient(@PathVariable(name = "patientID") String patientID) {
		Patient patient = patientService.get(patientID);

		healthTicketService.deleteAllByPatientID(patient);
		patientService.delete(patientID);
		return "redirect:/patient/logout";
	}

	/*-------------------------- PATIENT TICKETS --------------------------*/
	/*---- ADD PATIENT TICKET ----*/
	@RequestMapping("/patient/addTicket")
	public String addPatientTicket(Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());
		model.addAttribute("healthTicket", new HealthTicket());
		model.addAttribute("patient", patient);
		model.addAttribute("pageTitle", "Create Ticket");
		return "patient_add_ticket";
	}

	@PostMapping("/patient/processTicket")
	public String processTicket(@ModelAttribute("healthTicket") HealthTicket healthTicket, Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());
		healthTicket.setPatientID(patient);

		LocalDate localdate = LocalDate.now();
        healthTicket.setDateSubmitted(localdate);

		healthTicketService.save(healthTicket);
		return "redirect:/patient/homepage";

	}

	/*---- VIEW ALL PATIENT TICKETS ----*/
	@RequestMapping("/patient/viewTickets")
	public String listPatientTickets(Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());

		List<HealthTicket> healthTicketList = healthTicketService.getByPatient(patient);

		model.addAttribute("healthTicketList", healthTicketList);
		model.addAttribute("patient", patient);
		model.addAttribute("pageTitle", "View Tickets");
		return "patient_view_tickets";
	}

	@RequestMapping("patient/view-doctor/{doctorID}")
    public String viewPatient(@PathVariable(name = "doctorID") Doctor doctor, Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());
        String doctorID = doctor.getDoctorID();
        doctor = doctorService.get(doctorID);
        model.addAttribute("patient", patient);
        model.addAttribute("doctor", doctor);

        return "patient_view_doctor";
    }

	/*---- EDIT PATIENT TICKET ----*/
	@RequestMapping("/patient/edit-ticket/{ticketID}")
	public ModelAndView showEditPatientTicket(@PathVariable(name = "ticketID") int ticketID, Model model) {
		ModelAndView mav = new ModelAndView("patient_edit_ticket");
		HealthTicket healthTicket = healthTicketService.get(ticketID);
		mav.addObject("healthTicket", healthTicket);
		model.addAttribute("patient", new Patient());
		model.addAttribute("pageTitle", "Edit Ticket");
		return mav;
	}

	@PostMapping("/patient/processTicket/{ticketID}")
	public String processDiagnosis(@PathVariable(name = "ticketID") int ticketID,
			@ModelAttribute("healthTicket") HealthTicket healthTicket, Model model) {
		CustomPatientDetails patientInfo = (CustomPatientDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Patient patient = patientService.getLoggedInPatient(patientInfo.getPatientID());
		healthTicket.setPatientID(patient);

		LocalDate localdate = LocalDate.now();
        healthTicket.setDateSubmitted(localdate);

		healthTicket.setTicketID(ticketID);
		healthTicketService.update(healthTicket);

		return "redirect:/patient/viewTickets";
	}

	/*---- DELETE PATIENT TICKET ----*/
	@RequestMapping("/patient/delete-ticket/{ticketID}")
	public String deletePatientTicket(@PathVariable(name = "ticketID") int ticketID, Model model) {
		model.addAttribute("patient", new Patient());
		healthTicketService.delete(ticketID);
		return "redirect:/patient/viewTickets";
	}

}
