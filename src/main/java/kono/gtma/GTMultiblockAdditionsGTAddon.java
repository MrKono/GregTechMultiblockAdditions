package kono.gtma;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.data.recipe.misc.RecyclingRecipes;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.common.data.GTMARegistration;
import kono.gtma.data.recipe.GTMARecipeManager;

import static kono.gtma.common.data.GTMABlocks.*;

@GTAddon
public class GTMultiblockAdditionsGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return GTMARegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return GTMAValues.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTMARecipeManager.addRecipes(provider);
        RecyclingRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        GTMARecipeManager.removeRecipes(consumer);
    }

    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event) {}
}
