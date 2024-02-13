package org.pokesplash.keepitems.events;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kotlin.Unit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.pokesplash.keepitems.BattleData;
import org.pokesplash.keepitems.KeepItems;
import org.pokesplash.keepitems.PlayerItems;

import java.util.HashMap;
import java.util.UUID;

public class BattleEndedEvent {
    public void registerEvent() {
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, e -> {

            // Gets the data for the battle.
            BattleData data = KeepItems.items.get(e.getBattle().getBattleId());

            if (data == null) {
                return Unit.INSTANCE;
            }

            HashMap<UUID, PlayerItems> playerData = data.getPlayerItems();

            for (UUID id : playerData.keySet()) {

                ServerPlayerEntity player = KeepItems.server.getPlayerManager().getPlayer(id);

                // If the player can't be found, carry on.
                if (player == null) {
                    continue;
                }

                // Gets the IDs and Items for each of the Players Pokemon.
                HashMap<UUID, Item> items = playerData.get(id).getPokemonItems();

                // Gets the players party.
                PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(player);

                for (UUID pokemonId : items.keySet()) {
                    Pokemon pokemon = party.get(pokemonId);

                    // If the Pokemon can't be found, continue.
                    if (pokemon == null) {
                        continue;
                    }

                    // Give it the correct held item.
                    pokemon.swapHeldItem(new ItemStack(items.get(pokemonId)), true);
                }
            }

            // Remove from the list.
            KeepItems.items.remove(e.getBattle().getBattleId());

            return Unit.INSTANCE;
        });
    }
}
