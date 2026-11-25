package com.reverentworlds.posdelight.screen;

import com.reverentworlds.posdelight.menu.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class VendingMachineScreenHandler extends AbstractContainerMenu {
    private final IItemHandler itemHandler;
    private final int id;
    private final Inventory playerInventory;

    // 12 shelf slots + 1 payment slot + 1 output slot = 14
    private static final int TE_INVENTORY_SLOT_COUNT = 14;
    private static final int PLAYER_INVENTORY_START = TE_INVENTORY_SLOT_COUNT;
    private static final int PLAYER_INVENTORY_END = PLAYER_INVENTORY_START + 36;

    public VendingMachineScreenHandler(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(id, playerInventory, new ItemStackHandler(TE_INVENTORY_SLOT_COUNT));
    }

    public VendingMachineScreenHandler(int id, Inventory playerInventory, IItemHandler itemHandler) {
        super(ModMenuTypes.VENDING_MACHINE_MENU.get(), id);
        this.itemHandler = itemHandler;
        this.id = id;
        this.playerInventory = playerInventory;

        // 3x4 Vending Machine Shelf Slots
        int startX = 291;
        int startY = 57;
        int vendingSlotSize = 25;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                int index = col + row * 3;
                this.addSlot(new SlotItemHandler(itemHandler, index, startX + col * vendingSlotSize, startY + (row * 10) + (row * vendingSlotSize)));
            }
        }

        int slotSize = 25;
        // Payment Slot
        this.addSlot(new SlotItemHandler(itemHandler, 12, 403, 197));

        // Drop slot
        this.addSlot(new SlotItemHandler(itemHandler, 13, 319, 223));

        int invStartX = 23;
        int invStartY = 95;

        // Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(
                        playerInventory,
                        col + row * 9 + 9,
                        invStartX + col * slotSize,
                        invStartY + row * slotSize
                ));
            }
        }

        // Player Hotbar
        int hotbarY = 176;
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(
                    playerInventory,
                    col,
                    invStartX + col * slotSize,
                    hotbarY
            ));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = this.slots.get(index);
        if (!sourceSlot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Clicked a vending machine slot -> move to player inventory/hotbar
        if (index < TE_INVENTORY_SLOT_COUNT) {
            if (!this.moveItemStackTo(sourceStack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
        }
        // Clicked a player inventory/hotbar slot -> move to vending machine slots
        else if (index < PLAYER_INVENTORY_END) {
            if (!this.moveItemStackTo(sourceStack, 0, TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
