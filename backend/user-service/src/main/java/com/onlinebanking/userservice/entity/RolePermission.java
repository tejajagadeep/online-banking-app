package com.onlinebanking.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_permissions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) // This is the foreign key column in the DB
    private Role role; // Reference to the Role entity, not roleId

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false) // This is the foreign key column in the DB
    private Permission permission; // Reference to the Permission entity, not permissionId
}
