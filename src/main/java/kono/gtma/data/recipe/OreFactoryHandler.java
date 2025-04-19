package kono.gtma.data.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import kono.gtma.common.data.GTMARecipeTypes;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT;

import com.mojang.datafixers.util.Pair;

public class OreFactoryHandler {

    private OreFactoryHandler() {}

    private static final Map<TagPrefix, Integer> processMap = new HashMap<>();

    static {
        processMap.put(TagPrefix.rawOre, 1);
        processMap.put(TagPrefix.rawOreBlock, 9);
    }

    public static void register(@NotNull Consumer<FinishedRecipe> provider, @NotNull Material material) {
        OreProperty property = material.getProperty(PropertyKey.ORE);
        if (property == null) {
            return;
        }
        processMap.forEach((tagPrefix, integer) -> {
            registerProcess1(provider, tagPrefix, integer, material, property);
            registerProcess2(provider, tagPrefix, integer, material, property);
            registerProcess3(provider, tagPrefix, integer, material, property);
            registerProcess4(provider, tagPrefix, integer, material, property);
            registerProcess5(provider, tagPrefix, integer, material, property);
            registerProcess6(provider, tagPrefix, integer, material, property);
            registerProcess7(provider, tagPrefix, integer, material, property);
            registerProcess8(provider, tagPrefix, integer, material, property);
            registerProcess9(provider, tagPrefix, integer, material, property);
            registerProcess10(provider, tagPrefix, integer, material, property);
            registerProcess11(provider, tagPrefix, integer, material, property);
        });
    }

