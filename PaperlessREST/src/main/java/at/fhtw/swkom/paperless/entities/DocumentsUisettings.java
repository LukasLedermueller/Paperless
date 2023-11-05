package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class DocumentsUisettings {

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
    private Integer id;

    @Column(columnDefinition = "text")
    private String settings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(final String settings) {
        this.settings = settings;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(final AuthUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DocumentsUisettings{" + "\n" +
                "    id=" + id + "\n" +
                "    settings='" + settings + '\'' + "\n" +
                "    user=" + user + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsUisettings that = (DocumentsUisettings) o;
        return Objects.equals(id, that.id) && Objects.equals(settings, that.settings) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, settings, user);
    }
}
