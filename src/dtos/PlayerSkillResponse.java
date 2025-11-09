package src.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerSkillResponse {
    private Long id;
    private String skill;
    private String value;
    private Long playerId;
}
