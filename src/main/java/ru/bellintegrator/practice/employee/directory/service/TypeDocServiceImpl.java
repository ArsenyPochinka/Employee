package ru.bellintegrator.practice.employee.directory.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dao.TypeDocDao;
import ru.bellintegrator.practice.employee.directory.dto.TypeDocDto;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import java.util.List;

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
    public List<TypeDocDto> list() {
        List<TypeDocEntity> list = dao.list();
        return mapTypeDocList(list);
    }

    private List<TypeDocDto> mapTypeDocList(List<TypeDocEntity> entities) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(TypeDocEntity.class, TypeDocDto.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.mapAsList(entities, TypeDocDto.class);
    }
}