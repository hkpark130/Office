package kr.co.direa.office.dto;

import kr.co.direa.office.domain.LdapUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ldap.support.LdapUtils;

import java.io.Serializable;

import static kr.co.direa.office.constant.Constants.LDAP_USER_BASE_DN;

@Getter
@Setter
@NoArgsConstructor
public class LdapUserDto implements Serializable {
//    private String dn;
    private String cn;
    private String sn;
    private String uid;
    private int gidNumber;
    private int uidNumber;
    private String mail;
    private String userPassword;
    private String homeDirectory;
    private String ou;

    @Builder
    public LdapUserDto(LdapUser entity){
        this.cn = entity.getCn();
        this.sn = entity.getSn();
        this.uid = entity.getUid();
        this.gidNumber = entity.getGidNumber();
        this.uidNumber = entity.getUidNumber();
        this.mail = entity.getMail();
        this.userPassword = entity.getUserPassword();
        this.homeDirectory = entity.getHomeDirectory();
        this.ou = entity.getOu();
    }

    public LdapUser toEntity() {
        return LdapUser.builder()
                .dn(LdapUtils.newLdapName("cn=" + cn + "," + LDAP_USER_BASE_DN))
                .cn(cn)
                .sn(sn)
                .gidNumber(gidNumber)
                .uidNumber(uidNumber)
                .mail(mail)
                .userPassword(userPassword)
                .homeDirectory(homeDirectory)
                .ou(ou)
                .build();
    }
}
