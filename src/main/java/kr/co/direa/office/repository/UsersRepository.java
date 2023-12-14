package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

}