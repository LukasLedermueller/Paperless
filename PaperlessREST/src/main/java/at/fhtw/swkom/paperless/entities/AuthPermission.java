package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class AuthPermission {

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String contentType;

    @Column(nullable = false, length = 100)
    private String codename;

    @OneToMany(mappedBy = "permission")
    private Set<AuthGroupPermissions> permissionAuthGroupPermissionses;

    @OneToMany(mappedBy = "permission")
    private Set<AuthUserUserPermissions> permissionAuthUserUserPermissionses;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(final String codename) {
        this.codename = codename;
    }

    public Set<AuthGroupPermissions> getPermissionAuthGroupPermissionses() {
        return permissionAuthGroupPermissionses;
    }

    public void setPermissionAuthGroupPermissionses(
            final Set<AuthGroupPermissions> permissionAuthGroupPermissionses) {
        this.permissionAuthGroupPermissionses = permissionAuthGroupPermissionses;
    }

    public Set<AuthUserUserPermissions> getPermissionAuthUserUserPermissionses() {
        return permissionAuthUserUserPermissionses;
    }

    public void setPermissionAuthUserUserPermissionses(
            final Set<AuthUserUserPermissions> permissionAuthUserUserPermissionses) {
        this.permissionAuthUserUserPermissionses = permissionAuthUserUserPermissionses;
    }
    @Override
    public String toString() {
        return "AuthPermission{" + "\n" +
                "    id=" + id + "\n" +
                "    name='" + name + '\'' + "\n" +
                "    contentType='" + contentType + '\'' + "\n" +
                "    codename='" + codename + '\'' + "\n" +
                "    permissionAuthGroupPermissionses=" + permissionAuthGroupPermissionses + "\n" +
                "    permissionAuthUserUserPermissionses=" + permissionAuthUserUserPermissionses + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthPermission that = (AuthPermission) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(contentType, that.contentType) && Objects.equals(codename, that.codename) && Objects.equals(permissionAuthGroupPermissionses, that.permissionAuthGroupPermissionses) && Objects.equals(permissionAuthUserUserPermissionses, that.permissionAuthUserUserPermissionses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, codename, permissionAuthGroupPermissionses, permissionAuthUserUserPermissionses);
    }
}
