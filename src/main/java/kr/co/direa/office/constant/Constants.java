package kr.co.direa.office.constant;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {
    public static final String APPROVAL_COMPLETED = "승인완료";
    public static final String APPROVAL_WAITING = "승인대기";
    public static final String APPROVAL_REJECT = "반려";
    public static final String NORMAL_TYPE = "정상";
    public static final String OLD_TYPE = "노후";
    public static final String DISPOSE_TYPE = "폐기";
    public static final String APPROVAL_RETURN = "반납";
    public static final String APPROVAL_RENTAL = "대여";
    public static final String COMMENT_TYPE = "댓글";
    public static final String EDIT_TYPE = "수정";
    public static final String PURCHASE_TYPE = "구매";
    public static final String SUCCESS = "success";
    public static final String LDAP_BASE_DN = "dc=direa,dc=co,dc=kr";
    public static final String LDAP_USER_BASE_DN = "cn=Users," + LDAP_BASE_DN;
    public static final String LDAP_GROUP_BASE_DN = "ou=Groups," + LDAP_BASE_DN;
    public static final String LDAP_CN_ATTR = "cn";
    public static final String LDAP_SN_ATTR = "sn";
    public static final String LDAP_UID_ATTR = "uid";
    public static final String LDAP_GID_NUMBER_ATTR = "gidNumber";
    public static final String LDAP_UID_NUMBER_ATTR = "uidNumber";
    public static final String LDAP_EMAIL_ATTR = "mail";
    public static final String LDAP_PASSWORD_ATTR = "userPassword";
    public static final String LDAP_HOMEDIR_ATTR = "homeDirectory";
    public static final String LDAP_OU_ATTR = "ou";
    public static final String DIREA_DOMAIN = "direa.co.kr";
    public static final String ADMIN_GROUP_NAME = "Admin";
    public static final String FIXED_APPROVER = "조고은";

}
