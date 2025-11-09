package src.services;

import dtos.PlayerRequest;
import dtos.PlayerResponse;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getPlayers();

    PlayerResponse createPlayer(PlayerRequest request);

    PlayerResponse getPlayerById(Long id);

    PlayerResponse updateById(Long id, PlayerRequest request);

    void deletePlayerById(Long id);
}
