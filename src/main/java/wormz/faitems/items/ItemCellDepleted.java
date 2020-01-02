package wormz.faitems.items;

import ic2.api.reactor.IReactor;
import nc.enumm.IFissionStats;
import nc.enumm.IItemMeta;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCellDepleted<T extends Enum<T> & IStringSerializable & IItemMeta & IFissionStats> extends ItemCellBreeder {

    public ItemCellDepleted(Class<T> enumm, String name) {
        super(enumm, name);
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s:cell_depleted.name", this.getRegistryName().getNamespace());
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        return false;
    }

    @Override
    public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        int k = 0;
        for (int i = 0; i < this.values.length; i++) {
            ModelLoader.setCustomModelResourceLocation(this, k, new ModelResourceLocation("faitems:cell", "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, k + 1, new ModelResourceLocation("faitems:dual_cell", "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, k + 2, new ModelResourceLocation("faitems:quad_cell", "inventory"));
            k += 3;
        }
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 2);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList items) {
        if (this.isInCreativeTab(tab)) {
            int k = 0;
            for (int i = 0; i < super.values.length; i++) {
                ItemStack cell = new ItemStack(this, 1, k);
                ItemStack dual_cell = new ItemStack(this, 1, k + 1);
                ItemStack quad_cell = new ItemStack(this, 1, k + 2);
                items.add(cell);
                items.add(dual_cell);
                items.add(quad_cell);
                k += 3;
            }
        }
    }
}
