package ru.bellintegrator.practice.employee.directory.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.directory.dto.CountryDto;
import ru.bellintegrator.practice.employee.directory.service.CountryService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;
    /**
     * Constructor
     *
     * @param countryService (a service that provides methods of working with countries)
     */
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    /**
     * Returns a list of countries and their codes
     *
     * @return list of countries and their codes
     */
    @ApiOperation(value = "Get countries list", nickname = "getCountriesList", httpMethod = "POST")
    @PostMapping("")
    public List<CountryDto> list() {
        return countryService.list();
    }
}
