package kr.co.direa.office.service;

import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Users save(UserDto userDto){
        return usersRepository.save(userDto.toEntity());
    }

    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Users findByUsernameOrInsert(UserDto userDto) {
        Users user = usersRepository.findByUsername(userDto.getUsername()).orElse(null);
        if (user == null) {
            user = usersRepository.save(userDto.toEntity());
        } else {
            user.setAuth(userDto.getAuth());
            user = usersRepository.save(user);
        }
        return user;
    }
}
