package kono.gtma;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.common.data.GTMARegistration;

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
    public void addRecipes(Consumer<FinishedRecipe> provider) {}

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {}
}
