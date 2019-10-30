package com.example.notes.Controller;


import com.example.notes.Dao.TaskDao;
import com.example.notes.Execption.ResourceExecption;
import com.example.notes.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private TaskDao taskDao;

    @GetMapping("/notes")
    public List<Notes> getAllNotes(){
        return taskDao.findAll();
    }

    @PostMapping("/notes")
    public Notes createNote(@Valid @RequestBody Notes note) {
        return taskDao.save(note);
    }

    @PutMapping("/notes/{id}")
    public Notes updateNote(@PathVariable(value = "id") Long id,@Valid @RequestBody Notes updateNote){
        Notes notes=taskDao.findById(id).orElseThrow(() -> new ResourceExecption("Note", "id", id));
        notes.setTask(updateNote.getTask());
        notes.setPriority(updateNote.getPriority());
        Notes update=taskDao.save(notes);
        return update;

    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable(value = "id") Long noteId) {
        Notes note = taskDao.findById(noteId)
                .orElseThrow(() -> new ResourceExecption("Note", "id", noteId));
        taskDao.delete(note);

    }

}
