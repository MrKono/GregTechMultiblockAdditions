package kono.gtma.data.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Block;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import kono.gtma.api.utils.Triple;
import kono.gtma.common.data.GTMAMaterials;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.gtma.api.utils.GTMAValues.casingAmount;
import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMABlocks.*;
import static kono.gtma.common.data.GTMAMultiblockMachines.*;

import com.tterrag.registrate.util.entry.BlockEntry;
import it.unimi.dsi.fastutil.Pair;

public class GTMACraftingLoader {

    public static void register(Consumer<FinishedRecipe> provider) {
        machineRecipe(provider);
        casingRecipe(provider);
    }

    public static void machineRecipe(Consumer<FinishedRecipe> provider) {
        // Tank Valves
        List<Triple<BlockEntry<Block>, Material, MachineDefinition>> tankValves = new ArrayList<>();
        tankValves.add(new Triple<>(TANK_WALL_INVAR, GTMaterials.Invar, INVAR_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_ALUMINIUM, GTMaterials.Aluminium, ALUMINIUM_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_STAINLESS, GTMaterials.StainlessSteel, STAINLESS_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_NETHERITE, GTMaterials.Netherite, NETHERITE_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_TITANIUM, GTMaterials.Titanium, TITANIUM_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_TUNGSTEN, GTMaterials.Tungsten, TUNGSTEN_TANK_VALVE));
        tankValves.add(new Triple<>(TANK_WALL_TUNGSTENSTEEL, GTMaterials.TungstenSteel, TUNGSTENSTEEL_TANK_VALVE));
        for (Triple<BlockEntry<Block>, Material, MachineDefinition> entry : tankValves) {
            VanillaRecipeHelper.addShapedRecipe(provider, true, modId(entry.second.getName() + "_tank_valve"),
                    entry.third.asStack(), " R ", "hCw", " O ",
                    'O', new UnificationEntry(TagPrefix.rotor, entry.second),
                    'R', new UnificationEntry(TagPrefix.ring, entry.second),
                    'C', entry.first);
        }
        // Multiblock tanks
        List<Triple<BlockEntry<Block>, Material, MachineDefinition>> tanks = new ArrayList<>();
        tanks.add(new Triple<>(TANK_WALL_INVAR, GTMaterials.Invar, INVAR_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_ALUMINIUM, GTMaterials.Aluminium, ALUMINIUM_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_STAINLESS, GTMaterials.StainlessSteel, STAINLESS_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_NETHERITE, GTMaterials.Netherite, NETHERITE_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_TITANIUM, GTMaterials.Titanium, TITANIUM_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_TUNGSTEN, GTMaterials.Tungsten, TUNGSTEN_MULTIBLOCK_TANK));
        tanks.add(new Triple<>(TANK_WALL_TUNGSTENSTEEL, GTMaterials.TungstenSteel, TUNGSTENSTEEL_MULTIBLOCK_TANK));
        for (Triple<BlockEntry<Block>, Material, MachineDefinition> entry : tanks) {
            VanillaRecipeHelper.addShapedRecipe(provider, true, modId(entry.second.getName() + "_tank"),
                    entry.third.asStack(), " R ", "hCw", " R ",
                    'R', new UnificationEntry(TagPrefix.ring, entry.second),
                    'C', entry.first);
        }
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("atmosphere_collector"),
                ATMOSPHERE_COLLECTOR.asStack(), "TFT", "CMC", "SFS",
                'T', new UnificationEntry(TagPrefix.rotor, GTMAMaterials.Tribaloy),
                'F', GTItems.FLUID_FILTER.asStack(),
                'C', CustomTags.LuV_CIRCUITS,
                'M', GTMachines.GAS_COLLECTOR[IV].asStack(),
                'S', new UnificationEntry(TagPrefix.rotor, GTMAMaterials.Staballoy));
    }

    public static void casingRecipe(Consumer<FinishedRecipe> provider) {
        // TankWalls
        List<Pair<Material, BlockEntry<Block>>> walls = new ArrayList<>();
        walls.add(Pair.of(GTMaterials.Invar, TANK_WALL_INVAR));
        walls.add(Pair.of(GTMaterials.Aluminium, TANK_WALL_ALUMINIUM));
        walls.add(Pair.of(GTMaterials.StainlessSteel, TANK_WALL_STAINLESS));
        walls.add(Pair.of(GTMaterials.Netherite, TANK_WALL_NETHERITE));
        walls.add(Pair.of(GTMaterials.Titanium, TANK_WALL_TITANIUM));
        walls.add(Pair.of(GTMaterials.Tungsten, TANK_WALL_TUNGSTEN));
        walls.add(Pair.of(GTMaterials.TungstenSteel, TANK_WALL_TUNGSTENSTEEL));
        for (Pair<Material, BlockEntry<Block>> entry : walls) {
            VanillaRecipeHelper.addShapedRecipe(provider, true, modId(entry.left().getName() + "_tank_wall"),
                    entry.second().asStack(casingAmount), "PPP", "wFh", "PPP",
                    'P', new UnificationEntry(TagPrefix.plate, entry.left()),
                    'F', new UnificationEntry(TagPrefix.frameGt, entry.left()));
        }
        List<Triple<BlockEntry<Block>, BlockEntry<Block>, Material>> wallConvert = new ArrayList<>();
        wallConvert.add(new Triple<>(GTBlocks.CASING_INVAR_HEATPROOF, TANK_WALL_INVAR, GTMaterials.Invar));
        wallConvert.add(new Triple<>(GTBlocks.CASING_ALUMINIUM_FROSTPROOF, TANK_WALL_ALUMINIUM, GTMaterials.Aluminium));
        wallConvert.add(new Triple<>(GTBlocks.CASING_STAINLESS_CLEAN, TANK_WALL_STAINLESS, GTMaterials.StainlessSteel));
        wallConvert.add(new Triple<>(GTBlocks.CASING_TITANIUM_STABLE, TANK_WALL_TITANIUM, GTMaterials.Titanium));
        wallConvert.add(
                new Triple<>(GTBlocks.CASING_TUNGSTENSTEEL_ROBUST, TANK_WALL_TUNGSTENSTEEL, GTMaterials.TungstenSteel));
        for (Triple<BlockEntry<Block>, BlockEntry<Block>, Material> entry : wallConvert) {
            VanillaRecipeHelper.addShapelessRecipe(provider, modId("casing_to_wall_" + entry.third.getName()),
                    entry.first.asStack(), entry.second);
            VanillaRecipeHelper.addShapelessRecipe(provider, modId("wall_to_casing" + entry.third.getName()),
                    entry.second.asStack(), entry.first);
        }
        // Gearbox Casing
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("gearbox/iridium"),
                IRIDIUM_GEARBOX_CASING.asStack(casingAmount), "PhP", "GFG", "PwP",
                'P', new UnificationEntry(TagPrefix.plate, GTMaterials.Iridium),
                'G', new UnificationEntry(TagPrefix.gear, GTMaterials.Iridium),
                'F', new UnificationEntry(TagPrefix.frameGt, GTMaterials.Iridium));
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("gearbox/americium"),
                AMERICIUM_GEARBOX_CASING.asStack(casingAmount), "PhP", "GFG", "PwP",
                'P', new UnificationEntry(TagPrefix.plate, GTMaterials.Americium),
                'G', new UnificationEntry(TagPrefix.gear, GTMaterials.Americium),
                'F', new UnificationEntry(TagPrefix.frameGt, GTMaterials.Americium));
        // Pipe Casing
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("pipe_casing/iridium"),
                IRIDIUM_PIPE_CASING.asStack(casingAmount), "PNP", "NFN", "PNP",
                'P', new UnificationEntry(TagPrefix.plate, GTMaterials.Iridium),
                'N', new UnificationEntry(TagPrefix.pipeNormalFluid, GTMaterials.Iridium),
                'F', new UnificationEntry(TagPrefix.frameGt, GTMaterials.Iridium));
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("pipe_casing/americium"),
                AMERICIUM_PIPE_CASING.asStack(casingAmount), "PNP", "NFN", "PNP",
                'P', new UnificationEntry(TagPrefix.plate, GTMaterials.Americium),
                'N', new UnificationEntry(TagPrefix.pipeNormalItem, GTMaterials.Americium),
                'F', new UnificationEntry(TagPrefix.frameGt, GTMaterials.Americium));
        // Dust Proof Casing
        VanillaRecipeHelper.addShapedRecipe(provider, true, modId("metal/dust_proof_tribaloy"),
                CASING_TORIBALOY_DUSTPROOF.asStack(casingAmount), "PhP", "PFP", "PwP",
                'P', new UnificationEntry(TagPrefix.plate, GTMAMaterials.Tribaloy),
                'F', new UnificationEntry(TagPrefix.frameGt, GTMAMaterials.Tribaloy));
    }

    public static void miscRecipe(Consumer<FinishedRecipe> provider) {}
}
