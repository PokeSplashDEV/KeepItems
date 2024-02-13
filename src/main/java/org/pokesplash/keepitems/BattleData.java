package org.pokesplash.keepitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BattleData {
    private UUID battleId;
    private HashMap<UUID, PlayerItems> playerItems;

    public BattleData(UUID battleId, HashMap<UUID, PlayerItems> playerItems) {
        this.battleId = battleId;
        this.playerItems = playerItems;
    }

    public UUID getBattleId() {
        return battleId;
    }

    public HashMap<UUID, PlayerItems> getPlayerItems() {
        return playerItems;
    }
}
