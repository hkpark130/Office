package kr.co.direa.office.service;

import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Users save(UserDto userDto){
        return usersRepository.save(userDto.toEntity());
    }

    public void saveAll(List<Users> usersList) {
        usersRepository.saveAll(usersList);
    }
}
