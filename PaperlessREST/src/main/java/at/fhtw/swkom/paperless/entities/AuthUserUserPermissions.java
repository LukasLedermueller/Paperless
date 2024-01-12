package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AuthUserUserPermissions {

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
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private AuthPermission permission;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(final AuthUser user) {
        this.user = user;
    }

    public AuthPermission getPermission() {
        return permission;
    }

    public void setPermission(final AuthPermission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "AuthUserUserPermissions{" + "\n" +
                "    id=" + id + "\n" +
                "    user=" + user + "\n" +
                "    permission=" + permission + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserUserPermissions that = (AuthUserUserPermissions) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, permission);
    }
}
