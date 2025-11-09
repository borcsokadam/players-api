package src.mappers;

import enums.PlayerPosition;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class PlayerPositionConverter extends BidirectionalConverter<String, PlayerPosition> {
    @Override
    public PlayerPosition convertTo(String s, Type<PlayerPosition> type, MappingContext mappingContext) {
        if (s == null) return null;
        return PlayerPosition.valueOf(s.toUpperCase());
    }

    @Override
    public String convertFrom(PlayerPosition playerPosition, Type<String> type, MappingContext mappingContext) {
        if (playerPosition == null) return null;
        return playerPosition.name().toLowerCase();
    }
}
