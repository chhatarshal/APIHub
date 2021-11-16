package com.booknotes.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booknotes.base.entity.Note;
import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.NoteModel;
import com.booknotes.base.model.UserModel;
import com.booknotes.base.repository.NoteRepository;


@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public NoteModel saveNote(NoteModel noteModel) {
		noteRepository.save(this.convertToNote(noteModel));
		return noteModel;
	}

	@Override
	public List<NoteModel> getAllNotes() {
		return noteRepository.findAllByOrderByIdDesc().stream().map(this::convertToNoteModel).collect(Collectors.toList());
	}

	@Override
	public List<NoteModel> getAllNotesForUser(UserModel userModel) {
		System.out.println("This method should go in user repository");
		return null;
	}

	@Override
	public List<NoteModel> getAllNotesForBook(BookModel bookModel) {
		System.out.println("This method should go in user repository");
		return null;
	}

	@Override
	public NoteModel deleteNote(NoteModel noteModel) {
		noteRepository.deleteById(noteModel.getId());
		return noteModel;
	}
	
	private Note convertToNote(NoteModel noteModel) {
		Note note = modelMapper.map(noteModel, Note.class);	    
	    return note;
	}
	
	private NoteModel convertToNoteModel(Note note) {
		NoteModel noteModel = modelMapper.map(note, NoteModel.class);	    
	    return noteModel;
	}

	@Override
	public NoteModel getNote(long id) {
		return convertToNoteModel(noteRepository.findById(id).get());
	}

}
