package ru.bellintegrator.practice.employee.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.user.dto.*;
import ru.bellintegrator.practice.employee.user.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller class for working with users
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    /**
     * Constructor
     *
     * @param userService (a service that provides methods of working with users)
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a filtered list of users
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    @ApiOperation(value = "Get user's list by filter", nickname = "getUserListById", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserFilterResponseDto> list(@RequestBody UserFilterRequestDto filter) {
        return userService.list(filter);
    }

    /**
     * Returns the user with the specified ID
     *
     * @param id (user id)
     * @return the user with the specified id
     */
    @ApiOperation(value = "Get user by id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    /**
     * Updates information about the user
     *
     * @param updateUser (an object containing information to update)
     */
    @ApiOperation(value = "Update user", nickname = "updateUser", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody UserUpdateDto updateUser) {
        userService.update(updateUser);
    }

    /**
     * Saves information about the new user
     *
     * @param saveUser (an object containing information about the new user)
     */
    @ApiOperation(value = "Save user", nickname = "saveUser", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody UserSaveDto saveUser) {
        userService.save(saveUser);
    }
}
