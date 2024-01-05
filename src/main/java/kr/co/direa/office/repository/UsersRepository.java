package kr.co.direa.office.repository;

import kr.co.direa.office.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);
}