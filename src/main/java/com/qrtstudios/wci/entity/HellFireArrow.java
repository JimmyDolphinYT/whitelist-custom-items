package com.qrtstudios.wci.entity;

import com.qrtstudios.wci.WhitelistCustomItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import static com.qrtstudios.wci.items.HellfireBow.isFire;

public class HellFireArrow extends PersistentProjectileEntity {

	protected float explosionPower = 1;
	public static final String KEY_EXPLOSION_POWER = "ExplosionPower";

	public HellFireArrow(EntityType<? extends HellFireArrow> entityType, World world) {
		super(entityType, world);
	}

	public HellFireArrow(World world, double x, double y, double z) {
		super(WhitelistCustomItems.HELLOUM_ARROW, x, y, z, world);
	}

	public HellFireArrow(World world, LivingEntity owner) {
		super(WhitelistCustomItems.HELLOUM_ARROW, owner, world);
	}

	@Override
	public void handleStatus(byte status) {
		this.world.addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 1, 1, 1);
		super.handleStatus(status);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.world.isClient) {
			if (this.inGround) {
				if (this.inGroundTime % 5 == 0) {
					this.spawnParticles(1);
				}
			} else if (isFire) {
				this.spawnParticles(2);
			}
		}

	}

	public void initFromStack(ItemStack stack) {
		NbtCompound nbt = stack.getNbt();
		if (nbt == null) return;

		if (nbt.contains(KEY_EXPLOSION_POWER, NbtElement.FLOAT_TYPE)) {
			explosionPower = nbt.getFloat(KEY_EXPLOSION_POWER);
		}
	}

	private void spawnParticles(int amount) {
		this.world.addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 1, 1, 1);
	}

	protected void explode() {
		boolean canExplode = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
		if (!this.world.isClient)
			this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(),
					explosionPower, canExplode, canExplode ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);

		this.discard();
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		this.explode();
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if (!this.world.isClient) {

			Entity entity = entityHitResult.getEntity();
			Entity entity2 = this.getOwner();
			if (entity != entity2) {
				entity.damage(DamageSource.arrow(this, entity2), 2.0F);
				if (entity instanceof LivingEntity) {
					this.applyDamageEffects((LivingEntity) entity, entity);
				}
			}

			this.explode();
		}
	}

	@Override
	protected ItemStack asItemStack() {
		return WhitelistCustomItems.HELLFIRE_ARROW.getDefaultStack();
	}

}
