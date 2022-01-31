package com.booknotes.base.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
	
	@Autowired
	private UserService userService;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public NoteModel saveNote(NoteModel noteModel) {
		boolean stickyNote = noteModel.isSticky();
		noteModel.setSticky(false);
		Note noteObj = noteRepository.save(this.convertToNote(noteModel));
		if (stickyNote) {
			userService.markSticky(noteModel.getAuthorId(), noteObj.getId(), true);
		}
		return noteModel;
	}

	@Override
	public List<NoteModel> getAllNotes() {
		return noteRepository.findAllByOrderByIdDesc().stream().filter(note -> !note.isDeleted()).map(this::convertToNoteModel).collect(Collectors.toList());
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
	public NoteModel deleteNote(NoteModel noteModel, boolean softDelete) {
		if (softDelete) {
			Note note = noteRepository.findById(noteModel.getId()).get();
			note.setDeleted(true);
			noteRepository.save(note);
		} else {
			noteRepository.deleteById(noteModel.getId());	
		}
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
		Note note = noteRepository.findById(id).get();		
		return convertToNoteModel(note);
	}

	@Override
	public List<NoteModel> getAllPublishedNotes(long userId) {
		UserModel userModel = userService.getUserById(userId);
		String stickys = userModel.getMyStickyNotes();
		List<String> stickyNoteIds = new LinkedList<>();
		List<NoteModel> notes = new LinkedList<>();
		if (stickys != null && !stickys.isEmpty()) {
			for (String id : stickys.split(",")) {
				stickyNoteIds.add(id);
				NoteModel noteModel = this.getNote(Long.valueOf(id));
			 	if (!noteModel.isDeleted() && noteModel.isPublish()) {
					noteModel.setSticky(true);
					notes.add(noteModel);
				}			
			}
		}
		List<NoteModel> allNotes = noteRepository.findAllByOrderByIdDesc().stream().filter(note -> !note.isDeleted())
				.filter(note -> note.isPublish()).filter(note -> !note.isPrivateNote())
				.filter(note -> !stickyNoteIds.contains(String.valueOf(note.getId())))
				.map(this::convertToNoteModel).
				 map(note -> userModel.markIfSticky(note)).collect(Collectors.toList());
			 notes.addAll(allNotes);
			 return notes;
	}

	@Override
	public boolean publishNote(long noteId) {
		Note note = noteRepository.findById(noteId).get();
		note.setPublish(true);
		noteRepository.save(note);
		return true;
	}
	
	@Override
	public boolean notePrivacy(boolean privateState, long noteId) {
		Note note = noteRepository.findById(noteId).get();
		note.setPrivateNote(privateState);
				noteRepository.save(note);
		return true;
	}
	
	@Override
	public boolean unpublishNote(long noteId) {
		Note note = noteRepository.findById(noteId).get();
		note.setPublish(false);
		noteRepository.save(note);
		return true;
	}

	@Override
	public boolean vote(boolean up, long noteId) {
		Note note = noteRepository.findById(noteId).get();
		long upvoteCount = note.getUpvote();
		if (up) {
			upvoteCount = upvoteCount + 1; 
			note.setUpvote(upvoteCount);
		} else {
			upvoteCount = upvoteCount - 1; 
			note.setUpvote(upvoteCount);
		}
		note.setPublish(false);
		noteRepository.save(note);
		return true;
	}

	@Override
	public List<NoteModel> getAllMyNotes(long userId) {
		return noteRepository.findAllByOrderByIdDesc().stream().filter(note -> note.getAuthorId() == userId).filter(note -> !note.isDeleted()).map(this::convertToNoteModel).collect(Collectors.toList());
	}

	@Override
	public void updateNote(NoteModel noteModel) {
		Note note = noteRepository.findById(noteModel.getId()).get();
		if (noteModel.getTitle() != null) {
			note.setTitle(noteModel.getTitle());
		}
		if (noteModel.getDetails() != null) {
			note.setDetails(noteModel.getDetails());
		}
		noteRepository.save(note);
	}

	@Override
	public List<NoteModel> getAllNotesIncludingDeleted() {
		
		return noteRepository.findAllByOrderByIdDesc().stream().map(this::convertToNoteModel).collect(Collectors.toList());
	}

	@Override
	public List<NoteModel> searchNotesByTags(String tagContent) {
		String tags[] = tagContent != null && tagContent.length() > 0  ? tagContent.split("\\s") : null; 
		List<NoteModel> filteredNotes = noteRepository.findAllByOrderByIdDesc().stream().		
				filter(note -> !note.isDeleted()).
				filter(note -> note.isPublish()).
				map(this::convertToNoteModel).collect(Collectors.toList());
		// to do handle null pointer exception here
		for (String tag: tags) {
			List<NoteModel> tempList = filteredNotes.stream().filter(note -> note.getTags() != null && 
					note.getTags().toUpperCase().contains(tag.toUpperCase())).collect(Collectors.toList());
			filteredNotes = tempList;
		}
		return filteredNotes;
	}

	@Override
	public List<NoteModel> getTrendingNotes() {
		return noteRepository.findBydeletedFalseOrderByViewCountDesc().stream().
				//.filter(note -> !note.isDeleted()).
				map(this::convertToNoteModel).collect(Collectors.toList());

	}

	@Override
	public List<NoteModel> getAllPublishedNotesByEmail(String email) {
		return noteRepository.findByauthorNameIs(email).stream().filter(note -> !note.isDeleted()).
				map(this::convertToNoteModel).collect(Collectors.toList());
	}

	
}
