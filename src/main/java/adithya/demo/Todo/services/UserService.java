package adithya.demo.Todo.services;

import adithya.demo.Todo.models.User;
import adithya.demo.Todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User ent){
        return userRepository.save(ent);
    }
}
