package com.example.notes.Dao;
import com.example.notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Notes,Long> {

}
