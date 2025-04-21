package kono.gtma.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMAMaterials.*;

public class GTMAMaterial {

    public static void register() {
        higher();
    }

    public static void higher() {
        Tribaloy = new Material.Builder(modId("tribally"))
                .ingot(4).fluid()
                .color(0x6A7A8F).iconSet(METALLIC)
                .appendFlags(STD_METAL, GENERATE_PLATE, GENERATE_FRAME)
                .components(Stellite100, 2, HastelloyX, 1, Cobalt, 2, Silicon, 1, Manganese, 1, Carbon, 1)
                .blast(b -> b.temp(4300, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[EV], 1200))
                .buildAndRegister();
        Staballoy = new Material.Builder(modId("staballoy"))
                .ingot(3).fluid()
                .color(0x444B42).iconSet(METALLIC)
                .appendFlags(STD_METAL, GENERATE_PLATE, GENERATE_FRAME)
                .components(Uranium238, 9, Titanium, 1)
                .blast(b -> b.temp(3450, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[EV], 650))
                .buildAndRegister();
    }
}
