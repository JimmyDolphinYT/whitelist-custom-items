package com.qrtstudios.wci.entity.renderer;

import com.qrtstudios.wci.entity.HellFireArrow;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class HellfireArrowRenderer extends ProjectileEntityRenderer<HellFireArrow> {
    public static final Identifier TEXTURE = new Identifier("textures/entity/projectiles/arrow.png");

    public HellfireArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(HellFireArrow entity) {
        return TEXTURE;
    }

}
