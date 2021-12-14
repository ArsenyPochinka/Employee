package ru.bellintegrator.practice.employee.directory.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dao.CountryDao;
import ru.bellintegrator.practice.employee.directory.dto.CountryDto;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import java.util.List;

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
    public List<CountryDto> list() {
        List<CountryEntity> list = dao.list();
        return mapCountryList(list);
    }

    private List<CountryDto> mapCountryList(List<CountryEntity> entities) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(CountryEntity.class, CountryDto.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.mapAsList(entities, CountryDto.class);
    }
}
