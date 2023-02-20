package com.qrtstudios.wci.entity;

import com.qrtstudios.wci.WhitelistCustomItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;


import static com.qrtstudios.wci.items.HellfireBow.isFire;

public class HellFireArrow extends PersistentProjectileEntity {

    public HellFireArrow(EntityType<? extends HellFireArrow> entityType, World world) {
        super(entityType, world);
    }

    public HellFireArrow(World world, double x, double y, double z) {
        super(EntityType.ARROW, x, y, z, world);
    }

    public HellFireArrow(World world, LivingEntity owner) {
        super(EntityType.ARROW, owner, world);
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

    }

    private void spawnParticles(int amount) {
         this.world.addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 1, 1, 1);
        }
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.world.isClient) {
                boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
                int explosionPower = 1;
                this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), (float) explosionPower, bl, bl ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
                this.discard();
        }
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
        }
    }
    @Override
    protected ItemStack asItemStack() {
            return WhitelistCustomItems.HELLFIRE_ARROW.getDefaultStack();
    }

}
