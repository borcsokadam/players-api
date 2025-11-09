package src.dtos;

import enums.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import utils.ValueOfEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class PlayerRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @ValueOfEnum(enumClass = PlayerPosition.class)
    private String position;
    @NotEmpty
    private List<@Valid PlayerSkillRequest> playerSkills;
}
