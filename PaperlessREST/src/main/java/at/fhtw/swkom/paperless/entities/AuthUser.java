package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;


@Entity
public class AuthUser {

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
    private String password;

    @Column
    private OffsetDateTime lastLogin;

    @Column(nullable = false)
    private Boolean isSuperuser;

    @Column(nullable = false, length = 150)
    private String username;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(nullable = false)
    private Boolean isStaff;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private OffsetDateTime dateJoined;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserGroups> userAuthUserGroupses;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsCorrespondent> ownerDocumentsCorrespondents;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocumenttype> ownerDocumentsDocumenttypes;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsStoragepath> ownerDocumentsStoragepaths;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsTag> ownerDocumentsTags;

    @OneToMany(mappedBy = "user")
    private Set<DocumentsUisettings> userDocumentsUisettingses;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsSavedview> ownerDocumentsSavedviews;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailaccount> ownerPaperlessMailMailaccounts;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocument> ownerDocumentsDocuments;

    @OneToMany(mappedBy = "user")
    private Set<DocumentsNote> userDocumentsNotes;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailrule> ownerPaperlessMailMailrules;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserUserPermissions> userAuthUserUserPermissionses;

    @OneToMany(mappedBy = "user")
    private Set<AuthtokenToken> userAuthtokenTokens;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailProcessedmail> ownerPaperlessMailProcessedmails;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public OffsetDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(final OffsetDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(final Boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(final Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public OffsetDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(final OffsetDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Set<AuthUserGroups> getUserAuthUserGroupses() {
        return userAuthUserGroupses;
    }

    public void setUserAuthUserGroupses(final Set<AuthUserGroups> userAuthUserGroupses) {
        this.userAuthUserGroupses = userAuthUserGroupses;
    }

    public Set<DocumentsCorrespondent> getOwnerDocumentsCorrespondents() {
        return ownerDocumentsCorrespondents;
    }

    public void setOwnerDocumentsCorrespondents(
            final Set<DocumentsCorrespondent> ownerDocumentsCorrespondents) {
        this.ownerDocumentsCorrespondents = ownerDocumentsCorrespondents;
    }

    public Set<DocumentsDocumenttype> getOwnerDocumentsDocumenttypes() {
        return ownerDocumentsDocumenttypes;
    }

    public void setOwnerDocumentsDocumenttypes(
            final Set<DocumentsDocumenttype> ownerDocumentsDocumenttypes) {
        this.ownerDocumentsDocumenttypes = ownerDocumentsDocumenttypes;
    }

    public Set<DocumentsStoragepath> getOwnerDocumentsStoragepaths() {
        return ownerDocumentsStoragepaths;
    }

    public void setOwnerDocumentsStoragepaths(
            final Set<DocumentsStoragepath> ownerDocumentsStoragepaths) {
        this.ownerDocumentsStoragepaths = ownerDocumentsStoragepaths;
    }

    public Set<DocumentsTag> getOwnerDocumentsTags() {
        return ownerDocumentsTags;
    }

    public void setOwnerDocumentsTags(final Set<DocumentsTag> ownerDocumentsTags) {
        this.ownerDocumentsTags = ownerDocumentsTags;
    }

    public Set<DocumentsUisettings> getUserDocumentsUisettingses() {
        return userDocumentsUisettingses;
    }

    public void setUserDocumentsUisettingses(
            final Set<DocumentsUisettings> userDocumentsUisettingses) {
        this.userDocumentsUisettingses = userDocumentsUisettingses;
    }

    public Set<DocumentsSavedview> getOwnerDocumentsSavedviews() {
        return ownerDocumentsSavedviews;
    }

    public void setOwnerDocumentsSavedviews(
            final Set<DocumentsSavedview> ownerDocumentsSavedviews) {
        this.ownerDocumentsSavedviews = ownerDocumentsSavedviews;
    }

    public Set<PaperlessMailMailaccount> getOwnerPaperlessMailMailaccounts() {
        return ownerPaperlessMailMailaccounts;
    }

    public void setOwnerPaperlessMailMailaccounts(
            final Set<PaperlessMailMailaccount> ownerPaperlessMailMailaccounts) {
        this.ownerPaperlessMailMailaccounts = ownerPaperlessMailMailaccounts;
    }

    public Set<DocumentsDocument> getOwnerDocumentsDocuments() {
        return ownerDocumentsDocuments;
    }

    public void setOwnerDocumentsDocuments(final Set<DocumentsDocument> ownerDocumentsDocuments) {
        this.ownerDocumentsDocuments = ownerDocumentsDocuments;
    }

    public Set<DocumentsNote> getUserDocumentsNotes() {
        return userDocumentsNotes;
    }

    public void setUserDocumentsNotes(final Set<DocumentsNote> userDocumentsNotes) {
        this.userDocumentsNotes = userDocumentsNotes;
    }

    public Set<PaperlessMailMailrule> getOwnerPaperlessMailMailrules() {
        return ownerPaperlessMailMailrules;
    }

    public void setOwnerPaperlessMailMailrules(
            final Set<PaperlessMailMailrule> ownerPaperlessMailMailrules) {
        this.ownerPaperlessMailMailrules = ownerPaperlessMailMailrules;
    }

    public Set<AuthUserUserPermissions> getUserAuthUserUserPermissionses() {
        return userAuthUserUserPermissionses;
    }

    public void setUserAuthUserUserPermissionses(
            final Set<AuthUserUserPermissions> userAuthUserUserPermissionses) {
        this.userAuthUserUserPermissionses = userAuthUserUserPermissionses;
    }

    public Set<AuthtokenToken> getUserAuthtokenTokens() {
        return userAuthtokenTokens;
    }

    public void setUserAuthtokenTokens(final Set<AuthtokenToken> userAuthtokenTokens) {
        this.userAuthtokenTokens = userAuthtokenTokens;
    }

    public Set<PaperlessMailProcessedmail> getOwnerPaperlessMailProcessedmails() {
        return ownerPaperlessMailProcessedmails;
    }

    public void setOwnerPaperlessMailProcessedmails(
            final Set<PaperlessMailProcessedmail> ownerPaperlessMailProcessedmails) {
        this.ownerPaperlessMailProcessedmails = ownerPaperlessMailProcessedmails;
    }
    @Override
    public String toString() {
        return "AuthUser{" + "\n" +
                "    id=" + id + "\n" +
                "    password='" + password + '\'' + "\n" +
                "    lastLogin=" + lastLogin + "\n" +
                "    isSuperuser=" + isSuperuser + "\n" +
                "    username='" + username + '\'' + "\n" +
                "    firstName='" + firstName + '\'' + "\n" +
                "    lastName='" + lastName + '\'' + "\n" +
                "    email='" + email + '\'' + "\n" +
                "    isStaff=" + isStaff + "\n" +
                "    isActive=" + isActive + "\n" +
                "    dateJoined=" + dateJoined + "\n" +
                "    userAuthUserGroupses=" + userAuthUserGroupses + "\n" +
                "    ownerDocumentsCorrespondents=" + ownerDocumentsCorrespondents + "\n" +
                "    ownerDocumentsDocumenttypes=" + ownerDocumentsDocumenttypes + "\n" +
                "    ownerDocumentsStoragepaths=" + ownerDocumentsStoragepaths + "\n" +
                "    ownerDocumentsTags=" + ownerDocumentsTags + "\n" +
                "    userDocumentsUisettingses=" + userDocumentsUisettingses + "\n" +
                "    ownerDocumentsSavedviews=" + ownerDocumentsSavedviews + "\n" +
                "    ownerPaperlessMailMailaccounts=" + ownerPaperlessMailMailaccounts + "\n" +
                "    ownerDocumentsDocuments=" + ownerDocumentsDocuments + "\n" +
                "    userDocumentsNotes=" + userDocumentsNotes + "\n" +
                "    ownerPaperlessMailMailrules=" + ownerPaperlessMailMailrules + "\n" +
                "    userAuthUserUserPermissionses=" + userAuthUserUserPermissionses + "\n" +
                "    userAuthtokenTokens=" + userAuthtokenTokens + "\n" +
                "    ownerPaperlessMailProcessedmails=" + ownerPaperlessMailProcessedmails + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(id, authUser.id) && Objects.equals(password, authUser.password) && Objects.equals(lastLogin, authUser.lastLogin) && Objects.equals(isSuperuser, authUser.isSuperuser) && Objects.equals(username, authUser.username) && Objects.equals(firstName, authUser.firstName) && Objects.equals(lastName, authUser.lastName) && Objects.equals(email, authUser.email) && Objects.equals(isStaff, authUser.isStaff) && Objects.equals(isActive, authUser.isActive) && Objects.equals(dateJoined, authUser.dateJoined) && Objects.equals(userAuthUserGroupses, authUser.userAuthUserGroupses) && Objects.equals(ownerDocumentsCorrespondents, authUser.ownerDocumentsCorrespondents) && Objects.equals(ownerDocumentsDocumenttypes, authUser.ownerDocumentsDocumenttypes) && Objects.equals(ownerDocumentsStoragepaths, authUser.ownerDocumentsStoragepaths) && Objects.equals(ownerDocumentsTags, authUser.ownerDocumentsTags) && Objects.equals(userDocumentsUisettingses, authUser.userDocumentsUisettingses) && Objects.equals(ownerDocumentsSavedviews, authUser.ownerDocumentsSavedviews) && Objects.equals(ownerPaperlessMailMailaccounts, authUser.ownerPaperlessMailMailaccounts) && Objects.equals(ownerDocumentsDocuments, authUser.ownerDocumentsDocuments) && Objects.equals(userDocumentsNotes, authUser.userDocumentsNotes) && Objects.equals(ownerPaperlessMailMailrules, authUser.ownerPaperlessMailMailrules) && Objects.equals(userAuthUserUserPermissionses, authUser.userAuthUserUserPermissionses) && Objects.equals(userAuthtokenTokens, authUser.userAuthtokenTokens) && Objects.equals(ownerPaperlessMailProcessedmails, authUser.ownerPaperlessMailProcessedmails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, lastLogin, isSuperuser, username, firstName, lastName, email, isStaff, isActive, dateJoined, userAuthUserGroupses, ownerDocumentsCorrespondents, ownerDocumentsDocumenttypes, ownerDocumentsStoragepaths, ownerDocumentsTags, userDocumentsUisettingses, ownerDocumentsSavedviews, ownerPaperlessMailMailaccounts, ownerDocumentsDocuments, userDocumentsNotes, ownerPaperlessMailMailrules, userAuthUserUserPermissionses, userAuthtokenTokens, ownerPaperlessMailProcessedmails);
    }
}
