package com.qrtstudios.wci.mixin;

import com.qrtstudios.wci.WhitelistCustomItems;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayerEntity.class})
public abstract class ClientPlayerEntityMixin {
	@Shadow
	public abstract boolean isUsingItem();

	@Inject(method = "tick", at = @At("TAIL"))
	void tick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (this.isUsingItem() && entity.getActiveItem().isOf(WhitelistCustomItems.HELLFIRE_BOW)) {
			int i = entity.getItemUseTime() * 10;

			double radians = Math.toRadians(i + 90);
			double x = Math.cos(radians) * 0.8;
			double y = 0.5;
			double z = Math.sin(radians) * 0.8;


			entity.getWorld().addParticle(ParticleTypes.FLAME,
					entity.getX() + x, entity.getY() + y, entity.getZ() + z,
					0, 0, 0);
		}
	}
}
