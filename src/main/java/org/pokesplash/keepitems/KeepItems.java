package org.pokesplash.keepitems;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeepItems implements ModInitializer {
	public static final String MOD_ID = "KeepItems";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		load();
	}

	public static void load() {
	}
}
