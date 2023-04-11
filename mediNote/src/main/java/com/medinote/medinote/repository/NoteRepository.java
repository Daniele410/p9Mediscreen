package com.medinote.medinote.repository;

import com.medinote.medinote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Note Repository
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

public List<Note> findByPatientId(long id);

}
