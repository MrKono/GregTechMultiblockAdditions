package kono.gtma.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import kono.gtma.common.data.GTMAMultiblockMachines;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.gtma.api.utils.GTMAValues.casingAmount;
import static kono.gtma.common.data.GTMABlocks.*;

public class GTMAMachineLoader {

    public static void register(Consumer<FinishedRecipe> provider) {
        machineRecipe(provider);
        casingRecipe(provider);
    }

    public static void machineRecipe(Consumer<FinishedRecipe> provider) {
        // Ore Processing Factory
        GTRecipeTypes.ASSEMBLER_RECIPES
                .recipeBuilder("ore_processing_factory")
                .inputItems(GTMachines.MACERATOR[IV])
                .inputItems(GTMachines.ORE_WASHER[IV])
                .inputItems(GTMachines.CENTRIFUGE[IV])
                .inputItems(GTMachines.SIFTER[IV])
                .inputItems(GTMachines.CHEMICAL_BATH[IV])
                .inputItems(GTMachines.THERMAL_CENTRIFUGE[IV])
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144 * 8))
                .outputItems(GTMAMultiblockMachines.ORE_FACTORY)
                .duration(10 * 20).EUt(VA[IV]).save(provider);

        // Industrial Ore Processing Factory
        GTRecipeTypes.ASSEMBLY_LINE_RECIPES
                .recipeBuilder("industrial_ore_processing_factory")
                .inputItems(GTMAMultiblockMachines.ORE_FACTORY)
                .inputItems(TagPrefix.gearSmall, GTMaterials.TungstenCarbide, 8)
                .inputItems(TagPrefix.gear, GTMaterials.Tritanium, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_UV, 2)
                .inputItems(CustomTags.UHV_CIRCUITS, 1)
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputFluids(GTMaterials.SolderingAlloy.getFluid(144 * 16))
                .inputFluids(GTMaterials.Lubricant.getFluid(4000))
                .outputItems(GTMAMultiblockMachines.INDUSTRIAL_ORE_FACTORY)
                .stationResearch(b -> b
                        .researchStack(GTMAMultiblockMachines.ORE_FACTORY.asStack())
                        .CWUt(144).EUt(VA[ZPM]))
                .duration(60 * 20).EUt(VA[UHV]).save(provider);
    }

    public static void casingRecipe(Consumer<FinishedRecipe> provider) {
        // Factory Casing
        GTRecipeTypes.ASSEMBLER_RECIPES
                .recipeBuilder("factory_casing")
                .inputItems(GTBlocks.CASING_STAINLESS_CLEAN)
                .inputFluids(GTMaterials.Iridium.getFluid(144))
                .outputItems(FACTORY_CASING)
                .duration(5 * 20).EUt(VA[LV]).save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES
                .recipeBuilder("advanced_factory_casing")
                .inputItems(FACTORY_CASING)
                .inputFluids(GTMaterials.Americium.getFluid(144))
                .outputItems(ADVANCED_FACTORY_CASING)
                .duration(5 * 20).EUt(VA[LV]).save(provider);
        // Gearbox Casing
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("gearbox_iridium")
                .inputItems(TagPrefix.plate, GTMaterials.Iridium, 6)
                .inputItems(TagPrefix.gear, GTMaterials.Iridium, 2)
                .inputItems(TagPrefix.frameGt, GTMaterials.Iridium)
                .circuitMeta(4)
                .outputItems(IRIDIUM_GEARBOX_CASING, casingAmount)
                .duration(5 * 20).EUt(VA[LV]).save(provider);
        GTRecipeTypes.ASSEMBLER_RECIPES
                .recipeBuilder("gearbox_americium")
                .inputItems(TagPrefix.plate, GTMaterials.Americium, 6)
                .inputItems(TagPrefix.gear, GTMaterials.Americium, 2)
                .inputItems(TagPrefix.frameGt, GTMaterials.Americium)
                .circuitMeta(4)
                .outputItems(AMERICIUM_GEARBOX_CASING, casingAmount)
                .duration(5 * 20).EUt(VA[LV]).save(provider);
    }

    public static void miscRecipe(Consumer<FinishedRecipe> provider) {}
}
