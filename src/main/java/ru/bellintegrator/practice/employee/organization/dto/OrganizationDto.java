package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

    /**
     * Organization DTO
     */
    @Data
    public class OrganizationDto {
        /**
         * Unique organization identifier
         */
        private Integer id;
        /**
         * name of Organization
         */
        private String name;
        /**
         * full name  of Organization
         */
        private String fullName;
        /**
         * inn of Organization
         */
        private String inn;
        /**
         * kpp of Organization
         */
        private String kpp;
        /**
         * address of Organization
         */
        private String address;
        /**
         * phone  of Organization
         */
        private String phone;
        /**
         * Is the organization in operation (is_active = true / false)
         */
        private Boolean isActive;
    }
