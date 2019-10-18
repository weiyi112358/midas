package io.midas.repository;

import io.midas.model.Tutor;

import java.util.List;

public interface TutorDao {
    boolean save(Tutor tutor);
    boolean update (Tutor tutor);
    //TODO delete(Tutor stu)
    boolean delete(String tutorName);
    Tutor getTutorByName(String tutorName);
    List<Tutor> getTutors();
}
