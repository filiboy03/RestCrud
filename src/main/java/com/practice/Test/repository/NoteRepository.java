package com.practice.Test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.Test.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
	

}
