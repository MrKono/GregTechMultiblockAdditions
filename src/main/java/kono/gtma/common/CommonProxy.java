package kono.gtma.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;

import kono.gtma.common.data.GTMARegistration;
import kono.gtma.common.data.materials.GTMAMaterialFlagAdditions;
import kono.gtma.data.GTMADataGen;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::modifyMaterialInfo);
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GTMADataGen.init();
        GTMARegistration.REGISTRATE.registerRegistrate();
    }

    public void modifyMaterialInfo(MaterialEvent event) {
        GTMAMaterialFlagAdditions.init();
    }

    public void modifyMaterialInfoLowest(MaterialEvent event) {}
}
