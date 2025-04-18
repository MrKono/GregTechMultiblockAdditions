package kono.gtma.common.data;

import net.minecraft.world.item.CreativeModeTab;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import static kono.gtma.api.utils.GTMAValues.*;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;

public class GTMACreativeTab {

    public static RegistryEntry<CreativeModeTab> BLOCKS = REGISTRATE.defaultCreativeTab("block",
            builder -> builder.displayItems(
                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("block", REGISTRATE))
                    .icon(GTMABlocks.FACTORY_CASING::asStack)
                    .title(REGISTRATE.addLang("itemGroup", modId("item"), MOD_NAME))
                    .build())
            .register();
}
