package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class PaperlessMailMailruleAssignTags {

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
    @JoinColumn(name = "mailrule_id", nullable = false)
    private PaperlessMailMailrule mailrule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private DocumentsTag tag;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public PaperlessMailMailrule getMailrule() {
        return mailrule;
    }

    public void setMailrule(final PaperlessMailMailrule mailrule) {
        this.mailrule = mailrule;
    }

    public DocumentsTag getTag() {
        return tag;
    }

    public void setTag(final DocumentsTag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "PaperlessMailMailruleAssignTags{" + "\n" +
                "    id=" + id + "\n" +
                "    mailrule=" + mailrule + "\n" +
                "    tag=" + tag + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperlessMailMailruleAssignTags that = (PaperlessMailMailruleAssignTags) o;
        return Objects.equals(id, that.id) && Objects.equals(mailrule, that.mailrule) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mailrule, tag);
    }
}
