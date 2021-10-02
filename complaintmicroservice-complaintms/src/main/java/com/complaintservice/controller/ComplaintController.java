package com.complaintservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaintservice.entity.Complaint;
import com.complaintservice.exception.ComplaintAlreadyExistException;
import com.complaintservice.exception.ComplaintNotFoundException;
import com.complaintservice.service.ComplaintService;
import com.complaintservice.service.MapValidationErrorService;
import com.complaintservice.utilities.GlobalLogger;

/**
 * 
 * @author Ragini
 *
 */
@RestController
@RequestMapping(path= "/complaint")

public class ComplaintController {
	// Creating reference (it creates loosely coupled application)
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Logger used o track the log.
	 */
	private static final Logger logger = GlobalLogger.getLogger(ComplaintController.class);
	
	/**
	 * http://localhost:9091/Farm-api/complaint/viewAllComplaint     
	   View all the complaints
	 */
	@GetMapping("/viewAllComplaint")    
	public ResponseEntity<List<Complaint>> getAllComplaints() throws ComplaintNotFoundException{
		logger.info("Trying to fetch Complaint list");
		try {
			List<Complaint> complaint=complaintService .getAllComplaint();
			if(complaint.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<>(complaint,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * http://localhost:9091/Farm-api/complaint/addComplaint   
	 * Adding complaint
	 */
	@PostMapping("/addComplaint")
	public ResponseEntity<?> addComplaint(@RequestBody Complaint complaint,BindingResult result){
		logger.info("Inside Controller Add Complaint");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		try {
			Complaint addComplaint=complaintService.addComplaint(complaint);
			return new ResponseEntity<Complaint>(addComplaint, HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ComplaintAlreadyExistException("You are not filled all the information ");
		}
	}
	
	/**
	 * http://localhost:9091/Farm-api/complaint/{complaintId}   
	 * detete mapping
	 */
	@DeleteMapping("/{complaintId}")
	public ResponseEntity<?> deleteComplaint(@PathVariable Long complaintId){
		logger.info("Inside Controller Delete Complaint");
		complaintService.deleteComplaintById(complaintId);
		return new ResponseEntity<String>("Complaint Id  " + complaintId + " is deleted", HttpStatus.OK);
	}

	
	/**
	 * http://localhost:9091/Farm-api/complaint/updateComplaint     
	 * Update Complaint
	 */
	@PutMapping("/updateComplaint")
	public ResponseEntity<Complaint>updateComplaint(@RequestBody Complaint complaint){
		logger.info("Inside controller Update Complaint");
		Complaint updateComplaint=complaintService.addComplaint(complaint);
		if(complaint!=null)
			return new ResponseEntity<>(updateComplaint,HttpStatus.CREATED);
		else
			return new ResponseEntity<>(updateComplaint,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	/**
	 *  http://localhost:9091/Farm-api/complaint/{complaintId}   
	 *  View complaint by id
	 */
		@GetMapping("/{complaintId}")
		public ResponseEntity<?> getComplaintById(@PathVariable int complaintId){
			logger.info("Inside Controller View Complaint ById");
			Complaint foundcomplaint = complaintService.getComplaintById(complaintId);
			return new ResponseEntity<Complaint>(foundcomplaint, HttpStatus.OK);
		}
}
