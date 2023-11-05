package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class DocumentsCorrespondent {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "correspondent")
    private Set<DocumentsDocument> correspondentDocumentsDocuments;

    @OneToMany(mappedBy = "assignCorrespondent")
    private Set<PaperlessMailMailrule> assignCorrespondentPaperlessMailMailrules;

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

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<DocumentsDocument> getCorrespondentDocumentsDocuments() {
        return correspondentDocumentsDocuments;
    }

    public void setCorrespondentDocumentsDocuments(
            final Set<DocumentsDocument> correspondentDocumentsDocuments) {
        this.correspondentDocumentsDocuments = correspondentDocumentsDocuments;
    }

    public Set<PaperlessMailMailrule> getAssignCorrespondentPaperlessMailMailrules() {
        return assignCorrespondentPaperlessMailMailrules;
    }

    public void setAssignCorrespondentPaperlessMailMailrules(
            final Set<PaperlessMailMailrule> assignCorrespondentPaperlessMailMailrules) {
        this.assignCorrespondentPaperlessMailMailrules = assignCorrespondentPaperlessMailMailrules;
    }

    @Override
    public String toString() {
        return "DocumentsCorrespondent{" + "\n" +
                "    id=" + id + "\n" +
                "    name='" + name + '\'' + "\n" +
                "    match='" + match + '\'' + "\n" +
                "    matchingAlgorithm=" + matchingAlgorithm + "\n" +
                "    isInsensitive=" + isInsensitive + "\n" +
                "    owner=" + owner + "\n" +
                "    correspondentDocumentsDocuments=" + correspondentDocumentsDocuments + "\n" +
                "    assignCorrespondentPaperlessMailMailrules=" + assignCorrespondentPaperlessMailMailrules + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsCorrespondent that = (DocumentsCorrespondent) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(match, that.match) && Objects.equals(matchingAlgorithm, that.matchingAlgorithm) && Objects.equals(isInsensitive, that.isInsensitive) && Objects.equals(owner, that.owner) && Objects.equals(correspondentDocumentsDocuments, that.correspondentDocumentsDocuments) && Objects.equals(assignCorrespondentPaperlessMailMailrules, that.assignCorrespondentPaperlessMailMailrules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, match, matchingAlgorithm, isInsensitive, owner, correspondentDocumentsDocuments, assignCorrespondentPaperlessMailMailrules);
    }
}
