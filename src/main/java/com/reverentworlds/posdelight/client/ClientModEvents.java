package com.reverentworlds.posdelight.client;

import com.reverentworlds.posdelight.POSDelight;
import com.reverentworlds.posdelight.client.model.VendingMachineModel;
import com.reverentworlds.posdelight.client.renderer.VendingMachineRenderer;
import com.reverentworlds.posdelight.entity.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = POSDelight.MODID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VendingMachineModel.LAYER_LOCATION, VendingMachineModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.VENDING_MACHINE.get(), VendingMachineRenderer::new);
    }
}
