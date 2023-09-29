package com.example;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;

import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;

import net.minecraft.entity.ai.brain.Activity;
    
public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// An instance of our new item
	public static final CustomItem CUSTOM_ITEM =
		Registry.register(Registries.ITEM, new Identifier("tutorial", "custom_item"),
			new CustomItem(new FabricItemSettings().maxCount(16)));

	// Create an item group
	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(CUSTOM_ITEM))
		.displayName(Text.translatable("Custom Group"))
		.entries((context, entries) -> {
			entries.add(CUSTOM_ITEM);
		})
		.build();

	// Create a Villager activity
	public static final Activity PRAY = Activity.register("pray");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		// Make it furnace fuel or compostable
		FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 300);
		CompostingChanceRegistry.INSTANCE.add(CUSTOM_ITEM, 0.3f);
		Registry.register(Registries.ITEM_GROUP, new Identifier("tutorial", "custom_group"), ITEM_GROUP);
	}
}