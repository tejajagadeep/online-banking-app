package com.onlinebanking.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Add this if you're auto-generating IDs
    private Long permissionId;

    @Column(unique = true, nullable = false)
    private String permissionName;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER)
    private Set<RolePermission> rolePermissions;
}
