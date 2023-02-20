package com.qrtstudios.wci.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class Kitana extends SwordItem {
    public static int amp;
    public Kitana(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int amplifier) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        amp = amplifier;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (selected) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 35, amp, true, false));
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 35, 0, true, false));
                    entity.isInRange(entity, 5);
                }
            }
        }
    }

}
