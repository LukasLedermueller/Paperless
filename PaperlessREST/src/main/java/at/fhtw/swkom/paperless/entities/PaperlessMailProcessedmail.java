package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class PaperlessMailProcessedmail {

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

    @Column(nullable = false, length = 256)
    private String folder;

    @Column(nullable = false, length = 256)
    private String uid;

    @Column(nullable = false, length = 256)
    private String subject;

    @Column(nullable = false)
    private OffsetDateTime received;

    @Column(nullable = false)
    private OffsetDateTime processed;

    @Column(nullable = false, length = 256)
    private String status;

    @Column(columnDefinition = "text")
    private String error;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    private PaperlessMailMailrule rule;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(final String folder) {
        this.folder = folder;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public OffsetDateTime getReceived() {
        return received;
    }

    public void setReceived(final OffsetDateTime received) {
        this.received = received;
    }

    public OffsetDateTime getProcessed() {
        return processed;
    }

    public void setProcessed(final OffsetDateTime processed) {
        this.processed = processed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public PaperlessMailMailrule getRule() {
        return rule;
    }

    public void setRule(final PaperlessMailMailrule rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "PaperlessMailProcessedmail{" + "\n" +
                "    id=" + id + "\n" +
                "    folder='" + folder + '\'' + "\n" +
                "    uid='" + uid + '\'' + "\n" +
                "    subject='" + subject + '\'' + "\n" +
                "    received=" + received + "\n" +
                "    processed=" + processed + "\n" +
                "    status='" + status + '\'' + "\n" +
                "    error='" + error + '\'' + "\n" +
                "    owner=" + owner + "\n" +
                "    rule=" + rule + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperlessMailProcessedmail that = (PaperlessMailProcessedmail) o;
        return Objects.equals(id, that.id) && Objects.equals(folder, that.folder) && Objects.equals(uid, that.uid) && Objects.equals(subject, that.subject) && Objects.equals(received, that.received) && Objects.equals(processed, that.processed) && Objects.equals(status, that.status) && Objects.equals(error, that.error) && Objects.equals(owner, that.owner) && Objects.equals(rule, that.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, folder, uid, subject, received, processed, status, error, owner, rule);
    }
}
