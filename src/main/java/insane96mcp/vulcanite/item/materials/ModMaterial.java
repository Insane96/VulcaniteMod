package insane96mcp.vulcanite.item.materials;

import com.zimonishim.ziheasymodding.modMaterial.ZIHMaterial;
import insane96mcp.vulcanite.setup.ModItems;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;

public class ModMaterial {

    public static ZIHMaterial vulcaniteMaterial = new ZIHMaterial("vulcanite", ModItems.VULCANITE_INGOT.get());
    public static IItemTier TOOL_VULCANITE = vulcaniteMaterial.getItemTier();
    public static IArmorMaterial ARMOR_VULCANITE = vulcaniteMaterial.getArmorMaterial();
}