package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


@Entity
public class DocumentsLog {

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

    @Column(name = "\"group\"")
    private UUID group;

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private OffsetDateTime created;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public UUID getGroup() {
        return group;
    }

    public void setGroup(final UUID group) {
        this.group = group;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(final Integer level) {
        this.level = level;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "DocumentsLog{" + "\n" +
                "    id=" + id + "\n" +
                "    group=" + group + "\n" +
                "    message='" + message + '\'' + "\n" +
                "    level=" + level + "\n" +
                "    created=" + created + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsLog that = (DocumentsLog) o;
        return Objects.equals(id, that.id) && Objects.equals(group, that.group) && Objects.equals(message, that.message) && Objects.equals(level, that.level) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, message, level, created);
    }
}
