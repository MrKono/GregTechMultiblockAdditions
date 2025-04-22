package kono.gtma.data.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.block.Block;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import kono.gtma.common.data.GTMAMaterials;

import static com.gregtechceu.gtceu.api.GTValues.M;
import static kono.gtma.api.utils.GTMAValues.casingAmount;
import static kono.gtma.common.data.GTMABlocks.*;
import static kono.gtma.common.data.GTMAMultiblockMachines.*;

import com.tterrag.registrate.util.entry.BlockEntry;
import it.unimi.dsi.fastutil.Pair;

public class GTMAMaterialInfoLoader {

    public static void init() {
        // Factory Casing (casing/config + 1 ingots)
        ChemicalHelper.registerMaterialInfo(FACTORY_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.StainlessSteel, (M * 8) / casingAmount),
                        new MaterialStack(GTMaterials.Iridium, M)));
        // Advanced Factory Casing (casing/config + Ir * 1 + Am * 1)
        ChemicalHelper.registerMaterialInfo(ADVANCED_FACTORY_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.StainlessSteel, (M * 8) / casingAmount),
                        new MaterialStack(GTMaterials.Iridium, M),
                        new MaterialStack(GTMaterials.Americium, M)));
        // Gear Box ((2 plates + 4 gears + frame) / config)
        ChemicalHelper.registerMaterialInfo(IRIDIUM_GEARBOX_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Iridium, ((M * 4) + (M * 4) * 2 + (M * 2)) / casingAmount)));
        ChemicalHelper.registerMaterialInfo(AMERICIUM_GEARBOX_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Americium, ((M * 4) + (M * 4) * 2 + (M * 2)) / casingAmount)));
        // Pipe Casing ((4 plates + 4 pipeNormals + frame) / config)
        ChemicalHelper.registerMaterialInfo(IRIDIUM_PIPE_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Iridium, ((M * 4) + (M * 3) * 4 + (M * 2)) / casingAmount)));
        ChemicalHelper.registerMaterialInfo(AMERICIUM_PIPE_CASING,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Americium, ((M * 4) + (M * 3) * 4 + (M * 2)) / casingAmount)));
        // Tank Walls and Standard Casing
        List<Pair<BlockEntry<Block>, Material>> walls = new ArrayList<>();
        walls.add(Pair.of(TANK_WALL_INVAR, GTMaterials.Invar));
        walls.add(Pair.of(TANK_WALL_ALUMINIUM, GTMaterials.Aluminium));
        walls.add(Pair.of(TANK_WALL_STAINLESS, GTMaterials.StainlessSteel));
        walls.add(Pair.of(TANK_WALL_NETHERITE, GTMaterials.Netherite));
        walls.add(Pair.of(TANK_WALL_TITANIUM, GTMaterials.Titanium));
        walls.add(Pair.of(TANK_WALL_TUNGSTEN, GTMaterials.Tungsten));
        walls.add(Pair.of(TANK_WALL_TUNGSTENSTEEL, GTMaterials.TungstenSteel));
        walls.add(Pair.of(CASING_TORIBALOY_DUSTPROOF, GTMAMaterials.Tribaloy));
        for (Pair<BlockEntry<Block>, Material> entry : walls) {
            ChemicalHelper.registerMaterialInfo(entry.left(),
                    new ItemMaterialInfo(
                            new MaterialStack(entry.right(), M * 8 / casingAmount)));
        }

        // Tank Valves (Wall + 4 + 0.25)
        List<Pair<MachineDefinition, Material>> valves = new ArrayList<>();
        valves.add(Pair.of(INVAR_TANK_VALVE, GTMaterials.Invar));
        valves.add(Pair.of(ALUMINIUM_TANK_VALVE, GTMaterials.Aluminium));
        valves.add(Pair.of(STAINLESS_TANK_VALVE, GTMaterials.StainlessSteel));
        valves.add(Pair.of(NETHERITE_TANK_VALVE, GTMaterials.Netherite));
        valves.add(Pair.of(TITANIUM_TANK_VALVE, GTMaterials.Titanium));
        valves.add(Pair.of(TUNGSTEN_TANK_VALVE, GTMaterials.Tungsten));
        valves.add(Pair.of(TUNGSTENSTEEL_TANK_VALVE, GTMaterials.TungstenSteel));
        for (Pair<MachineDefinition, Material> entry : valves) {
            ChemicalHelper.registerMaterialInfo(entry.left().getItem(),
                    new ItemMaterialInfo(
                            new MaterialStack(entry.right(), (M * 8 / casingAmount) + M * 4 + (M / 4))));
        }

        // Multiblock Tanks (Wall + 0.5)
        List<Pair<MachineDefinition, Material>> tanks = new ArrayList<>();
        tanks.add(Pair.of(INVAR_MULTIBLOCK_TANK, GTMaterials.Invar));
        tanks.add(Pair.of(ALUMINIUM_MULTIBLOCK_TANK, GTMaterials.Aluminium));
        tanks.add(Pair.of(STAINLESS_MULTIBLOCK_TANK, GTMaterials.StainlessSteel));
        tanks.add(Pair.of(NETHERITE_MULTIBLOCK_TANK, GTMaterials.Netherite));
        tanks.add(Pair.of(TITANIUM_MULTIBLOCK_TANK, GTMaterials.Titanium));
        tanks.add(Pair.of(TUNGSTEN_MULTIBLOCK_TANK, GTMaterials.Tungsten));
        tanks.add(Pair.of(TUNGSTENSTEEL_MULTIBLOCK_TANK, GTMaterials.TungstenSteel));
        for (Pair<MachineDefinition, Material> entry : tanks) {
            ChemicalHelper.registerMaterialInfo(entry.left().getItem(),
                    new ItemMaterialInfo(
                            new MaterialStack(entry.right(), (M * 8 / casingAmount) + (M / 2))));
        }
    }
}
