package at.fhtw.swkom.paperless.entities;


import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class AuthGroupPermissions {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private AuthPermission permission;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public AuthGroup getGroup() {
        return group;
    }

    public void setGroup(final AuthGroup group) {
        this.group = group;
    }

    public AuthPermission getPermission() {
        return permission;
    }

    public void setPermission(final AuthPermission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "AuthGroupPermissions{" + "\n" +
                "    id=" + id + "\n" +
                "    group=" + group + "\n" +
                "    permission=" + permission + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthGroupPermissions that = (AuthGroupPermissions) o;
        return Objects.equals(id, that.id) && Objects.equals(group, that.group) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, permission);
    }
}
