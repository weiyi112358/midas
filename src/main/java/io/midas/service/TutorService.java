package io.midas.service;

import io.midas.model.Tutor;
import io.midas.repository.TutorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    TutorDao tutorDao;

    public boolean save(Tutor t)
    {
        return tutorDao.save(t);


    }

}
