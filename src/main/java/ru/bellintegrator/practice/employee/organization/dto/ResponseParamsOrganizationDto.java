package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

@Data
public class ResponseParamsOrganizationDto {

    public Integer id;

    public String name;

    public boolean isActive;

    public ResponseParamsOrganizationDto(Integer id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
