package ru.bellintegrator.practice.employee.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithIdDto;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithOrgIdDto;
import ru.bellintegrator.practice.employee.office.dto.RequestParamsOfficeDto;
import ru.bellintegrator.practice.employee.office.dto.ResponseParamsOfficeDto;
import ru.bellintegrator.practice.employee.office.service.OfficeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/{id}")
    public OfficeWithIdDto getById(@PathVariable Integer id) {
        return officeService.getById(id);
    }

    @PostMapping("/list")
    public List<ResponseParamsOfficeDto> getParamsOrganization(@RequestBody RequestParamsOfficeDto request) {
        return officeService.getByParams(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody OfficeWithIdDto request) {
        officeService.update(request);
    }

    @PostMapping("/save")
    public void save(@RequestBody OfficeWithOrgIdDto request) {
        officeService.add(request);
    }
}
