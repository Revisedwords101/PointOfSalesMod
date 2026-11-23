package com.reverentworlds.posdelight.block;

import com.mojang.serialization.MapCodec;
import com.reverentworlds.posdelight.entity.VendingMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlock extends BaseEntityBlock {
    public static final MapCodec<VendingMachineBlock> CODEC = simpleCodec(VendingMachineBlock::new);

    public VendingMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VendingMachineBlockEntity(pos, state);
    }
}
