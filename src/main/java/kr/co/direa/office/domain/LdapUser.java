package kr.co.direa.office.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Attribute;
import javax.naming.Name;

import static kr.co.direa.office.constant.Constants.LDAP_USER_BASE_DN;

@Getter
@Setter
@NoArgsConstructor
@Entry(objectClasses = {"inetOrgPerson", "posixAccount", "top"}, base = "cn=Users")
public class LdapUser {
    @Id
    private Name dn;

    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "sn")
    private String sn;

    @Attribute(name = "entryUUID")
    private String entryUUID;

    @Attribute(name = "gidNumber")
    private int gidNumber;

    @Attribute(name = "uidNumber")
    private int uidNumber;

    @Attribute(name = "uid")
    private String uid;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "userPassword")
    private String userPassword;

    @Attribute(name = "homeDirectory")
    private String homeDirectory;

    @Attribute(name = "ou")
    private String ou;

    @Builder
    public LdapUser(Name dn, String cn, String sn, int gidNumber, int uidNumber, String mail,
                    String userPassword, String homeDirectory, String ou, String uid){
        this.dn = dn;
        this.cn = cn;
        this.sn = sn;
        this.gidNumber = gidNumber;
        this.uidNumber = uidNumber;
        this.mail = mail;
        this.userPassword = userPassword;
        this.homeDirectory = homeDirectory;
        this.uid = uid;
        this.ou = ou;
    }
}
