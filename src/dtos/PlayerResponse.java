package src.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerResponse {
    private Long id;
    private String name;
    private String position;
    private List<PlayerSkillResponse> playerSkills;
}
