package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class PaperlessMailMailrule {

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

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String folder;

    @Column(length = 256)
    private String filterFrom;

    @Column(length = 256)
    private String filterSubject;

    @Column(length = 256)
    private String filterBody;

    @Column(nullable = false)
    private Integer maximumAge;

    @Column(nullable = false)
    private Integer action;

    @Column(length = 256)
    private String actionParameter;

    @Column(nullable = false)
    private Integer assignTitleFrom;

    @Column(nullable = false)
    private Integer assignCorrespondentFrom;

    @Column(nullable = false, name = "\"order\"")
    private Integer order;

    @Column(nullable = false)
    private Integer attachmentType;

    @Column(length = 256)
    private String filterAttachmentFilename;

    @Column(nullable = false)
    private Integer consumptionScope;

    @Column(length = 256)
    private String filterTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private PaperlessMailMailaccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assign_correspondent_id")
    private DocumentsCorrespondent assignCorrespondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assign_document_type_id")
    private DocumentsDocumenttype assignDocumentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "rule")
    private Set<PaperlessMailProcessedmail> rulePaperlessMailProcessedmails;

    @OneToMany(mappedBy = "mailrule")
    private Set<PaperlessMailMailruleAssignTags> mailrulePaperlessMailMailruleAssignTagses;

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

    public String getFolder() {
        return folder;
    }

    public void setFolder(final String folder) {
        this.folder = folder;
    }

    public String getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(final String filterFrom) {
        this.filterFrom = filterFrom;
    }

    public String getFilterSubject() {
        return filterSubject;
    }

    public void setFilterSubject(final String filterSubject) {
        this.filterSubject = filterSubject;
    }

    public String getFilterBody() {
        return filterBody;
    }

    public void setFilterBody(final String filterBody) {
        this.filterBody = filterBody;
    }

    public Integer getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(final Integer maximumAge) {
        this.maximumAge = maximumAge;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(final Integer action) {
        this.action = action;
    }

    public String getActionParameter() {
        return actionParameter;
    }

    public void setActionParameter(final String actionParameter) {
        this.actionParameter = actionParameter;
    }

    public Integer getAssignTitleFrom() {
        return assignTitleFrom;
    }

    public void setAssignTitleFrom(final Integer assignTitleFrom) {
        this.assignTitleFrom = assignTitleFrom;
    }

    public Integer getAssignCorrespondentFrom() {
        return assignCorrespondentFrom;
    }

    public void setAssignCorrespondentFrom(final Integer assignCorrespondentFrom) {
        this.assignCorrespondentFrom = assignCorrespondentFrom;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(final Integer order) {
        this.order = order;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(final Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFilterAttachmentFilename() {
        return filterAttachmentFilename;
    }

    public void setFilterAttachmentFilename(final String filterAttachmentFilename) {
        this.filterAttachmentFilename = filterAttachmentFilename;
    }

    public Integer getConsumptionScope() {
        return consumptionScope;
    }

    public void setConsumptionScope(final Integer consumptionScope) {
        this.consumptionScope = consumptionScope;
    }

    public String getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(final String filterTo) {
        this.filterTo = filterTo;
    }

    public PaperlessMailMailaccount getAccount() {
        return account;
    }

    public void setAccount(final PaperlessMailMailaccount account) {
        this.account = account;
    }

    public DocumentsCorrespondent getAssignCorrespondent() {
        return assignCorrespondent;
    }

    public void setAssignCorrespondent(final DocumentsCorrespondent assignCorrespondent) {
        this.assignCorrespondent = assignCorrespondent;
    }

    public DocumentsDocumenttype getAssignDocumentType() {
        return assignDocumentType;
    }

    public void setAssignDocumentType(final DocumentsDocumenttype assignDocumentType) {
        this.assignDocumentType = assignDocumentType;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<PaperlessMailProcessedmail> getRulePaperlessMailProcessedmails() {
        return rulePaperlessMailProcessedmails;
    }

    public void setRulePaperlessMailProcessedmails(
            final Set<PaperlessMailProcessedmail> rulePaperlessMailProcessedmails) {
        this.rulePaperlessMailProcessedmails = rulePaperlessMailProcessedmails;
    }

    public Set<PaperlessMailMailruleAssignTags> getMailrulePaperlessMailMailruleAssignTagses() {
        return mailrulePaperlessMailMailruleAssignTagses;
    }

    public void setMailrulePaperlessMailMailruleAssignTagses(
            final Set<PaperlessMailMailruleAssignTags> mailrulePaperlessMailMailruleAssignTagses) {
        this.mailrulePaperlessMailMailruleAssignTagses = mailrulePaperlessMailMailruleAssignTagses;
    }

    @Override
    public String toString() {
        return "PaperlessMailMailrule{" + "\n" +
                "    id=" + id + "\n" +
                "    name='" + name + '\'' + "\n" +
                "    folder='" + folder + '\'' + "\n" +
                "    filterFrom='" + filterFrom + '\'' + "\n" +
                "    filterSubject='" + filterSubject + '\'' + "\n" +
                "    filterBody='" + filterBody + '\'' + "\n" +
                "    maximumAge=" + maximumAge + "\n" +
                "    action=" + action + "\n" +
                "    actionParameter='" + actionParameter + '\'' + "\n" +
                "    assignTitleFrom=" + assignTitleFrom + "\n" +
                "    assignCorrespondentFrom=" + assignCorrespondentFrom + "\n" +
                "    order=" + order + "\n" +
                "    attachmentType=" + attachmentType + "\n" +
                "    filterAttachmentFilename='" + filterAttachmentFilename + '\'' + "\n" +
                "    consumptionScope=" + consumptionScope + "\n" +
                "    filterTo='" + filterTo + '\'' + "\n" +
                "    account=" + account + "\n" +
                "    assignCorrespondent=" + assignCorrespondent + "\n" +
                "    assignDocumentType=" + assignDocumentType + "\n" +
                "    owner=" + owner + "\n" +
                "    rulePaperlessMailProcessedmails=" + rulePaperlessMailProcessedmails + "\n" +
                "    mailrulePaperlessMailMailruleAssignTagses=" + mailrulePaperlessMailMailruleAssignTagses + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperlessMailMailrule that = (PaperlessMailMailrule) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(folder, that.folder) && Objects.equals(filterFrom, that.filterFrom) && Objects.equals(filterSubject, that.filterSubject) && Objects.equals(filterBody, that.filterBody) && Objects.equals(maximumAge, that.maximumAge) && Objects.equals(action, that.action) && Objects.equals(actionParameter, that.actionParameter) && Objects.equals(assignTitleFrom, that.assignTitleFrom) && Objects.equals(assignCorrespondentFrom, that.assignCorrespondentFrom) && Objects.equals(order, that.order) && Objects.equals(attachmentType, that.attachmentType) && Objects.equals(filterAttachmentFilename, that.filterAttachmentFilename) && Objects.equals(consumptionScope, that.consumptionScope) && Objects.equals(filterTo, that.filterTo) && Objects.equals(account, that.account) && Objects.equals(assignCorrespondent, that.assignCorrespondent) && Objects.equals(assignDocumentType, that.assignDocumentType) && Objects.equals(owner, that.owner) && Objects.equals(rulePaperlessMailProcessedmails, that.rulePaperlessMailProcessedmails) && Objects.equals(mailrulePaperlessMailMailruleAssignTagses, that.mailrulePaperlessMailMailruleAssignTagses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, folder, filterFrom, filterSubject, filterBody, maximumAge, action, actionParameter, assignTitleFrom, assignCorrespondentFrom, order, attachmentType, filterAttachmentFilename, consumptionScope, filterTo, account, assignCorrespondent, assignDocumentType, owner, rulePaperlessMailProcessedmails, mailrulePaperlessMailMailruleAssignTagses);
    }
}
