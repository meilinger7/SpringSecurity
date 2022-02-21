package at.htlimst.springsecurity.repository;

import at.htlimst.springsecurity.model.Role;
import at.htlimst.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
