package com.reverentworlds.posdelight.menu; // adjust package if needed

import com.reverentworlds.posdelight.POSDelight;
import com.reverentworlds.posdelight.screen.VendingMachineScreenHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, POSDelight.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<VendingMachineScreenHandler>> VENDING_MACHINE_MENU =
            MENUS.register("vending_machine_menu", () ->
                    IMenuTypeExtension.create(VendingMachineScreenHandler::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
