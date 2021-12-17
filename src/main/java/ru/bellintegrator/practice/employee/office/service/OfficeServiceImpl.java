package ru.bellintegrator.practice.employee.office.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.exception.RecordNotFoundException;
import ru.bellintegrator.practice.employee.exception.WrongRequestException;
import ru.bellintegrator.practice.employee.office.dao.OfficeDao;
import ru.bellintegrator.practice.employee.office.dto.*;
import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;
import ru.bellintegrator.practice.employee.organization.dao.OrganizationDao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao dao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao organizationDao) {
        this.dao = dao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeFilterResponseDto> list(OfficeFilterRequestDto filter) {
        if (filter == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateFilter(filter);
        OfficeEntity office = new OfficeEntity();
        office.setOrgId(filter.getOrgId());
        office.setName(filter.getName());
        office.setPhone(filter.getPhone());
        office.setIsActive(filter.getIsActive());
        List<OfficeEntity> list = dao.list(office);

        return mapOfficeFilterList(list);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeDto getById(Integer id) {
        if (id == null) {
            throw new WrongRequestException("Field ID is null.");
        }
        OfficeEntity office = dao.getById(id);
        if (office == null) {
            throw new RecordNotFoundException("Record with ID = " + id + " was not found in Office.");
        }
        return mapOfficeDtoFromEntity(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeUpdateDto updateOffice) {
        if (updateOffice == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateUpdate(updateOffice);
        dao.update(mapOfficeEntityFromUpdateDto(updateOffice));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeSaveDto saveOffice) {
        if (saveOffice == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateSave(saveOffice);
        dao.save(mapOfficeEntityFromSaveDto(saveOffice));
    }

    private List<OfficeFilterResponseDto> mapOfficeFilterList(List<OfficeEntity> entities) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(OfficeEntity.class, OfficeFilterResponseDto.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.mapAsList(entities, OfficeFilterResponseDto.class);
    }

    private OfficeDto mapOfficeDtoFromEntity(OfficeEntity entity) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OfficeEntity.class, OfficeDto.class).exclude("orgId").exclude("users").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(entity, OfficeDto.class);
    }

    private OfficeEntity mapOfficeEntityFromUpdateDto(OfficeUpdateDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OfficeUpdateDto.class, OfficeEntity.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(dto, OfficeEntity.class);
    }

    private OfficeEntity mapOfficeEntityFromSaveDto(OfficeSaveDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OfficeSaveDto.class, OfficeEntity.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(dto, OfficeEntity.class);
    }

    private void validateFilter(OfficeFilterRequestDto filter) {
        if (filter.getOrgId() == null) {
            throw new WrongRequestException("Field orgID is null.");
        }
    }

    private boolean isNameValid(String name) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я\"\\s-]{1,50}");
        if(name == null) {
            return true;
        }
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }

    private boolean isAddressValid(String address) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я0-9\"\\s,.-]{1,50}");
        if(address == null) {
            return true;
        }
        Matcher matcher = regex.matcher(address);
        return matcher.matches();
    }

    private boolean isPhoneValid(String phone) {
        Pattern regex = Pattern.compile("^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$");
        if(phone == null) {
            return true;
        }
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }

    private void validateUpdate(OfficeUpdateDto updateOffice) {
        StringBuilder message = new StringBuilder();
        if (updateOffice.getId() == null) {
            message.append("Field ID is null. ");
        }
        if (updateOffice.getName() == null || !isNameValid(updateOffice.getName())) {
            message.append("Field NAME is null or invalid. ");
        }
        if (updateOffice.getAddress() == null || !isAddressValid(updateOffice.getAddress())) {
            message.append("Field ADDRESS is null or invalid. ");
        }
        if (!isPhoneValid(updateOffice.getPhone())) {
            message.append("Field PHONE is invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (dao.getById(updateOffice.getId()) == null) {
            throw new RecordNotFoundException("Record with ID " + updateOffice.getId() + " wasn't found. ");
        }
    }

    private void validateSave(OfficeSaveDto saveOffice) {
        StringBuilder message = new StringBuilder();
        if (saveOffice.getOrgId() == null) {
            message.append("Field orgID is null. ");
        }
        if (organizationDao.getById(saveOffice.getOrgId()) == null) {
            throw new RecordNotFoundException("Organization with ID " + saveOffice.getOrgId() + " wasn't found. ");
        }
        if (!isNameValid(saveOffice.getName())) {
            message.append("Field NAME is invalid. ");
        }
        if (!isAddressValid(saveOffice.getAddress())) {
            message.append("Field ADDRESS is invalid. ");
        }
        if (!isPhoneValid(saveOffice.getPhone())) {
            message.append("Field PHONE is invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
    }
}
