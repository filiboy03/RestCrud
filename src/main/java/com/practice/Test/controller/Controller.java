package com.practice.Test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Test.model.Text;
import com.practice.Test.model.TextRequest;

@RestController
public class Controller {
	
	 Logger logger = LoggerFactory.getLogger(Controller.class);
	 

	@RequestMapping(value="/greeting", method = RequestMethod.POST)
	public Text getData(@RequestBody TextRequest request) {
		Text textFil = new Text(); 
		textFil.setId(request.getId());
		textFil.setMessage("Hi "+request.getName());
		
		logger.info(request.toString());
		return textFil;
	}
		
	
/*	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
	    return noteRepository.save(note);
	}*/
}
