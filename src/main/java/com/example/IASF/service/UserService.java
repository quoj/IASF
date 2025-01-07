package com.example.IASF.service;

import com.example.IASF.dto.req.UserReq;
import com.example.IASF.dto.res.UserRes;
import com.example.IASF.entity.User;
import com.example.IASF.mapper.UserMapper;
import com.example.IASF.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public List<UserRes> all(){
        return userRepository.findAll().stream().map(
                userMapper::toDTO
        ).toList();
    }
    public UserRes create(UserReq user){
        return userMapper.toDTO(
                userRepository.save(
                        userMapper.toEntity(user)));
    }
    public List<UserRes> searchByName(String s){
        return userRepository.findAllByNameContains(s)
                .stream().map(userMapper::toDTO).toList();
    }
    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

    public UserRes update(Integer id, UserReq userReq) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }

        User existingUser = optionalUser.get();
        existingUser.setName(userReq.getName());
        existingUser.setAge(userReq.getAge());
        existingUser.setSalary(userReq.getSalary());

        return userMapper.toDTO(userRepository.save(existingUser));
    }
}
