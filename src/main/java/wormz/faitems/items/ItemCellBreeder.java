package wormz.faitems.items;

import ic2.api.reactor.IReactor;
import ic2.core.IC2Potion;
import ic2.core.init.Localization;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.util.StackUtil;
import nc.enumm.IFissionStats;
import nc.enumm.IItemMeta;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.Color;
import wormz.faitems.faitems;

import java.util.HashMap;
import java.util.Map;

public class ItemCellBreeder<T extends Enum<T> & IStringSerializable & IItemMeta & IFissionStats> extends ItemCell {
    private final Class<T> enumm;
    public final Enum[] values;
    //Fuel Meta | Color
    public final Map<Integer, Integer> colors;
    public static final String duration_key = "duration";

    public ItemCellBreeder(Class<T> enumm, String name) {
        super(name);
        this.enumm = enumm;
        this.values = enumm.getEnumConstants();
        this.colors = new HashMap<>();
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (heatrun) {
            //higher heat higher speed
            int myLevel = getCustomDamage(stack) - 4 * reactor.getHeat() / reactor.getMaxHeat() - 1;
            if (myLevel <= 0) {
                reactor.setItemAt(youX, youY, getDepletedStack(stack, reactor));
            } else {
                setCustomDamage(stack, myLevel);
            }
        }
        return true;
    }

    private ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        ItemCellDepleted itemCellDepleted = (ItemCellDepleted) Item.getByNameOrId(String.format("%s:depleted_%s", getRegistryName().getNamespace(), getRegistryName().getPath()));
        return new ItemStack(itemCellDepleted, 1, stack.getMetadata());
    }

    public void setDuration(ItemStack stack, int duration) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        nbt.setInteger(duration_key, duration);
    }

    private int getFuelIndex(ItemStack stack) {
        return (int) Math.floor(stack.getMetadata() / 3);
    }

    public double getFuelBasePower(ItemStack stack) {
        IFissionStats stats = (IFissionStats) values[getFuelIndex(stack)];
        return stats.getBasePower();
    }

    public double getFuelBaseTime(ItemStack stack) {
        IFissionStats stats = (IFissionStats) values[getFuelIndex(stack)];
        return stats.getBaseTime();
    }

    public double getFuelBaseHeat(ItemStack stack) {
        IFissionStats stats = (IFissionStats) values[getFuelIndex(stack)];
        return stats.getBaseHeat();
    }

    public int getFuelMeta(ItemStack stack) {
        return ((IItemMeta) values[getFuelIndex(stack)]).getID();
    }

    public String getFuelName(ItemStack stack) {
        return ((IStringSerializable) values[getFuelIndex(stack)]).getName();
    }

    public String getFuelTranslationKey(ItemStack stack) {
        return "item.nuclearcraft." + this.getRegistryName().getPath() + "." + this.getFuelName(stack) + ".name";
    }

    public int getCellNumber(ItemStack stack) {
        return stack.getMetadata() - (int) Math.floor(stack.getMetadata() / 3);
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s:cell_breeder.name", this.getRegistryName().getNamespace());
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return stack.hasTagCompound() ? stack.getTagCompound().getInteger(duration_key) : 0;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return String.format(Localization.translate(this.getTranslationKey(stack)), Localization.translate(getFuelTranslationKey(stack)));
    }

    @SideOnly(Side.CLIENT)
    public void initFuelColor() {
        for (int i = 0; i < values.length; i++) {
            String fuel_name = ((IStringSerializable) values[i]).getName();
            String location = String.format("nuclearcraft:items/%s_%s", this.getRegistryName().getPath(), fuel_name);
            TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location);
            //check if texture exist
            if (texture.getIconName() != "missingno") {
                //texture size
                int width = texture.getIconWidth();
                int height = texture.getIconHeight();

                int[] data = texture.getFrameTextureData(0)[0];
                //pixel position
                int y = height / 4;
                int x = width / 2;
                int index = y * width + x;
                //check if it has pixels
                if (data.length > index) {
                    //center pixel
                    int pixel_color = data[y * width + x];
                    //check if it is visable
                    if ((pixel_color >> 24 & 255) / 255f > 0.1f) {
                        //Use ARGB format
                        Color color = new Color(pixel_color >> 16, pixel_color >> 8, pixel_color);

                        //convert to hsb to change its brightness
                        float[] hsb = color.toHSB(null);
                        hsb[2] = (float) (hsb[2] * 1.8);//improve brightness

                        //back to rgb
                        color.fromHSB(hsb[0], hsb[1], hsb[2]);
                        int color_int = (color.getAlphaByte() << 24) & 0xff000000 |
                                (color.getRedByte() << 16) & 0x00ff0000 |
                                (color.getGreenByte() << 8) & 0x0000ff00 |
                                (color.getBlueByte() << 0) & 0x000000ff;
                        colors.put(((IItemMeta) values[i]).getID(), color_int);
                        continue;
                    }
                }
            }
            //pixel/texture not exist or invisible
            faitems.logger.warn(String.format("Failed to init %s's fuel color", fuel_name));
        }
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
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initColorHandler(ItemColors itemColor) {
        itemColor.registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 2) {
                return colors.getOrDefault(getFuelMeta(stack), -1);
            }
            return -1;
        }, this);
        initFuelColor();
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving)) {
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
            }
        }

    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            int k = 0;
            for (int i = 0; i < this.values.length; i++) {
                ItemStack cell = new ItemStack(this, 1, k);
                ItemStack dual_cell = new ItemStack(this, 1, k + 1);
                ItemStack quad_cell = new ItemStack(this, 1, k + 2);
                int fuel_basetime = (int) this.getFuelBaseTime(cell);
                this.setDuration(cell, fuel_basetime);
                this.setDuration(dual_cell, fuel_basetime);
                this.setDuration(quad_cell, fuel_basetime);
                this.setCustomDamage(cell, fuel_basetime);
                this.setCustomDamage(dual_cell, fuel_basetime);
                this.setCustomDamage(quad_cell, fuel_basetime);
                items.add(cell);
                items.add(dual_cell);
                items.add(quad_cell);
                k += 3;
            }
        }
    }
}
