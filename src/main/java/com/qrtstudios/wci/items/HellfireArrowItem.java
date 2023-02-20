package com.qrtstudios.wci.items;

import com.qrtstudios.wci.entity.HellFireArrow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HellfireArrowItem extends Item {
    public HellfireArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        HellFireArrow arrowEntity = new HellFireArrow(world, shooter);
        arrowEntity.initFromStack(stack);
        return arrowEntity;
    }
}
