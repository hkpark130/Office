package kr.co.direa.office.dto;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String auth;
    private Departments departmentId;

    @Builder
    public UserDto(Users entity){
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.auth = entity.getAuth();
        this.departmentId = entity.getDepartmentId();
    }

    public UserDto(String username, String auth, Departments departmentId) {
        this.username = username;
        this.auth = auth;
        this.departmentId = departmentId;
    }

    public Users toEntity() {
        return Users.builder()
                .id(id)
                .username(username)
                .auth(auth)
                .departmentId(departmentId)
                .build();
    }

}
