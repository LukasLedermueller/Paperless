package at.fhtw.swkom.paperless.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AuthUserGroups {

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
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroup group;

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

    public AuthGroup getGroup() {
        return group;
    }

    public void setGroup(final AuthGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "AuthUserGroups{" + "\n" +
                "    id=" + id + "\n" +
                "    user=" + user + "\n" +
                "    group=" + group + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserGroups that = (AuthUserGroups) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, group);
    }
}
