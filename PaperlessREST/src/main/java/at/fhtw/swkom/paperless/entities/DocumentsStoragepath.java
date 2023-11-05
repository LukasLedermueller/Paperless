package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class DocumentsStoragepath {

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

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 256)
    private String match;

    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Column(nullable = false)
    private Boolean isInsensitive;

    @Column(nullable = false, length = 512)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "storagePath")
    private Set<DocumentsDocument> storagePathDocumentsDocuments;

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

    public String getMatch() {
        return match;
    }

    public void setMatch(final String match) {
        this.match = match;
    }

    public Integer getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(final Integer matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getIsInsensitive() {
        return isInsensitive;
    }

    public void setIsInsensitive(final Boolean isInsensitive) {
        this.isInsensitive = isInsensitive;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<DocumentsDocument> getStoragePathDocumentsDocuments() {
        return storagePathDocumentsDocuments;
    }

    public void setStoragePathDocumentsDocuments(
            final Set<DocumentsDocument> storagePathDocumentsDocuments) {
        this.storagePathDocumentsDocuments = storagePathDocumentsDocuments;
    }

    @Override
    public String toString() {
        return "DocumentsStoragepath{" + "\n" +
                "    id=" + id + "\n" +
                "    name='" + name + '\'' + "\n" +
                "    match='" + match + '\'' + "\n" +
                "    matchingAlgorithm=" + matchingAlgorithm + "\n" +
                "    isInsensitive=" + isInsensitive + "\n" +
                "    path='" + path + '\'' + "\n" +
                "    owner=" + owner + "\n" +
                "    storagePathDocumentsDocuments=" + storagePathDocumentsDocuments + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsStoragepath that = (DocumentsStoragepath) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(match, that.match) && Objects.equals(matchingAlgorithm, that.matchingAlgorithm) && Objects.equals(isInsensitive, that.isInsensitive) && Objects.equals(path, that.path) && Objects.equals(owner, that.owner) && Objects.equals(storagePathDocumentsDocuments, that.storagePathDocumentsDocuments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, match, matchingAlgorithm, isInsensitive, path, owner, storagePathDocumentsDocuments);
    }
}
