package org.pokesplash.keepitems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.keepitems.events.BattleEndedEvent;
import org.pokesplash.keepitems.events.BattleStartEvent;

import java.util.HashMap;
import java.util.UUID;

public class KeepItems implements ModInitializer {
	public static final String MOD_ID = "KeepItems";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static HashMap<UUID, BattleData> items = new HashMap<>();

	public static MinecraftServer server;

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		ServerWorldEvents.LOAD.register((t, e) -> server = t);
		new BattleStartEvent().registerEvent();
		new BattleEndedEvent().registerEvent();
	}
}
