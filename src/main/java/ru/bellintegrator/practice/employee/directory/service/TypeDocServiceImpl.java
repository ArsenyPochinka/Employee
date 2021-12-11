package ru.bellintegrator.practice.employee.directory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dao.TypeDocDao;
import ru.bellintegrator.practice.employee.directory.dto.TypeDocDto;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class TypeDocServiceImpl implements TypeDocService {
    private final TypeDocDao dao;

    @Autowired
    public TypeDocServiceImpl(TypeDocDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeDocDto> getByParams(TypeDocDto typeDocDto) {
        List<TypeDocEntity> typeDocEntities = dao.loadByParams(typeDocDto.getName(), typeDocDto.getCode());
        return typeDocEntities.stream().map(ce -> new TypeDocDto(ce.getId(), ce.getName(), ce.getCode())).collect(Collectors.toList());
    }
}

