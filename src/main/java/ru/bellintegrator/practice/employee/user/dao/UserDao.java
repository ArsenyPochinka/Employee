package ru.bellintegrator.practice.employee.user.dao;

import ru.bellintegrator.practice.employee.user.entity.UserEntity;

import java.util.List;

/**
 * DAO for working with User
 */
public interface UserDao {
    /**
     * Returns a filtered list of users
     *
     * @param filter (object with filtering data)
     * @return filtered list of users
     */
    List<UserEntity> list(UserEntity filter);

    /**
     * Returns the user with the specified ID
     *
     * @param id (user id)
     * @return user with the specified id
     */
    UserEntity getById(Integer id);

    /**
     * Updates information about the user
     *
     * @param updateUser (object with new user data)
     */
    void update(UserEntity updateUser);

    /**
     * Saves information about the new user
     *
     * @param newUser (object with data about the new user)
     */
    void save(UserEntity newUser);
}

