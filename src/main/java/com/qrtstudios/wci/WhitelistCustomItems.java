package com.qrtstudios.wci;

import com.qrtstudios.wci.entity.HellFireArrow;
import com.qrtstudios.wci.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WhitelistCustomItems implements ModInitializer {

	public static final String MODID = "wci";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);


	public static final Item KITANA = new Kitana(ToolMaterials.DIAMOND, 8, 1.6f, new Item.Settings().group(ItemGroup.COMBAT), 0);
	public static final Item NETHERITE_KITANA = new Kitana(ToolMaterials.NETHERITE, 9, 1.6f, new Item.Settings().group(ItemGroup.COMBAT), 1);
	public static final Item MULTITOOL = new MultiTool(5, 1.2f, 6, ToolMaterials.DIAMOND, BlockTags.NEEDS_DIAMOND_TOOL , new Item.Settings().maxDamage(750).group(ItemGroup.COMBAT));
	public static final Item NETHERITE_MULTITOOL = new MultiTool(6, 1.2f, 7, ToolMaterials.NETHERITE, BlockTags.NEEDS_DIAMOND_TOOL , new Item.Settings().maxDamage(1250).group(ItemGroup.COMBAT));
	public static final Item SHADOW_SCYTHE = new ShadowScythe(ToolMaterials.DIAMOND, 9, 1, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item NETHERITE_SHADOW_SCYTHE = new ShadowScythe(ToolMaterials.NETHERITE, 10, 1, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item HELLFIRE_ARROW = new HellfireArrowItem(new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item HELLFIRE_BOW = new HellfireBow(new Item.Settings().group(ItemGroup.COMBAT));

	public static final EntityType<HellFireArrow> HELLOUM_ARROW =
			Registry.register(
					Registry.ENTITY_TYPE,
					new Identifier(MODID, "helloum_arrow"),
					FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<HellFireArrow>) HellFireArrow::new)
							.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build()
			);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MODID + "!");

		Registry.register(Registry.ITEM, new Identifier(MODID, "diamond_kitana"), KITANA);
		Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_kitana"), NETHERITE_KITANA);
		Registry.register(Registry.ITEM, new Identifier(MODID, "femur_multi_tool"), MULTITOOL);
		Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_femur_multi_tool"), NETHERITE_MULTITOOL);
		Registry.register(Registry.ITEM, new Identifier(MODID, "diamond_shadow_scythe"), SHADOW_SCYTHE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_shadow_scythe"), NETHERITE_SHADOW_SCYTHE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "helloum_arrow"), HELLFIRE_ARROW);
		Registry.register(Registry.ITEM, new Identifier(MODID, "helloum_fire_bow"), HELLFIRE_BOW);
	}
}
