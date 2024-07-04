package kr.co.direa.office.repository;

import kr.co.direa.office.domain.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {
    LdapUser findByCn(String cn);

    List<LdapUser> findAll();
    LdapUser findByUidNumber(String uidNumber);

}
