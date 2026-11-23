package com.reverentworlds.posdelight.entity;

import com.reverentworlds.posdelight.POSDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, POSDelight.MODID);

    public static final Supplier<BlockEntityType<VendingMachineBlockEntity>> VENDING_MACHINE =
            BLOCK_ENTITIES.register("vending_machine", () ->
                    BlockEntityType.Builder.of(
                            VendingMachineBlockEntity::new, POSDelight.VENDING_MACHINE.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
