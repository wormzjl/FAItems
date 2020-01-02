package wormz.faitems.items;

import ic2.core.item.reactor.AbstractDamageableReactorComponent;
import ic2.core.util.StackUtil;
import nc.enumm.IItemMeta;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wormz.faitems.faitems;

import javax.annotation.Nullable;

public class ItemCell extends AbstractDamageableReactorComponent {

    public ItemCell(String name) {
        //overrided getMaxCustomDamage set 0 here
        super(null, 0);
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);//use metadata to represent cell number
        this.setRegistryName(faitems.MODID, name);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("faitems:cell", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation("faitems:dual_cell", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation("faitems:quad_cell", "inventory"));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;//damage represent cell amount,return 0 to make it render correctly
    }

    @SideOnly(Side.CLIENT)
    public void initColorHandler(ItemColors itemColor) {
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s.name", getRegistryName());
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return this.isDamaged(stack);
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
