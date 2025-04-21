package kono.gtma.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.common.data.GTMABlocks;
import kono.gtma.common.data.GTMAMaterials;
import kono.gtma.common.data.GTMAMultiblockMachines;
import kono.gtma.common.data.GTMARecipeTypes;

@Mod.EventBusSubscriber(modid = GTMAValues.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTMAMultiblockMachines.init();
        GTMABlocks.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTMARecipeTypes.init();
    }

    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        GTMAMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialPost(PostMaterialEvent event) {}
}
