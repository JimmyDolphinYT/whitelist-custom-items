package com.qrtstudios.wci;

import com.qrtstudios.wci.entity.renderer.HellfireArrowRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(WhitelistCustomItems.HELLOUM_ARROW, HellfireArrowRenderer::new);

    }
}
