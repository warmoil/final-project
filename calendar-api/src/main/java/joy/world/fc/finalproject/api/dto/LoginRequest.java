package joy.world.fc.finalproject.api.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @Size(min = 4 , message = "4자리 이상 입력해주세요")
    private final String email;
    private final String password;

}
