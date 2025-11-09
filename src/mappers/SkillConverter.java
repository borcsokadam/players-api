package src.mappers;

import enums.Skill;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class SkillConverter extends BidirectionalConverter<String, Skill> {
    @Override
    public Skill convertTo(String s, Type<Skill> type, MappingContext mappingContext) {
        if (s == null) return null;
        return Skill.valueOf(s.toUpperCase());
    }

    @Override
    public String convertFrom(Skill skill, Type<String> type, MappingContext mappingContext) {
        if (skill == null) return null;
        return skill.name().toLowerCase();
    }
}
