package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;


@Entity
public class DocumentsDocument {

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

    @Column(length = 128)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @Column()
    private OffsetDateTime created;

    @Column()
    private OffsetDateTime modified;

    @Column(length = 32)
    private String checksum;

    @Column()
    private OffsetDateTime added;

    @Column(length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(length = 256)
    private String mimeType;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_id")
    private DocumentsCorrespondent correspondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentsDocumenttype documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_path_id")
    private DocumentsStoragepath storagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsNote> documentDocumentsNotes;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsDocumentTags> documentDocumentsDocumentTagses;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public void setModified(final OffsetDateTime modified) {
        this.modified = modified;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    public OffsetDateTime getAdded() {
        return added;
    }

    public void setAdded(final OffsetDateTime added) {
        this.added = added;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(final String storageType) {
        this.storageType = storageType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public Integer getArchiveSerialNumber() {
        return archiveSerialNumber;
    }

    public void setArchiveSerialNumber(final Integer archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getArchiveChecksum() {
        return archiveChecksum;
    }

    public void setArchiveChecksum(final String archiveChecksum) {
        this.archiveChecksum = archiveChecksum;
    }

    public String getArchiveFilename() {
        return archiveFilename;
    }

    public void setArchiveFilename(final String archiveFilename) {
        this.archiveFilename = archiveFilename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(final String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public DocumentsCorrespondent getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(final DocumentsCorrespondent correspondent) {
        this.correspondent = correspondent;
    }

    public DocumentsDocumenttype getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final DocumentsDocumenttype documentType) {
        this.documentType = documentType;
    }

    public DocumentsStoragepath getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(final DocumentsStoragepath storagePath) {
        this.storagePath = storagePath;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<DocumentsNote> getDocumentDocumentsNotes() {
        return documentDocumentsNotes;
    }

    public void setDocumentDocumentsNotes(final Set<DocumentsNote> documentDocumentsNotes) {
        this.documentDocumentsNotes = documentDocumentsNotes;
    }

    public Set<DocumentsDocumentTags> getDocumentDocumentsDocumentTagses() {
        return documentDocumentsDocumentTagses;
    }

    public void setDocumentDocumentsDocumentTagses(
            final Set<DocumentsDocumentTags> documentDocumentsDocumentTagses) {
        this.documentDocumentsDocumentTagses = documentDocumentsDocumentTagses;
    }
    @Override
    public String toString() {
        return "DocumentsDocument{" + "\n" +
                "    id=" + id + "\n" +
                "    title='" + title + '\'' + "\n" +
                "    content='" + content + '\'' + "\n" +
                "    created=" + created + "\n" +
                "    modified=" + modified + "\n" +
                "    checksum='" + checksum + '\'' + "\n" +
                "    added=" + added + "\n" +
                "    storageType='" + storageType + '\'' + "\n" +
                "    filename='" + filename + '\'' + "\n" +
                "    archiveSerialNumber=" + archiveSerialNumber + "\n" +
                "    mimeType='" + mimeType + '\'' + "\n" +
                "    archiveChecksum='" + archiveChecksum + '\'' + "\n" +
                "    archiveFilename='" + archiveFilename + '\'' + "\n" +
                "    originalFilename='" + originalFilename + '\'' + "\n" +
                "    correspondent=" + correspondent + "\n" +
                "    documentType=" + documentType + "\n" +
                "    storagePath=" + storagePath + "\n" +
                "    owner=" + owner + "\n" +
                "    documentDocumentsNotes=" + documentDocumentsNotes + "\n" +
                "    documentDocumentsDocumentTagses=" + documentDocumentsDocumentTagses + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsDocument that = (DocumentsDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(created, that.created) && Objects.equals(modified, that.modified) && Objects.equals(checksum, that.checksum) && Objects.equals(added, that.added) && Objects.equals(storageType, that.storageType) && Objects.equals(filename, that.filename) && Objects.equals(archiveSerialNumber, that.archiveSerialNumber) && Objects.equals(mimeType, that.mimeType) && Objects.equals(archiveChecksum, that.archiveChecksum) && Objects.equals(archiveFilename, that.archiveFilename) && Objects.equals(originalFilename, that.originalFilename) && Objects.equals(correspondent, that.correspondent) && Objects.equals(documentType, that.documentType) && Objects.equals(storagePath, that.storagePath) && Objects.equals(owner, that.owner) && Objects.equals(documentDocumentsNotes, that.documentDocumentsNotes) && Objects.equals(documentDocumentsDocumentTagses, that.documentDocumentsDocumentTagses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created, modified, checksum, added, storageType, filename, archiveSerialNumber, mimeType, archiveChecksum, archiveFilename, originalFilename, correspondent, documentType, storagePath, owner, documentDocumentsNotes, documentDocumentsDocumentTagses);
    }
}
