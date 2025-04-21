package kono.gtma.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import kono.gtma.common.data.materials.GTMAMaterial;

public class GTMAMaterials {

    public static void init() {
        GTMAMaterial.register();
    }

    public static Material Tribaloy;
    public static Material Staballoy;
}
