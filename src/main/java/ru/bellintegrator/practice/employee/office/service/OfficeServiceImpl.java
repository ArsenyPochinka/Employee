package ru.bellintegrator.practice.employee.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.office.dao.OfficeDao;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithIdDto;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithOrgIdDto;
import ru.bellintegrator.practice.employee.office.dto.RequestParamsOfficeDto;
import ru.bellintegrator.practice.employee.office.dto.ResponseParamsOfficeDto;
import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService{
    private final OfficeDao dao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseParamsOfficeDto> getByParams(RequestParamsOfficeDto request) {
        List<OfficeEntity> officeEntities = dao.loadByParams(request.getOrganizationId(), request.getName(), request.getPhone(), request.getIsActive());
        return officeEntities.stream().map(oe -> new ResponseParamsOfficeDto(oe.getId(), oe.getName(), oe.getIsActive())).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeWithIdDto getById(Integer id) {
        OfficeEntity officeEntity = dao.loadById(id);
        return new OfficeWithIdDto(officeEntity.getId(), officeEntity.getName(), officeEntity.getAddress(), officeEntity.getPhone(), officeEntity.getIsActive());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeWithIdDto officeWithIdDto) {
        OfficeEntity officeEntity = new OfficeEntity(officeWithIdDto.getName(), officeWithIdDto.getAddress(), officeWithIdDto.getPhone(), officeWithIdDto.getIsActive());
        officeEntity.setId(officeWithIdDto.getId());
        dao.update(officeEntity);
    }

    @Override
    @Transactional
    public void add(OfficeWithOrgIdDto officeWithOrgIdDto) {
        OfficeEntity officeEntity = new OfficeEntity(officeWithOrgIdDto.getOrganizationId(), officeWithOrgIdDto.getName(), officeWithOrgIdDto.getAddress(), officeWithOrgIdDto.getPhone(), officeWithOrgIdDto.getIsActive());
        dao.save(officeEntity);
    }
}
