package org.pokesplash.keepitems.events;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import kotlin.Unit;
import net.minecraft.item.Item;
import org.pokesplash.keepitems.BattleData;
import org.pokesplash.keepitems.KeepItems;
import org.pokesplash.keepitems.PlayerItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BattleStartEvent {
    public void registerEvent() {
        CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.NORMAL, e -> {

            // Gets all actors.
            Iterable<BattleActor> actors = e.getBattle().getActors();

            // Hashmap to store all players and their items for the battle.
            HashMap<UUID, PlayerItems> playerItems = new HashMap<>();

            // For each actor.
            actors.forEach(actor -> {

                // Get all ids.
                ArrayList<UUID> ids = new ArrayList<>();

                //Gets all ids related to the actor.
                actor.getPlayerUUIDs().forEach(ids::add);

                // Creates a hashmap of pokemon UUIDs and items they were holding.
                HashMap<UUID, Item> pokemons = new HashMap<>();

                // Adds all Pokemon and their original items to the map.
                actor.getPokemonList().forEach(pokemon -> {
                    pokemons.put(pokemon.getOriginalPokemon().getUuid(),
                            pokemon.getOriginalPokemon().heldItem().getItem());
                });

                // For each ID, add them and their items to the hashmap.
                for (UUID id : ids) {
                    playerItems.put(id, new PlayerItems(pokemons));
                }
            });

            // Store them in the hashmap.
            KeepItems.items.put(e.getBattle().getBattleId(),
                    new BattleData(e.getBattle().getBattleId(), playerItems));

            return Unit.INSTANCE;
        });
    }
}
