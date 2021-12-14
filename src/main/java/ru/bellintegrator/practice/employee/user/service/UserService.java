package ru.bellintegrator.practice.employee.user.service;

import ru.bellintegrator.practice.employee.user.dto.*;

import java.util.List;

/**
 * An interface that provides methods for working with users
 */
public interface UserService {

    /**
     * Returns a filtered list of users
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    List<UserFilterResponseDto> list(UserFilterRequestDto filter);

    /**
     * Returns the user with the specified ID
     *
     * @param id (office id)
     * @return the user with the specified id
     */
    UserDto getById(Integer id);

    /**
     * Updates information about the user
     *
     * @param updateUser (object containing information to update)
     */
    void update(UserUpdateDto updateUser);

    /**
     * Saves information about the new user
     *
     * @param newUser (an object containing information about the new user)
     */
    void save(UserSaveDto newUser);
}
