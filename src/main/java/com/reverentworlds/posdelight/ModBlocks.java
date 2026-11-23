package com.reverentworlds.posdelight;

import com.reverentworlds.posdelight.block.VendingMachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(POSDelight.MODID);

    public static final DeferredBlock<Block> VENDING_MACHINE = registerBlock("vending_machine",
            () -> new VendingMachineBlock(BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.METAL)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
