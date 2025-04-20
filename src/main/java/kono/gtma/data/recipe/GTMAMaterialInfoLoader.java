package kono.gtma.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.api.GTValues.M;
import static kono.gtma.api.utils.GTMAValues.casingAmount;
import static kono.gtma.common.data.GTMABlocks.*;
import static kono.gtma.common.data.GTMAMultiblockMachines.*;

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
        // Tank Wall
        ChemicalHelper.registerMaterialInfo(TANK_WALL_INVAR,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Invar, M * 8 / casingAmount)));
        ChemicalHelper.registerMaterialInfo(TANK_WALL_NETHERITE,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Netherite, M * 8 / casingAmount)));
        ChemicalHelper.registerMaterialInfo(TANK_WALL_TITANIUM,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Titanium, M * 8 / casingAmount)));
        ChemicalHelper.registerMaterialInfo(TANK_WALL_TUNGSTEN,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Tungsten, M * 8 / casingAmount)));
        ChemicalHelper.registerMaterialInfo(TANK_WALL_TUNGSTENSTEEL,
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.TungstenSteel, M * 8 / casingAmount)));
        // Tank Valves (Walls + 4 + 0.25)
        ChemicalHelper.registerMaterialInfo(INVAR_TANK_VALVE.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Invar, (M * 8 / casingAmount) + M * 4 + (M / 4))));
        ChemicalHelper.registerMaterialInfo(NETHERITE_TANK_VALVE.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Netherite, (M * 8 / casingAmount) + M * 4 + (M / 4))));
        ChemicalHelper.registerMaterialInfo(TITANIUM_TANK_VALVE.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Titanium, (M * 8 / casingAmount) + M * 4 + (M / 4))));
        ChemicalHelper.registerMaterialInfo(TUNGSTEN_TANK_VALVE.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Tungsten, (M * 8 / casingAmount) + M * 4 + (M / 4))));
        ChemicalHelper.registerMaterialInfo(TUNGSTENSTEEL_TANK_VALVE.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.TungstenSteel, (M * 8 / casingAmount) + M * 4 + (M / 4))));
        // Multiblock Tanks (Wall + 0.5
        ChemicalHelper.registerMaterialInfo(INVAR_MULTIBLOCK_TANK.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Invar, (M * 8 / casingAmount) + (M / 2))));
        ChemicalHelper.registerMaterialInfo(NETHERITE_MULTIBLOCK_TANK.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Netherite, (M * 8 / casingAmount) + (M / 2))));
        ChemicalHelper.registerMaterialInfo(TITANIUM_MULTIBLOCK_TANK.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Titanium, (M * 8 / casingAmount) + (M / 2))));
        ChemicalHelper.registerMaterialInfo(TUNGSTEN_MULTIBLOCK_TANK.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.Tungsten, (M * 8 / casingAmount) + (M / 2))));
        ChemicalHelper.registerMaterialInfo(TUNGSTENSTEEL_MULTIBLOCK_TANK.getItem(),
                new ItemMaterialInfo(
                        new MaterialStack(GTMaterials.TungstenSteel, (M * 8 / casingAmount) + (M / 2))));
    }
}
