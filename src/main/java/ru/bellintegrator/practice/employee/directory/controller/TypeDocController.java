package ru.bellintegrator.practice.employee.directory.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    /**
     * Constructor
     *
     * @param typeDocService (a service that provides methods of working with type's docs)
     */
    @Autowired
    public TypeDocController(TypeDocService typeDocService) {
        this.typeDocService = typeDocService;
    }
    /**
     * Returns a list of type's doc
     *
     * @return list of type's doc
     */
    @ApiOperation(value = "Get of type's doc list", nickname = "getTypeDocList", httpMethod = "POST")
    @PostMapping("")
    public List<TypeDocDto> list() {
        return typeDocService.list();
    }
}
