package ru.bellintegrator.practice.employee.person.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.employee.person.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service
 */
@Validated
public interface PersonService {

    /**
     * Get Persons with specific attributes (id, firstName, secondName, middleName,  post)
     * by parameters officeId, firstName, secondName, middleName,  post, docCode, citizenshipCode
     *
     * @param request
     * @return List<PersonListResponseDto>
     */
    List<PersonListResponseDto> getByParams(@Valid PersonListRequestDto request);

    /**
     * Get Person by id
     *
     * @param id
     * @return PersonDto
     */
    PersonDto getById(@NotNull Integer id);

    /**
     * update Person by id
     *
     * @param personUpdateDto
     */
    void update(@Valid PersonUpdateDto personUpdateDto);

    /**
     * add new Person in DB
     *
     * @param personSaveDto
     */
    void add(@Valid PersonSaveDto personSaveDto);
}
