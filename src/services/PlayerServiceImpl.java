package src.services;

import dtos.PlayerRequest;
import dtos.PlayerResponse;
import dtos.PlayerSkillRequest;
import enums.PlayerPosition;
import exceptions.EntityNotFoundException;
import mappers.EntityMapper;
import models.Player;
import models.PlayerSkill;
import org.springframework.stereotype.Service;
import repositories.PlayerRepository;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final EntityMapper mapper;

    public PlayerServiceImpl(PlayerRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PlayerResponse> getPlayers() {
        return mapper.mapAsList(repository.findAll(), PlayerResponse.class);
    }

    @Override
    public PlayerResponse createPlayer(PlayerRequest request) {
        Player entity = mapper.map(request, Player.class);
        entity.getPlayerSkills().forEach(playerSkill -> playerSkill.setPlayer(entity));
        Player created = repository.save(entity);
        return mapper.map(created, PlayerResponse.class);
    }

    @Override
    public PlayerResponse getPlayerById(Long id) {
        return repository.findById(id)
                .map(player -> mapper.map(player, PlayerResponse.class))
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public PlayerResponse updateById(Long id, PlayerRequest request) {
        Player entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        entity.setName(request.getName());
        entity.setPosition(PlayerPosition.valueOf(request.getPosition()));

        entity.getPlayerSkills().clear();
        for (PlayerSkillRequest playerSkillRequest : request.getPlayerSkills()) {
            PlayerSkill playerSkill = new PlayerSkill();
            playerSkill.setSkill(Skill.valueOf(playerSkillRequest.getSkill()));
            playerSkill.setValue(playerSkillRequest.getValue());
            playerSkill.setPlayer(entity);
            entity.getPlayerSkills().add(playerSkill);
        }

        Player updated = repository.save(entity);
        return mapper.map(updated, PlayerResponse.class);
    }

    @Override
    public void deletePlayerById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
