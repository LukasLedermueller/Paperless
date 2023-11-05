package at.fhtw.swkom.paperless.entities;


import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class AuthtokenToken {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(final AuthUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthtokenToken{" + "\n" +
                "    id=" + id + "\n" +
                "    created=" + created + "\n" +
                "    user=" + user + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthtokenToken that = (AuthtokenToken) o;
        return Objects.equals(id, that.id) && Objects.equals(created, that.created) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, user);
    }
}
