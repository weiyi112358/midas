package io.midas.repository;

import io.midas.model.Organization;

import java.util.List;

public interface OrganizationDao {
    boolean save(Organization organization);
    boolean update (Organization organization);
    //TODO delete(Organization stu)
    boolean delete(String organizationName);
    Organization getOrganizationByName(String organizationName);
    List<Organization> getOrganizations();
    //List<Organization> getOrganizationsWithChildren();
}
