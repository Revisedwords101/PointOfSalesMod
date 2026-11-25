package com.reverentworlds.posdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.reverentworlds.posdelight.client.model.VendingMachineModel;
import com.reverentworlds.posdelight.entity.VendingMachineBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class VendingMachineRenderer implements BlockEntityRenderer<VendingMachineBlockEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "posdelight", "textures/entity/vending_machine.png");

    private final VendingMachineModel model;

    public VendingMachineRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new VendingMachineModel(context.bakeLayer(VendingMachineModel.LAYER_LOCATION));
    }

    @Override
    public void render(VendingMachineBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        // Center and flip Blockbench coordinates
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, -1);

        poseStack.popPose();
    }


}