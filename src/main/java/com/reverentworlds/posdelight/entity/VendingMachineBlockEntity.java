package com.reverentworlds.posdelight.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
// Import your ModBlockEntities registry class here (e.g., import com.reverentworlds.posdelight.registry.ModBlockEntities;)

public class VendingMachineBlockEntity extends BlockEntity {
    public final ItemStackHandler vendingInventory = new ItemStackHandler(12) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VENDING_MACHINE.get(), pos, state);
    }

    public void clearContents() {
        for (int i = 0; i < vendingInventory.getSlots(); i++) {
            vendingInventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(vendingInventory.getSlots());
        for (int i = 0; i < vendingInventory.getSlots(); i++) {
            inv.setItem(i, vendingInventory.getStackInSlot(i));
        }

        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inv);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("vendingInventory", vendingInventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        vendingInventory.deserializeNBT(registries, tag.getCompound("vendingInventory"));
    }

    // --- Client Sync for BlockEntityRenderer ---
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
