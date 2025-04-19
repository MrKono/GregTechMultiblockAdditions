package kono.gtma.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class GTMARecipeTypes {

    public static final GTRecipeType ORE_FACTORY = GTRecipeTypes
            .register("ore_factory", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(2, 12, 2, 0)
            .setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.IN_SLOT_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MACERATOR)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(30));

    public static void init() {}
}
