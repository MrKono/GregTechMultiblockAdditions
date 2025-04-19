package kono.gtma.common.data.materials;

import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class GTMAMaterialFlagAdditions {

    public static void init() {
        // Americium
        GTMaterials.Americium.addFlags(GENERATE_GEAR, GENERATE_FRAME);
        // Invar
        GTMaterials.Invar.addFlags(GENERATE_RING, GENERATE_BOLT_SCREW, GENERATE_ROTOR);
        // Netherite
        GTMaterials.Netherite.addFlags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME, GENERATE_RING, GENERATE_BOLT_SCREW,
                GENERATE_ROTOR);
        // Tungsten
        GTMaterials.Tungsten.addFlags(GENERATE_BOLT_SCREW, GENERATE_ROTOR);
    }
}
