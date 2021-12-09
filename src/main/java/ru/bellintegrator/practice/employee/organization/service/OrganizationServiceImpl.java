package ru.bellintegrator.practice.employee.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.employee.organization.dto.*;
import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ResponseParamsOrganizationDto> getByParams(RequestParamsOrganizationDto request) {
        List<OrganizationEntity> organizationEntities = dao.loadByParams(request.getName(), request.getInn(), request.getIsActive());
        return organizationEntities.stream().map(oe -> new ResponseParamsOrganizationDto(oe.getId(), oe.getName(), oe.getIsActive())).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDto getById(Integer id) {
        OrganizationEntity organizationEntity = dao.loadById(id);
        return new OrganizationDto(organizationEntity.getId(), organizationEntity.getName(), organizationEntity.getFullName(), organizationEntity.getInn(), organizationEntity.getKpp(), organizationEntity.getAddress(), organizationEntity.getPhone(), organizationEntity.getIsActive());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationDto organizationDto) {
        OrganizationEntity newOrganizationEntity = new OrganizationEntity(organizationDto.getId(), organizationDto.getName(), organizationDto.getFullName(), organizationDto.getInn(), organizationDto.getKpp(), organizationDto.getAddress(), organizationDto.getPhone(), organizationDto.getIsActive());
        dao.update(newOrganizationEntity);
    }

    @Override
    @Transactional
    public void add(OrganizationWithoutIdDto organizationWithoutIdDto) {
        OrganizationEntity newOrganizationEntity = new OrganizationEntity(organizationWithoutIdDto.getName(), organizationWithoutIdDto.getFullName(), organizationWithoutIdDto.getInn(), organizationWithoutIdDto.getKpp(), organizationWithoutIdDto.getAddress(), organizationWithoutIdDto.getPhone(), organizationWithoutIdDto.getIsActive());
        dao.save(newOrganizationEntity);
    }
}
