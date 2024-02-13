package org.pokesplash.keepitems;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.UUID;

public class PlayerItems {
    private HashMap<UUID, Item> pokemonItems;

    public PlayerItems(HashMap<UUID, Item> items) {
        pokemonItems = items;
    }

    public HashMap<UUID, Item> getPokemonItems() {
        return pokemonItems;
    }
}
