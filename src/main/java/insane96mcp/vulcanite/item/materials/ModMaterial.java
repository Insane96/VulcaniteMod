package insane96mcp.vulcanite.item.materials;

import com.zimonishim.ziheasymodding.modMaterial.ZIHMaterial;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;

public class ModMaterial {

    public static ZIHMaterial vulcaniteMaterial = new ZIHMaterial("vulcanite", ModItems.VULCANITE_INGOT.get());
    public static Tier TOOL_VULCANITE = vulcaniteMaterial.getItemTier();
    public static ArmorMaterial ARMOR_VULCANITE = vulcaniteMaterial.getArmorMaterial();
}