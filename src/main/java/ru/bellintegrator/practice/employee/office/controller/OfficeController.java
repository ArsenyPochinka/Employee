package ru.bellintegrator.practice.employee.office.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.office.dto.*;
import ru.bellintegrator.practice.employee.office.service.OfficeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller class for working with offices
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Constructor
     *
     * @param officeService (a service that provides methods of working with offices)
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Returns a filtered list of offices
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    @ApiOperation(value = "Get office's list by filter", nickname = "getOfficeListById", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeFilterResponseDto> list(@RequestBody OfficeFilterRequestDto filter) {
        return officeService.list(filter);
    }

    /**
     * Returns the office with the specified ID
     *
     * @param id (office id)
     * @return the office with the specified id
     */
    @ApiOperation(value = "Get office by id", nickname = "getOfficeById", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeDto getById(@PathVariable Integer id) {
        return officeService.getById(id);
    }

    /**
     * Updates information about the office
     *
     * @param updateOffice (an object containing information to update)
     */
    @ApiOperation(value = "Update office", nickname = "updateOffice", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody OfficeUpdateDto updateOffice) {
        officeService.update(updateOffice);
    }

    /**
     * Saves information about the new office
     *
     * @param saveOffice (an object containing information about the new office)
     */
    @ApiOperation(value = "Save office", nickname = "saveOffice", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody OfficeSaveDto saveOffice) {
        officeService.save(saveOffice);
    }
}
