package ru.bellintegrator.practice.employee.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.employee.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.RequestParamsOrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.ResponseParamsOrganizationDto;
import ru.bellintegrator.practice.employee.organization.entity.Organization;

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
    public ResponseParamsOrganizationDto getParamsOrganization(RequestParamsOrganizationDto request) {
        Organization organization = dao.loadByName(request.getName());
        ResponseParamsOrganizationDto response = new ResponseParamsOrganizationDto(organization.getId(), organization.getName(), organization.isActive());
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDto getById(Integer id) {
        Organization organization = dao.loadById(id);
        OrganizationDto response = new OrganizationDto(organization.getId(), organization.getName(), organization.getFullName(), organization.getInn(), organization.getKpp(), organization.getAddress(), organization.getPhone(), organization.isActive());
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationDto organizationDto) {
       dao.update(organizationDto.getId(), organizationDto.getName(), organizationDto.getFullName(), organizationDto.getInn(), organizationDto.getKpp(), organizationDto.getAddress(), organizationDto.getPhone(), organizationDto.isActive());
       // return number of changed
    }

    @Override
    @Transactional
    public void add(OrganizationDto organizationDto) {
        Organization newOrganization = new Organization(organizationDto.getId(), organizationDto.getName(), organizationDto.getFullName(), organizationDto.getInn(), organizationDto.getKpp(), organizationDto.getAddress(), organizationDto.getPhone(), organizationDto.isActive());
        dao.save(newOrganization);
        // return number of changed
    }
}
