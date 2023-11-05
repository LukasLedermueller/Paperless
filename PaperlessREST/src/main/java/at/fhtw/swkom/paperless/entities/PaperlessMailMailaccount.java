package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class PaperlessMailMailaccount {

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
    private String name;

    @Column(nullable = false, length = 256)
    private String imapServer;

    @Column
    private Integer imapPort;

    @Column(nullable = false)
    private Integer imapSecurity;

    @Column(nullable = false, length = 256)
    private String username;

    @Column(nullable = false, length = 2048)
    private String password;

    @Column(nullable = false, length = 256)
    private String characterSet;

    @Column(nullable = false)
    private Boolean isToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "account")
    private Set<PaperlessMailMailrule> accountPaperlessMailMailrules;

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

    public String getImapServer() {
        return imapServer;
    }

    public void setImapServer(final String imapServer) {
        this.imapServer = imapServer;
    }

    public Integer getImapPort() {
        return imapPort;
    }

    public void setImapPort(final Integer imapPort) {
        this.imapPort = imapPort;
    }

    public Integer getImapSecurity() {
        return imapSecurity;
    }

    public void setImapSecurity(final Integer imapSecurity) {
        this.imapSecurity = imapSecurity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(final String characterSet) {
        this.characterSet = characterSet;
    }

    public Boolean getIsToken() {
        return isToken;
    }

    public void setIsToken(final Boolean isToken) {
        this.isToken = isToken;
    }

    public AuthUser getOwner() {
        return owner;
    }

    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }

    public Set<PaperlessMailMailrule> getAccountPaperlessMailMailrules() {
        return accountPaperlessMailMailrules;
    }

    public void setAccountPaperlessMailMailrules(
            final Set<PaperlessMailMailrule> accountPaperlessMailMailrules) {
        this.accountPaperlessMailMailrules = accountPaperlessMailMailrules;
    }

    @Override
    public String toString() {
        return "PaperlessMailMailaccount{" + "\n" +
                "    id=" + id + "\n" +
                "    name='" + name + '\'' + "\n" +
                "    imapServer='" + imapServer + '\'' + "\n" +
                "    imapPort=" + imapPort + "\n" +
                "    imapSecurity=" + imapSecurity + "\n" +
                "    username='" + username + '\'' + "\n" +
                "    password='" + password + '\'' + "\n" +
                "    characterSet='" + characterSet + '\'' + "\n" +
                "    isToken=" + isToken + "\n" +
                "    owner=" + owner + "\n" +
                "    accountPaperlessMailMailrules=" + accountPaperlessMailMailrules + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperlessMailMailaccount that = (PaperlessMailMailaccount) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(imapServer, that.imapServer) && Objects.equals(imapPort, that.imapPort) && Objects.equals(imapSecurity, that.imapSecurity) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(characterSet, that.characterSet) && Objects.equals(isToken, that.isToken) && Objects.equals(owner, that.owner) && Objects.equals(accountPaperlessMailMailrules, that.accountPaperlessMailMailrules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imapServer, imapPort, imapSecurity, username, password, characterSet, isToken, owner, accountPaperlessMailMailrules);
    }
}
