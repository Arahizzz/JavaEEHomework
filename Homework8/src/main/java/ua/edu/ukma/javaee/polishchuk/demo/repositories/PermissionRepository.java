package ua.edu.ukma.javaee.polishchuk.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.ukma.javaee.polishchuk.demo.models.Permission;
import ua.edu.ukma.javaee.polishchuk.demo.models.PermissionEntity;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    @Query("select p from PermissionEntity p where p.permission = :permission")
    Optional<PermissionEntity> findByPermission(@Param("permission") Permission permission);
}
