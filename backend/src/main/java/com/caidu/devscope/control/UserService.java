package com.caidu.devscope.control;

import com.caidu.devscope.dto.UserDTO;
import com.caidu.devscope.entity.User;
import com.caidu.devscope.exception.UserAlreadyExistsException;
import com.caidu.devscope.exception.UserNotFoundException;
import com.caidu.devscope.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(
                        user ->
                                new UserDTO(
                                        user.id,
                                        user.getUsername(),
                                        user.getEmail(),
                                        user.getKeycloakId()))
                .toList();
    }

    public UserDTO findById(Long id) {
        return userRepository
                .findByIdOptional(id)
                .map(
                        user ->
                                new UserDTO(
                                        user.id,
                                        user.getUsername(),
                                        user.getEmail(),
                                        user.getKeycloakId()))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userRepository.find("username", userDTO.username()).firstResultOptional().isPresent()) {
            throw new UserAlreadyExistsException(userDTO.username());
        }

        var user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setKeycloakId(userDTO.keycloakId());
        userRepository.persist(user);
        return new UserDTO(user.id, user.getUsername(), user.getEmail(), user.getKeycloakId());
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        var user =
                userRepository
                        .findByIdOptional(id)
                        .orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        if (userDTO.keycloakId() != null) {
            user.setKeycloakId(userDTO.keycloakId());
        }
        userRepository.persist(user);
        return new UserDTO(user.id, user.getUsername(), user.getEmail(), user.getKeycloakId());
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.deleteById(id);
    }
}
