package kono.gtma.data.recipe.handler;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import kono.gtma.common.data.GTMARecipeTypes;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class AtmosphereCollectorHandler {

    public static void register(Consumer<FinishedRecipe> provider) {
        // dust to gas
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("gas_from_dust/overword")
                .inputItems(TagPrefix.dust, GTMaterials.Stone, 5)
                .circuitMeta(20)
                .outputFluids(GTMaterials.Air.getFluid(10000))
                .duration(400).EUt(64).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("gas_from_dust/nether")
                .inputItems(TagPrefix.dust, GTMaterials.Netherrack, 5)
                .circuitMeta(20)
                .outputFluids(GTMaterials.NetherAir.getFluid(10000))
                .duration(400).EUt(256).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("gas_from_dust/end")
                .inputItems(TagPrefix.dust, GTMaterials.Endstone, 5)
                .circuitMeta(20)
                .outputFluids(GTMaterials.EnderAir.getFluid(10000))
                .duration(400).EUt(1024).save(provider);
        // Liquid Air
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air/overworld")
                .circuitMeta(1)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidAir.getFluid(10000))
                .dimension(new ResourceLocation("overworld"))
                .duration(200).EUt(VA[EV]).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air/nether")
                .circuitMeta(2)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidNetherAir.getFluid(10000))
                .dimension(new ResourceLocation("the_nether"))
                .duration(200).EUt(VA[IV]).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air/end")
                .circuitMeta(3)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidEnderAir.getFluid(10000))
                .dimension(new ResourceLocation("the_end"))
                .duration(200).EUt(VA[LuV]).save(provider);
        // Liquid Air from dust
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air_from_dust/overworld")
                .inputItems(TagPrefix.dust, GTMaterials.Stone, 5)
                .circuitMeta(21)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidAir.getFluid(10000))
                .duration(400).EUt(VA[IV]).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air_from_dust/nether")
                .inputItems(TagPrefix.dust, GTMaterials.Netherrack, 5)
                .circuitMeta(21)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidNetherAir.getFluid(10000))
                .duration(400).EUt(VA[LuV]).save(provider);
        GTMARecipeTypes.ATMOSPHERE_COLLECTOR
                .recipeBuilder("liquid_air_from_dust/end")
                .inputItems(TagPrefix.dust, GTMaterials.Endstone, 5)
                .circuitMeta(21)
                .inputFluids(GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .outputFluids(GTMaterials.LiquidEnderAir.getFluid(10000))
                .duration(400).EUt(VA[ZPM]).save(provider);
    }
}
