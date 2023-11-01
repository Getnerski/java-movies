package br.com.movies.demo.service;

import br.com.movies.demo.model.User;
import br.com.movies.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        Optional<User> old = userRepository.findById(id);

        if (old.isPresent()) {
            User updated = old.get();
            updated.setName(user.getName());
            return userRepository.save(updated);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        if(findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

}
