package com.webms.demo.services;

import com.webms.demo.Repositories.UserRepository;
import com.webms.demo.dao.UserDao;
import com.webms.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findOne(int id) {
        return repository.findById(Integer.valueOf(id));
    }

    public User addUser(User u) {
        return repository.save(u);
    }
}
