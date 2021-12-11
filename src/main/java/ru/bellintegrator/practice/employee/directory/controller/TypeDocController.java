package ru.bellintegrator.practice.employee.directory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.employee.directory.dto.TypeDocDto;
import ru.bellintegrator.practice.employee.directory.service.TypeDocService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class TypeDocController {

    private final TypeDocService typeDocService;

    @Autowired
    public TypeDocController(TypeDocService typeDocService) {
        this.typeDocService = typeDocService;
    }

    @PostMapping("")
    public List<TypeDocDto> getGuideTypeDocs(@RequestBody TypeDocDto request) {
        return typeDocService.getByParams(request);
    }
}
