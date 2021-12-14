package ru.bellintegrator.practice.employee.organization.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.exception.RecordNotFoundException;
import ru.bellintegrator.practice.employee.exception.WrongRequestException;
import ru.bellintegrator.practice.employee.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.employee.organization.dto.*;
import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationFilterResponseDto> list(OrganizationFilterRequestDto filter) {
        if (filter == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateFilter(filter);
        OrganizationEntity organization = new OrganizationEntity();
        organization.setName(filter.getName());
        organization.setInn(filter.getInn());
        organization.setIsActive(filter.getIsActive());
        List<OrganizationEntity> list = dao.list(organization);

        return mapOrganizationFilterList(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDto getById(Integer id) {
        if (id == null) {
            throw new WrongRequestException("Field ID is null.");
        }
        OrganizationEntity organizationEntity = dao.getById(id);
        if (organizationEntity == null) {
            throw new RecordNotFoundException("Record with ID = " + id + " was not found in Organization.");
        }
        return mapOrganizationDtoFromEntity(organizationEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationUpdateDto updateOrganization) {
        if (updateOrganization == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateUpdate(updateOrganization);
        dao.update(mapOrganizationEntityFromUpdateDto(updateOrganization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationSaveDto saveOrganization) {
        if (saveOrganization == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateSave(saveOrganization);
        dao.save(mapOrganizationEntityFromSaveDto(saveOrganization));
    }

    private List<OrganizationFilterResponseDto> mapOrganizationFilterList(List<OrganizationEntity> entities) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(OrganizationEntity.class, OrganizationFilterResponseDto.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.mapAsList(entities, OrganizationFilterResponseDto.class);
    }

    private OrganizationDto mapOrganizationDtoFromEntity(OrganizationEntity entity) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OrganizationEntity.class, OrganizationDto.class).exclude("offices").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(entity, OrganizationDto.class);
    }

    private OrganizationEntity mapOrganizationEntityFromUpdateDto(OrganizationUpdateDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OrganizationUpdateDto.class, OrganizationEntity.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(dto, OrganizationEntity.class);
    }

    private OrganizationEntity mapOrganizationEntityFromSaveDto(OrganizationSaveDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OrganizationSaveDto.class, OrganizationEntity.class).exclude("id").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(dto, OrganizationEntity.class);
    }

    private void validateFilter(OrganizationFilterRequestDto filter) {
        if (filter.getName() == null) {
            throw new WrongRequestException("Field NAME is null.");
        }
    }

    private boolean isNameValid(String name) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я\"\\s-]{1,50}");
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }

    private boolean isFullNameValid(String fullName) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я\"\\s,.-]{1,50}");
        Matcher matcher = regex.matcher(fullName);
        return matcher.matches();
    }

    private boolean isInnValid(String inn) {
        Pattern regex = Pattern.compile("[0-9]{12}");
        Matcher matcher = regex.matcher(inn);
        return matcher.matches();
    }

    private boolean isKppValid(String kpp) {
        Pattern regex = Pattern.compile("[0-9]{9}");
        Matcher matcher = regex.matcher(kpp);
        return matcher.matches();
    }

    private boolean isAddressValid(String address) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я0-9\"\\s,.-]{1,50}");
        Matcher matcher = regex.matcher(address);
        return matcher.matches();
    }

    private boolean isPhoneValid(String phone) {
        Pattern regex = Pattern.compile("^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$");
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }

    private void validateUpdate(OrganizationUpdateDto updateOrganization) {
        StringBuilder message = new StringBuilder();
        if (updateOrganization.getId() == null) {
            message.append("Field ID is null.");
        }
        if (updateOrganization.getName() == null || !isNameValid(updateOrganization.getName())) {
            message.append("Field NAME is null or invalid.");
        }
        if (updateOrganization.getFullName() == null || !isFullNameValid(updateOrganization.getFullName())) {
            message.append("Field FULLNAME is null or invalid.");
        }
        if (updateOrganization.getInn() == null || !isInnValid(updateOrganization.getInn())) {
            message.append("Field INN is null or invalid.");
        }
        if (updateOrganization.getKpp() == null || !isKppValid(updateOrganization.getKpp())) {
            message.append("Field KPP is null or invalid.");
        }
        if (updateOrganization.getAddress() == null || !isAddressValid(updateOrganization.getAddress())) {
            message.append("Field ADDRESS is null or invalid.");
        }
        if (!isPhoneValid(updateOrganization.getPhone())) {
            message.append("Field PHONE is invalid.");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (dao.getById(updateOrganization.getId()) == null) {
            throw new RecordNotFoundException("Record with ID " + updateOrganization.getId() + " wasn't found.\n");
        }
    }

    private void validateSave(OrganizationSaveDto saveOrganization) {
        StringBuilder message = new StringBuilder();
        if (saveOrganization.getName() == null || !isNameValid(saveOrganization.getName())) {
            message.append("Field NAME is null or invalid.");
        }
        if (saveOrganization.getFullName() == null || !isFullNameValid(saveOrganization.getFullName())) {
            message.append("Field FULLNAME is null or invalid.");
        }
        if (saveOrganization.getInn() == null || !isInnValid(saveOrganization.getInn())) {
            message.append("Field INN is null or invalid.");
        }
        if (saveOrganization.getKpp() == null || !isKppValid(saveOrganization.getKpp())) {
            message.append("Field KPP is null or invalid.");
        }
        if (saveOrganization.getAddress() == null || !isAddressValid(saveOrganization.getAddress())) {
            message.append("Field ADDRESS is null or invalid.");
        }
        if (!isPhoneValid(saveOrganization.getPhone())) {
            message.append("Field PHONE is invalid.");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
    }
}
