package ru.bellintegrator.practice.employee.user.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dao.CountryDao;
import ru.bellintegrator.practice.employee.directory.dao.TypeDocDao;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;
import ru.bellintegrator.practice.employee.exception.RecordNotFoundException;
import ru.bellintegrator.practice.employee.exception.WrongRequestException;
import ru.bellintegrator.practice.employee.office.dao.OfficeDao;
import ru.bellintegrator.practice.employee.user.dao.DocDao;
import ru.bellintegrator.practice.employee.user.dto.*;
import ru.bellintegrator.practice.employee.user.dao.UserDao;
import ru.bellintegrator.practice.employee.user.entity.DocEntity;
import ru.bellintegrator.practice.employee.user.entity.UserEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    private final OfficeDao officeDao;
    private final TypeDocDao typeDocDao;
    private final CountryDao countryDao;
    private final DocDao docDao;

    @Autowired
    public UserServiceImpl(UserDao dao, OfficeDao officeDao, TypeDocDao typeDocDao, CountryDao countryDao, DocDao docDao) {
        this.dao = dao;
        this.officeDao = officeDao;
        this.typeDocDao = typeDocDao;
        this.countryDao = countryDao;
        this.docDao = docDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserFilterResponseDto> list(UserFilterRequestDto filter) {
        if (filter == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateFilter(filter);
        UserEntity user = new UserEntity();
        user.setOfficeId(filter.getOfficeId());
        user.setFirstName(filter.getFirstName());
        user.setLastName(filter.getLastName());
        user.setMiddleName(filter.getMiddleName());
        user.setPosition(filter.getPosition());

        DocEntity doc = new DocEntity();
        TypeDocEntity typeDoc = new TypeDocEntity();
        typeDoc.setCode(filter.getDocCode());
        doc.setTypeDoc(typeDoc);
        user.setDoc(doc);

        CountryEntity country = new CountryEntity();
        country.setCode(filter.getCitizenshipCode());
        user.setCountry(country);

        List<UserEntity> list = dao.list(user);

        return mapUserFilterList(list);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Integer id) {
        if (id == null) {
            throw new WrongRequestException("Field ID is null.");
        }
        UserEntity user = dao.getById(id);
        if (user == null) {
            throw new RecordNotFoundException("Record with ID = " + id + " was not found in User.");
        }
        return mapUserDtoFromEntity(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserUpdateDto updateUser) {
        if (updateUser == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateUpdate(updateUser);
        dao.update(mapUserEntityFromUpdateDto(updateUser));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(UserSaveDto saveUser) {
        if (saveUser == null) {
            throw new WrongRequestException("Empty input data.");
        }
        validateSave(saveUser);
        UserEntity user = mapUserEntityFromSaveDto(saveUser);

        dao.save(user);

        TypeDocEntity typeDoc = new TypeDocEntity();

        if(saveUser.getDocName() != null || saveUser.getDocCode() != null) {
            if (typeDocDao.getByNameAndCode(saveUser.getDocName(), saveUser.getDocCode()) != null) {
                typeDoc = typeDocDao.getByNameAndCode(saveUser.getDocName(), saveUser.getDocCode());
            } else {
                typeDoc.setName(saveUser.getDocName());
                typeDoc.setCode(saveUser.getDocCode());
                typeDocDao.save(typeDoc);
            }
        }

        docDao.save(new DocEntity(user.getId(), saveUser.getDocNumber(), saveUser.getDocDate(), typeDoc));

    }

    private List<UserFilterResponseDto> mapUserFilterList(List<UserEntity> entities) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(UserEntity.class, UserFilterResponseDto.class).mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.mapAsList(entities, UserFilterResponseDto.class);
    }

    private UserDto mapUserDtoFromEntity(UserEntity entity) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserEntity.class, UserDto.class).exclude("officeId").exclude("doc").exclude("country").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        UserDto dto = mapperFacade.map(entity, UserDto.class);
        dto.setDocName(entity.getDoc().getTypeDoc().getName());
        dto.setDocNumber(entity.getDoc().getDocNumber());
        dto.setDocDate(entity.getDoc().getDocDate());
        dto.setCitizenshipName(entity.getCountry().getName());
        dto.setCitizenshipCode(entity.getCountry().getCode());

        return dto;
    }

    private UserEntity mapUserEntityFromUpdateDto(UserUpdateDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserUpdateDto.class, UserEntity.class).exclude("docName").exclude("docNumber").exclude("docDate").exclude("citizenshipCode").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        UserEntity entity = mapperFacade.map(dto, UserEntity.class);

        DocEntity doc = dao.getById(dto.getId()).getDoc();

        TypeDocEntity typeDoc = new TypeDocEntity();

        CountryEntity country = new CountryEntity();

        if(dto.getDocNumber() != null) {
            doc.setDocNumber(dto.getDocNumber());
        }
        if(dto.getDocDate() != null) {
            doc.setDocDate(dto.getDocDate());
        }
        if(dto.getDocName() != null) {
            if (typeDocDao.getByName(dto.getDocName()) != null) {
                doc.setTypeDoc(typeDocDao.getByName(dto.getDocName()));
            } else {
                typeDoc.setName(dto.getDocName());
                typeDocDao.save(typeDoc);
                doc.setTypeDoc(typeDoc);
            }
        }
        entity.setDoc(doc);
        if(dto.getCitizenshipCode() != null) {
            if (countryDao.getByCode(dto.getCitizenshipCode()) != null) {
                country = countryDao.getByCode(dto.getCitizenshipCode());
            } else {
                country.setCode(dto.getCitizenshipCode());
                countryDao.save(country);
            }
        }
        entity.setCountry(country);

        return entity;
    }

    private UserEntity mapUserEntityFromSaveDto(UserSaveDto dto) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserUpdateDto.class, UserEntity.class).exclude("docName").exclude("docCode").exclude("docNumber").exclude("docDate").exclude("citizenshipCode").mapNulls(false).byDefault().register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        UserEntity entity = mapperFacade.map(dto, UserEntity.class);

        CountryEntity country = new CountryEntity();

        if(dto.getCitizenshipCode() != null) {
            if (countryDao.getByCode(dto.getCitizenshipCode()) != null) {
                country = countryDao.getByCode(dto.getCitizenshipCode());
            } else {
                country.setCode(dto.getCitizenshipCode());
                countryDao.save(country);
            }
        }
        entity.setCountry(country);

        return entity;
    }

    private void validateFilter(UserFilterRequestDto filter) {
        if (filter.getOfficeId() == null) {
            throw new WrongRequestException("Field officeID is null.");
        }
    }

    private boolean isNameAndPositionValid(String name) {
        Pattern regex = Pattern.compile("[a-zA-Zа-яА-Я\"\\s-]{1,50}");
        if(name == null) {
            return true;
        }
        Matcher matcher = regex.matcher(name);
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
    private boolean isNumberValid(String number) {
        Pattern regex = Pattern.compile("[0-9\\s]{1,12}");
        if(number == null) {
            return true;
        }
        Matcher matcher = regex.matcher(number);
        return matcher.matches();
    }
    private boolean isCodeValid(String code) {
        Pattern regex = Pattern.compile("[0-9]{1,10}");
        if(code == null) {
            return true;
        }
        Matcher matcher = regex.matcher(code);
        return matcher.matches();
    }

    private void validateUpdate(UserUpdateDto updateUser) {
        StringBuilder message = new StringBuilder();
        if (updateUser.getId() == null) {
            message.append("Field ID is null. ");
        }
        if (updateUser.getFirstName() == null || !isNameAndPositionValid(updateUser.getFirstName())) {
            message.append("Field FIRSTNAME is null or invalid. ");
        }
        if (!isNameAndPositionValid(updateUser.getLastName())) {
            message.append("Field LAST NAME is invalid. ");
        }
        if (!isNameAndPositionValid(updateUser.getMiddleName())) {
            message.append("Field MIDDLE NAME is invalid. ");
        }
        if (updateUser.getPosition() == null || !isNameAndPositionValid(updateUser.getPosition())) {
            message.append("Field POSITION is null or invalid. ");
        }
        if (!isPhoneValid(updateUser.getPhone())) {
            message.append("Field PHONE is invalid. ");
        }
        if(!isNameAndPositionValid(updateUser.getDocName())) {
            message.append("Field DOC NAME is invalid. ");
        }
        if(!isNumberValid(updateUser.getDocNumber())) {
            message.append("Field DOC NUMBER is invalid. ");
        }
        if(!isCodeValid(updateUser.getCitizenshipCode())) {
            message.append("Field CITIZENSHIP CODE is invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (dao.getById(updateUser.getId()) == null) {
            throw new RecordNotFoundException("Record with ID (" + updateUser.getId() + ") wasn't found. ");
        }
    }

    private void validateSave(UserSaveDto saveUser) {
        StringBuilder message = new StringBuilder();
        if (saveUser.getOfficeId() == null) {
            message.append("Field officeID is null. ");
        }
        if (officeDao.getById(saveUser.getOfficeId()) == null) {
            throw new RecordNotFoundException("Office with ID (" + saveUser.getOfficeId() + ") wasn't found. ");
        }
        if (saveUser.getFirstName() == null || !isNameAndPositionValid(saveUser.getFirstName())) {
            message.append("Field FIRSTNAME is null or invalid. ");
        }
        if (!isNameAndPositionValid(saveUser.getLastName())) {
            message.append("Field LAST NAME is invalid. ");
        }
        if (!isNameAndPositionValid(saveUser.getMiddleName())) {
            message.append("Field MIDDLE NAME is invalid. ");
        }
        if (saveUser.getPosition() == null || !isNameAndPositionValid(saveUser.getPosition())) {
            message.append("Field POSITION is null or invalid. ");
        }
        if (!isPhoneValid(saveUser.getPhone())) {
            message.append("Field PHONE is invalid. ");
        }
        if(!isCodeValid(saveUser.getDocCode())) {
            message.append("Field DOC CODE is invalid. ");
        }
        if(!isNameAndPositionValid(saveUser.getDocName())) {
            message.append("Field DOC NAME is invalid. ");
        }
        if(!isNumberValid(saveUser.getDocNumber())) {
            message.append("Field DOC NUMBER is invalid. ");
        }
        if(!isCodeValid(saveUser.getCitizenshipCode())) {
            message.append("Field CITIZENSHIP CODE is invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
    }
}
