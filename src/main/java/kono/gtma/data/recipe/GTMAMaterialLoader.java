package kono.gtma.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import kono.gtma.common.data.GTMAMaterials;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class GTMAMaterialLoader {

    public static void register(Consumer<FinishedRecipe> provider) {
        mixer(provider);
    }

    public static void mixer(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("staballoy")
                .inputItems(TagPrefix.dust, GTMaterials.Uranium238, 9)
                .inputItems(TagPrefix.dust, GTMaterials.Titanium)
                .circuitMeta(10)
                .outputItems(TagPrefix.dust, GTMAMaterials.Staballoy, 10)
                .EUt(VA[EV]).duration(40).save(provider);
    }
}
