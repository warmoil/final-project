package joy.world.fc.finalproject.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data

public class SignUpRequest {
    @NotBlank
    private final String name;
    @Email
    private final String email;
    @Size(min = 4 , message = "비밀 번호는 4자리 이상이어야 합니다 ")
    private final String password;
    @NotNull
    private final LocalDate birthday;
}
