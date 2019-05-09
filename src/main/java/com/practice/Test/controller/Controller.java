package com.practice.Test.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Test.exception.ResourceNotFoundException;
import com.practice.Test.model.Note;
import com.practice.Test.model.Text;
import com.practice.Test.model.TextRequest;
import com.practice.Test.repository.NoteRepository;

@RestController
public class Controller {
	
	 Logger logger = LoggerFactory.getLogger(Controller.class);
	 
	 @Autowired
	 NoteRepository noteRepository;
	 

	@RequestMapping(value="/greeting", method = RequestMethod.POST)
	public Text getData(@RequestBody TextRequest request) {
		Text textFil = new Text(); 
		textFil.setId(request.getId());
		textFil.setMessage("Hi "+request.getName());
		
		logger.info(request.toString());
		return textFil;
	}
		
	
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
	    
		return noteRepository.save(note);
	}
	
	// Get All Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
	    return noteRepository.findAll();
	}
	
	// Get one note
	@GetMapping("/notes/{id}")
	public Note getNote(@PathVariable(value="id") Long noteid) {
	   
		return noteRepository.findById(noteid).orElseThrow(()-> new ResourceNotFoundException("Note","id",noteid));
	}
	
	// Update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId,
	                                        @Valid @RequestBody Note noteDetails) {

	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());

	    Note updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
	
	// Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
}
