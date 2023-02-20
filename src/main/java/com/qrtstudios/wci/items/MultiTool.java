package com.qrtstudios.wci.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;

public class MultiTool extends MiningToolItem {
    public static float speedMultiplier;
    public MultiTool(float attackDamage, float attackSpeed, float speed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
        speedMultiplier = speed;
    }

    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isIn( BlockTags.AXE_MINEABLE)) {
            return speedMultiplier;
        }
        else if (state.isIn( BlockTags.SHOVEL_MINEABLE)) {
            return speedMultiplier;
        }
        else if (state.isIn( BlockTags.PICKAXE_MINEABLE)) {
            return speedMultiplier;
        }
        else return 1;
    }


}
