package src.mappers;

import dtos.PlayerResponse;
import dtos.PlayerSkillResponse;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("entityMapper")
public class EntityMapper {

    private final MapperFacade mapper;

    public EntityMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new PlayerPositionConverter());
        mapperFactory.getConverterFactory().registerConverter(new SkillConverter());
        mapper = mapperFactory.getMapperFacade();
    }

    public <S, T> List<T> mapAsList(final List<S> sourceElements, final Class<T> targetClass) {
        List<T> mapped = mapper.mapAsList(sourceElements, targetClass);
        if (!mapped.isEmpty() && mapped.get(0) instanceof PlayerResponse) {
            for (T element : mapped) {
                PlayerResponse dto = (PlayerResponse) element;
                if (dto.getPlayerSkills() != null) {
                    for (PlayerSkillResponse skillDto : dto.getPlayerSkills()) {
                        skillDto.setPlayerId(dto.getId());
                    }
                }
            }
        }

        return mapped;
    }

    public <S, T> T map(final S element, final Class<T> targetClass) {
        T mapped = mapper.map(element, targetClass);
        if (mapped instanceof PlayerResponse) {
            PlayerResponse dto = (PlayerResponse) mapped;
            if (dto.getPlayerSkills() != null) {
                for (PlayerSkillResponse skillDto : dto.getPlayerSkills()) {
                    skillDto.setPlayerId(dto.getId());
                }
            }
        }

        return mapped;
    }
}
