package src.dtos;

import enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;
import utils.ValueOfEnum;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PlayerSkillRequest {
    @NotNull
    @ValueOfEnum(enumClass = Skill.class)
    private String skill;
    @Nullable
    private Integer value;
}
