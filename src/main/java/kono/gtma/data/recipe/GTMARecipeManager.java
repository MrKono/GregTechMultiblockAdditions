package kono.gtma.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import kono.gtma.data.recipe.handler.AtmosphereCollectorHandler;
import kono.gtma.data.recipe.handler.OreFactoryHandler;

public class GTMARecipeManager {

    public static void addRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(MaterialFlags.NO_UNIFICATION)) {
                continue;
            }
            OreFactoryHandler.register(provider, material);
        }
        AtmosphereCollectorHandler.register(provider);
        GTMACraftingLoader.register(provider);
        GTMAMachineLoader.register(provider);
        GTMAMaterialLoader.register(provider);
        GTMAMaterialInfoLoader.init();
    }

    public static void removeRecipes(Consumer<ResourceLocation> consumer) {}
}
