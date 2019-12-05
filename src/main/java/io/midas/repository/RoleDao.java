package io.midas.repository;

import io.midas.model.Role;

public interface RoleDao {

    Role getRoleByName(String name);

}
