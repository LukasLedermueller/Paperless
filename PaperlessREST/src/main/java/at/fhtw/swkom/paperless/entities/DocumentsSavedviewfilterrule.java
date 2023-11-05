package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class DocumentsSavedviewfilterrule {

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

    @Column(nullable = false)
    private Integer ruleType;

    @Column
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saved_view_id", nullable = false)
    private DocumentsSavedview savedView;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(final Integer ruleType) {
        this.ruleType = ruleType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public DocumentsSavedview getSavedView() {
        return savedView;
    }

    public void setSavedView(final DocumentsSavedview savedView) {
        this.savedView = savedView;
    }

    @Override
    public String toString() {
        return "DocumentsSavedviewfilterrule{" + "\n" +
                "    id=" + id + "\n" +
                "    ruleType=" + ruleType + "\n" +
                "    value='" + value + '\'' + "\n" +
                "    savedView=" + savedView + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsSavedviewfilterrule that = (DocumentsSavedviewfilterrule) o;
        return Objects.equals(id, that.id) && Objects.equals(ruleType, that.ruleType) && Objects.equals(value, that.value) && Objects.equals(savedView, that.savedView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruleType, value, savedView);
    }
}
