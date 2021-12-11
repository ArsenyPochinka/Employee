package ru.bellintegrator.practice.employee.directory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dao.CountryDao;
import ru.bellintegrator.practice.employee.directory.dto.CountryDto;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryDto> all() {
        List<CountryEntity> countryEntities = dao.all();
        return countryEntities.stream().map(ce -> new CountryDto(ce.getName(), ce.getCode())).collect(Collectors.toList());
    }
}
