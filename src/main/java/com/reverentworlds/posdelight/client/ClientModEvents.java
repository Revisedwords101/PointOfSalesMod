package com.reverentworlds.posdelight.client;

import com.reverentworlds.posdelight.client.model.VendingMachineModel;
import com.reverentworlds.posdelight.client.renderer.VendingMachineRenderer;
import com.reverentworlds.posdelight.entity.ModBlockEntities;
import com.reverentworlds.posdelight.menu.ModMenuTypes;
import com.reverentworlds.posdelight.screen.VendingMachineScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

// Note: Minecraft, ClientLevel, Inventory, and AbstractContainerMenu are now used in VendingMachineBlock.java
// via useWithoutItem() override instead of MouseEvent handling.


@EventBusSubscriber(modid = "posdelight", value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VendingMachineModel.LAYER_LOCATION, VendingMachineModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.VENDING_MACHINE.get(), VendingMachineRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.VENDING_MACHINE_MENU.get(), VendingMachineScreen::new);
    }


    // Note: Block GUI interaction is now handled by overriding useWithoutItem() in VendingMachineBlock.java
    // This ensures proper server-side validation and prevents client-side duplication issues.

    // The vending machine block's useWithoutItem method will open the GUI when right-clicked.
}