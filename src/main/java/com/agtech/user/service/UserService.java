package com.agtech.user.service;

import com.agtech.core.exception.UserNotFoundException;
import com.agtech.user.model.User;
import com.agtech.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = findUserById(id);

        userRepository.deleteById(user.getId());
    }

    public User update(Integer id, User userUpdate) {
        User user = findUserById(id);

        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }

        if (userUpdate.getEmail() != null) {
            user.setEmail(userUpdate.getEmail());
        }

        return userRepository.save(user);
    }

    private User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                UserNotFoundException::new);
    }
}
