package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class DocumentsDocumentTags {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentsDocument document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private DocumentsTag tag;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public DocumentsDocument getDocument() {
        return document;
    }

    public void setDocument(final DocumentsDocument document) {
        this.document = document;
    }

    public DocumentsTag getTag() {
        return tag;
    }

    public void setTag(final DocumentsTag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "DocumentsDocumentTags{" + "\n" +
                "    id=" + id + "\n" +
                "    document=" + document + "\n" +
                "    tag=" + tag + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsDocumentTags that = (DocumentsDocumentTags) o;
        return Objects.equals(id, that.id) && Objects.equals(document, that.document) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, document, tag);
    }
}
