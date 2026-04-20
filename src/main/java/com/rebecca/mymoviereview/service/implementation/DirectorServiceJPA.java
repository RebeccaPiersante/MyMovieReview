package com.rebecca.mymoviereview.service.implementation;

import com.rebecca.mymoviereview.model.Director;
import com.rebecca.mymoviereview.repository.DirectorRepository;
import com.rebecca.mymoviereview.service.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceJPA implements DirectorService {

    private final DirectorRepository repository;

    @Autowired
    public DirectorServiceJPA(DirectorRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Director> findAllDirector() {
        return repository.findAll();
    }

    @Override
    public Optional<Director> findDirectorById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteDirectorById(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateDirector(int id, Director update) {
        if(repository.existsById(id) && id == update.getId()){
            repository.save(update);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Director createDirector(Director create) {
        create.setId(null);
        return repository.save(create);
    }

    @Override
    public Optional<Director> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname) {
        return repository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase(firstname, lastname);
    }
}