    // Macerate -> Ore Washer -> Macerate -> Centrifuge
    public static void registerProcess1(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        int amount = 2 * outputAmount(property) * factor;

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process1/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(1)
                .duration(factor * 15 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(purifiedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850)
                .chancedOutput(pureByproduct(material, property).copyWithCount(amount), 1111, 0);
        builder.save(provider);
    }

    // Macerate -> Ore Washer -> Thermal Centrifuge -> Macerate
    public static void registerProcess2(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        ItemStack crushedCentrifugedStack = ChemicalHelper.get(TagPrefix.crushedRefined, material);

        if (crushedCentrifugedStack.isEmpty()) return;
        int amount = 2 * outputAmount(property) * factor;

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process2/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(2)
                .duration(factor * 30 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }

        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(purifiedCentrifugingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(centrifugedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850);
        builder.save(provider);
    }

    // Macerate -> Ore Washer -> Sifter -> Centrifuge
    public static void registerProcess3(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        int amount = 2 * outputAmount(property) * factor;

        if (!material.hasProperty(PropertyKey.GEM)) return;
        ItemStack exquisiteStack = ChemicalHelper.get(TagPrefix.gemExquisite, material);
        ItemStack flawlessStack = ChemicalHelper.get(TagPrefix.gemFlawless, material);
        ItemStack gemStack = ChemicalHelper.get(TagPrefix.gem, material);
        ItemStack flawedStack = ChemicalHelper.get(TagPrefix.gemFlawed, material);
        ItemStack chippedStack = ChemicalHelper.get(TagPrefix.gemChipped, material);

        exquisiteStack.setCount(amount);
        flawlessStack.setCount(amount);
        gemStack.setCount(amount);
        flawedStack.setCount(amount);
        chippedStack.setCount(amount);

        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process3/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(3)
                .duration(factor * 25 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(output.copyWithCount(amount), 833, 167);
        // Shifting
        if (material.hasFlag(HIGH_SIFTER_OUTPUT)) {
            builder.chancedOutput(exquisiteStack, 500, 150)
                    .chancedOutput(flawlessStack, 1500, 200)
                    .chancedOutput(gemStack, 5000, 1000);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2000, 500);
            if (!chippedStack.isEmpty())
                builder.chancedOutput(chippedStack, 3000, 350);
        } else {
            builder.chancedOutput(exquisiteStack, 300, 100)
                    .chancedOutput(flawlessStack, 1000, 150)
                    .chancedOutput(gemStack, 3500, 500);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2500, 300);
            if (!exquisiteStack.isEmpty())
                builder.chancedOutput(chippedStack, 3500, 400);
        }
        builder.save(provider);
    }

    // Macerate -> Macerate -> Centrifuge
    public static void registerProcess4(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        int amount = 2 * outputAmount(property) * factor;

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process4/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(4)
                .duration(factor * 10 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.chancedOutput(crushedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850)
                .chancedOutput(impureByproduct(material, property).copyWithCount(amount), 1111, 0);
        builder.save(provider);
    }

    // Macerate -> Thermal Centrifuge -> Macerate
    public static void registerProcess5(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        int amount = 2 * outputAmount(property) * factor;

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process5/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(5)
                .duration(factor * 25 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.chancedOutput(crushedCentrifugingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(crushedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850);
        builder.save(provider);
    }

    // Macerate -> Chemical Bathing -> Macerate -> Centrifuge
    public static void registerProcess6(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        if (property.getWashedIn().getFirst() == null) return;
        Pair<Material, Integer> washedInTuple = property.getWashedIn();
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        int amount = 2 * outputAmount(property) * factor;
        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process6/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(6)
                .duration(factor * 17 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(washedInTuple.getFirst().getFluid(washedInTuple.getSecond() * amount))
                .chancedOutput(bathingByproduct(material, property).copyWithCount(amount), 7000, 580)
                .chancedOutput(purifiedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850)
                .chancedOutput(pureByproduct(material, property).copyWithCount(amount), 1111, 0);
        builder.save(provider);
    }

    // Macerate -> Chemical Bathing -> Thermal Centrifuge -> Macerate
    public static void registerProcess7(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        if (property.getWashedIn().getFirst() == null) return;
        Pair<Material, Integer> washedInTuple = property.getWashedIn();
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);

        int amount = 2 * outputAmount(property) * factor;
        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process7/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(7)
                .duration(factor * 27 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(washedInTuple.getFirst().getFluid(washedInTuple.getSecond() * amount))
                .chancedOutput(bathingByproduct(material, property).copyWithCount(amount), 7000, 580)
                .chancedOutput(purifiedCentrifugingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(centrifugedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850);
        builder.save(provider);
    }

    // Macerate -> Ore Washer -> Sifter -> Centrifuge / Macerate (All Gems)
    public static void registerProcess8(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        int amount = 2 * outputAmount(property) * factor;
        if (!material.hasProperty(PropertyKey.GEM)) return;
        ItemStack exquisiteStack = ChemicalHelper.get(TagPrefix.dust, material);
        ItemStack flawlessStack = ChemicalHelper.get(TagPrefix.dust, material);
        ItemStack gemStack = ChemicalHelper.get(TagPrefix.dust, material);
        ItemStack flawedStack = ChemicalHelper.get(TagPrefix.dustSmall, material);
        ItemStack chippedStack = ChemicalHelper.get(TagPrefix.dustSmall, material);

        exquisiteStack.setCount(amount * 4);
        flawlessStack.setCount(amount * 2);
        gemStack.setCount(amount);
        flawedStack.setCount(amount * 2);
        chippedStack.setCount(amount);

        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process8/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(8)
                .duration(factor * 30 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(output.copyWithCount(amount), 833, 167);
        // Shifting
        if (material.hasFlag(HIGH_SIFTER_OUTPUT)) {
            builder.chancedOutput(exquisiteStack, 500, 150)
                    .chancedOutput(flawlessStack, 1500, 200)
                    .chancedOutput(gemStack, 5000, 1000);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2000, 500);
            if (!chippedStack.isEmpty())
                builder.chancedOutput(chippedStack, 3000, 350);
        } else {
            builder.chancedOutput(exquisiteStack, 300, 100)
                    .chancedOutput(flawlessStack, 1000, 150)
                    .chancedOutput(gemStack, 3500, 500);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2500, 300);
            if (!exquisiteStack.isEmpty())
                builder.chancedOutput(chippedStack, 3500, 400);
        }
        builder.save(provider);
    }

    // Macerate -> Ore Washer -> Sifter -> Centrifuge / Macerate (Flawed and Chipped)
    public static void registerProcess9(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                        @NotNull Material material,
                                        @NotNull OreProperty property) {
        int amount = 2 * outputAmount(property) * factor;
        if (!material.hasProperty(PropertyKey.GEM)) return;
        ItemStack exquisiteStack = ChemicalHelper.get(TagPrefix.gemExquisite, material);
        ItemStack flawlessStack = ChemicalHelper.get(TagPrefix.gemFlawless, material);
        ItemStack gemStack = ChemicalHelper.get(TagPrefix.gem, material);
        ItemStack flawedStack = ChemicalHelper.get(TagPrefix.dustSmall, material);
        ItemStack chippedStack = ChemicalHelper.get(TagPrefix.dustSmall, material);

        exquisiteStack.setCount(amount);
        flawlessStack.setCount(amount);
        gemStack.setCount(amount);
        flawedStack.setCount(amount * 2);
        chippedStack.setCount(amount);

        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);
        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process9/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(9)
                .duration(factor * 30 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(output.copyWithCount(amount), 833, 167);
        // Shifting
        if (material.hasFlag(HIGH_SIFTER_OUTPUT)) {
            builder.chancedOutput(exquisiteStack, 500, 150)
                    .chancedOutput(flawlessStack, 1500, 200)
                    .chancedOutput(gemStack, 5000, 1000);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2000, 500);
            if (!chippedStack.isEmpty())
                builder.chancedOutput(chippedStack, 3000, 350);
        } else {
            builder.chancedOutput(exquisiteStack, 300, 100)
                    .chancedOutput(flawlessStack, 1000, 150)
                    .chancedOutput(gemStack, 3500, 500);
            if (!flawedStack.isEmpty())
                builder.chancedOutput(flawedStack, 2500, 300);
            if (!exquisiteStack.isEmpty())
                builder.chancedOutput(chippedStack, 3500, 400);
        }
        builder.save(provider);
    }

    // Macerate -> Ore Washer -> Macerate -> Electromagnetic Separating
    public static void registerProcess10(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                         @NotNull Material material,
                                         @NotNull OreProperty property) {
        if (property.getSeparatedInto() == null || property.getSeparatedInto().isEmpty()) return;
        int amount = 2 * outputAmount(property) * factor;
        List<Material> separatedMaterial = property.getSeparatedInto();
        ItemStack separateStack = ChemicalHelper.get(TagPrefix.dust, separatedMaterial.get(0));
        TagPrefix fix = (separatedMaterial.get(separatedMaterial.size() - 1).getBlastTemperature() == 0 &&
                separatedMaterial.get(separatedMaterial.size() - 1).hasProperty(PropertyKey.INGOT)) ?
                        TagPrefix.nugget : TagPrefix.dust;
        ItemStack separatedStack2 = ChemicalHelper.get(fix, separatedMaterial.get(separatedMaterial.size() - 1),
                fix == TagPrefix.nugget ? 2 : 1);
        separateStack.setCount(amount);
        separatedStack2.setCount(amount);
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process10/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(10)
                .duration(factor * 25 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(GTMaterials.DistilledWater.getFluid(100 * amount))
                .chancedOutput(washingByproduct(material, property).copyWithCount(amount), 3333, 0)
                .chancedOutput(purifiedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850)
                .chancedOutput(separateStack, 1000, 250)
                .chancedOutput(separatedStack2, fix == TagPrefix.dust ? 500 : 2000,
                        fix == TagPrefix.dust ? 150 : 600);
        builder.save(provider);
    }

    // Macerate -> Chemical Bathing -> Macerate -> Electromagnetic Separating
    public static void registerProcess11(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, int factor,
                                         @NotNull Material material,
                                         @NotNull OreProperty property) {
        if (property.getWashedIn().getFirst() == null) return;
        Pair<Material, Integer> washedInTuple = property.getWashedIn();
        if (property.getSeparatedInto() == null || property.getSeparatedInto().isEmpty()) return;
        int amount = 2 * outputAmount(property) * factor;
        List<Material> separatedMaterial = property.getSeparatedInto();
        ItemStack separateStack = ChemicalHelper.get(TagPrefix.dust, separatedMaterial.get(0));
        TagPrefix fix = (separatedMaterial.get(separatedMaterial.size() - 1).getBlastTemperature() == 0 &&
                separatedMaterial.get(separatedMaterial.size() - 1).hasProperty(PropertyKey.INGOT)) ?
                        TagPrefix.nugget : TagPrefix.dust;
        ItemStack separatedStack2 = ChemicalHelper.get(fix, separatedMaterial.get(separatedMaterial.size() - 1),
                fix == TagPrefix.nugget ? 2 : 1);
        separateStack.setCount(amount);
        separatedStack2.setCount(amount);
        ItemStack output = ChemicalHelper.get(TagPrefix.dust, material);

        GTRecipeBuilder builder = GTMARecipeTypes.ORE_FACTORY
                .recipeBuilder("process11/" + prefix.name + "/" + material.getName())
                .inputItems(prefix, material)
                .outputItems(output.copyWithCount(amount))
                .chancedOutput(crushingByproduct(material, property), 1400, 850)
                .inputFluids(GTMaterials.Lubricant.getFluid(10))
                .circuitMeta(11)
                .duration(factor * 27 * 20);
        for (MaterialStack secondaryMaterial : TagPrefix.ore.secondaryMaterials()) {
            if (secondaryMaterial.material().hasProperty(PropertyKey.DUST)) {
                ItemStack dustStack = ChemicalHelper.getGem(secondaryMaterial);
                builder.chancedOutput(dustStack, 6700, 800);
            }
        }
        builder.inputFluids(washedInTuple.getFirst().getFluid(washedInTuple.getSecond() * amount))
                .chancedOutput(bathingByproduct(material, property).copyWithCount(amount), 7000, 580)
                .chancedOutput(purifiedCrushingByproduct(material, property).copyWithCount(amount), 1400, 850)
                .chancedOutput(separateStack, 1000, 250)
                .chancedOutput(separatedStack2, fix == TagPrefix.dust ? 500 : 2000,
                        fix == TagPrefix.dust ? 150 : 600);
        builder.save(provider);
    }

    public static int outputAmount(@NotNull OreProperty property) {
        return property.getOreMultiplier();
    }

    /**
     * @return byproduct of ore -> crushed
     */
    public static ItemStack crushingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        ItemStack byproductStack = ChemicalHelper.get(TagPrefix.gem, byproductMaterial);
        if (byproductStack.isEmpty()) byproductStack = ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
        return byproductStack;
    }

    /**
     * @return byproduct of crushed -> crushedPurified (Ore Washer)
     */
    public static ItemStack washingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushed -> crushedPurified (Chemical Bath)
     */
    public static ItemStack bathingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(3, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushedPurified -> crushedCentrifuged
     */
    public static ItemStack purifiedCentrifugingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(1, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushed -> crushedCentrifuged
     */
    public static ItemStack crushedCentrifugingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(1, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushed -> dustImpure
     */
    public static ItemStack crushedCrushingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushedPurified -> dustPure
     */
    public static ItemStack purifiedCrushingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(1, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of crushedCentrifuged -> dust
     */
    public static ItemStack centrifugedCrushingByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(2, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of dustImpure -> dust
     */
    public static ItemStack impureByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }

    /**
     * @return byproduct of dustPure -> dust
     */
    public static ItemStack pureByproduct(@NotNull Material material, @NotNull OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(1, material);
        return ChemicalHelper.get(TagPrefix.dust, byproductMaterial);
    }
}
