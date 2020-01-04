package wormz.faitems.items;

import ic2.core.IC2;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wormz.faitems.faitems;

public class ItemCell extends Item {

    public ItemCell(String name) {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);//use metadata to represent cell number
        this.setNoRepair();
        this.setRegistryName(faitems.MODID, name);
        this.setCreativeTab(IC2.tabIC2);
        this.setTranslationKey(getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("faitems:cell", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation("faitems:dual_cell", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation("faitems:quad_cell", "inventory"));
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getTranslationKey() + ".name");
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            subItems.add(new ItemStack(this, 1, 0));
            subItems.add(new ItemStack(this, 1, 1));
            subItems.add(new ItemStack(this, 1, 2));
        }
    }
}
