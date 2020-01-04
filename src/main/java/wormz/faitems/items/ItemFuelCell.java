package wormz.faitems.items;

import ic2.api.item.ICustomDamageItem;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.init.Localization;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.item.reactor.ItemReactorUranium;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wormz.faitems.IFuel;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class ItemFuelCell<T extends Enum<T> & IFuel> extends ItemCell implements ICustomDamageItem, IReactorComponent {
    public final Map<Integer, IFuel> fuels = new HashMap<>();
    /*
     * Metadata structure
     * |byte|byte|4bit|byte   |4bit       |
     * |00  |00  |0   |fuel id|cell number|
     */
    Function<ItemStack, Integer> Fuel_ID_GETTER = stack -> (stack.getMetadata() & 0x00000FF0) >> 4;
    Function<ItemStack, Integer> Cell_Num_GETTER = stack -> stack.getMetadata() & 0x0000000F;

    public ItemFuelCell(Class<T> enumm, String name) {
        super(name);
        for (IFuel fuel : enumm.getEnumConstants()) {
            fuels.put((int) fuel.getID(), fuel);
        }
        //fuel_type -> use different content texture
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 1);
    }

    @Nullable
    public IFuel getFuel(ItemStack stack) {
        return fuels.get(Fuel_ID_GETTER.apply(stack));
    }

    public String getFuelName(ItemStack stack) {
        return getFuel(stack).getName();
    }

    public String getFuelTranslationKey(ItemStack stack) {
        return getFuel(stack).getTranslationKey();
    }

    public int getFuelDurability(ItemStack stack) {
        return getFuel(stack).getDurability();
    }

    public int getFuelHeat(ItemStack stack) {
        return getFuel(stack).getHeat();
    }

    public int getFuelPulse(ItemStack stack) {
        return getFuel(stack).getPulse();
    }

    public int getFuelColor(ItemStack stack) {
        return getFuel(stack).getColor();
    }

    public ItemStack setFuelDurability(ItemStack stack, int durability) {
        StackUtil.getOrCreateNbtData(stack).setInteger(durability_key, durability);
        return stack;
    }

    //IC2 methods copy from ItemReactorUranium
    protected void checkHeatAcceptor(IReactor reactor, int x, int y, Queue<ItemStackCoord> heatAcceptors) {
        ItemStack stack = reactor.getItemAt(x, y);
        if (stack != null && stack.getItem() instanceof IReactorComponent && ((IReactorComponent) stack.getItem()).canStoreHeat(stack, reactor, x, y)) {
            heatAcceptors.add(new ItemStackCoord(stack, x, y));
        }

    }

    protected static int triangularNumber(int x) {
        return (x * x + x) / 2;
    }

    protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        return heat;
    }

    protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun) {
        ItemStack other = reactor.getItemAt(x, y);
        return other != null && other.getItem() instanceof IReactorComponent && ((IReactorComponent) other.getItem()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun) ? 1 : 0;
    }

    private static class ItemStackCoord {
        public final ItemStack stack;
        public final int x;
        public final int y;

        public ItemStackCoord(ItemStack stack, int x, int y) {
            this.stack = stack;
            this.x = x;
            this.y = y;
        }
    }

    //IC2 - Start - IReactorComponent
    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
        if (heatRun) {
            if (getCustomDamage(stack) < getMaxCustomDamage(stack)) {
                applyCustomDamage(stack, 1, null);

                int basePulses = 1 + Cell_Num_GETTER.apply(stack) / 2;
                int pulses;
                int heat;
                pulses = basePulses + checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) + checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) + checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) + checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
                heat = triangularNumber(pulses) * 4;
                heat = this.getFinalHeat(stack, reactor, x, y, heat);
                Queue<ItemStackCoord> heatAcceptors = new ArrayDeque();
                this.checkHeatAcceptor(reactor, x - 1, y, heatAcceptors);
                this.checkHeatAcceptor(reactor, x + 1, y, heatAcceptors);
                this.checkHeatAcceptor(reactor, x, y - 1, heatAcceptors);
                this.checkHeatAcceptor(reactor, x, y + 1, heatAcceptors);

                while (!heatAcceptors.isEmpty() && heat > 0) {
                    int dheat = heat / heatAcceptors.size();
                    heat -= dheat;
                    ItemStackCoord acceptor = heatAcceptors.remove();
                    IReactorComponent acceptorComp = (IReactorComponent) acceptor.stack.getItem();
                    dheat = acceptorComp.alterHeat(acceptor.stack, reactor, acceptor.x, acceptor.y, dheat);
                    heat += dheat;
                }

                if (heat > 0) {
                    reactor.addHeat(heat);
                }
            } else {
                //todo:handle fission recipe
            }
        }
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        return false;
    }

    @Override
    public boolean canStoreHeat(ItemStack itemStack, IReactor iReactor, int x, int y) {
        return false;
    }

    @Override
    public int getMaxHeat(ItemStack itemStack, IReactor iReactor, int x, int y) {
        return 0;
    }

    @Override
    public int getCurrentHeat(ItemStack itemStack, IReactor iReactor, int x, int y) {
        return 0;
    }

    @Override
    public int alterHeat(ItemStack itemStack, IReactor iReactor, int i, int i1, int i2) {
        return 0;
    }

    @Override
    public float influenceExplosion(ItemStack itemStack, IReactor iReactor) {
        return 2 * Cell_Num_GETTER.apply(itemStack);
    }

    @Override
    public boolean canBePlacedIn(ItemStack itemStack, IReactor iReactor) {
        return true;
    }

    //IC2 - ICustomDamageItem
    String reacted_key = "reacted";
    String durability_key = "durability";

    @Override
    public int getCustomDamage(ItemStack itemStack) {
        return itemStack.hasTagCompound() ? itemStack.getTagCompound().getInteger(reacted_key) : 0;
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return stack.hasTagCompound() ? stack.getTagCompound().getInteger(durability_key) : 0;
    }

    @Override
    public void setCustomDamage(ItemStack itemStack, int reacted) {
        StackUtil.getOrCreateNbtData(itemStack).setInteger(reacted_key, Math.max(reacted, 0));
    }

    @Override
    public boolean applyCustomDamage(ItemStack itemStack, int delta, EntityLivingBase entityLivingBase) {
        StackUtil.getOrCreateNbtData(itemStack).setInteger(reacted_key, Math.max(delta + getCustomDamage(itemStack), 0));
        return true;
    }
//IC2 - End

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        for (int id : fuels.keySet()) {
            ModelLoader.setCustomModelResourceLocation(this, id << 4 | 1, new ModelResourceLocation("faitems:cell", "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, id << 4 | 2, new ModelResourceLocation("faitems:dual_cell", "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, id << 4 | 4, new ModelResourceLocation("faitems:quad_cell", "inventory"));
        }
    }

    @SideOnly(Side.CLIENT)
    public void initColorHandler(ItemColors itemColor) {
        itemColor.registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 2) {
                return getFuelColor(stack);
            }
            return -1;
        }, this);
    }
//Vanilla Start - Item


    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getMaxCustomDamage(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return getCustomDamage(stack) / getMaxCustomDamage(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;//make it render correctly
    }

    @Override
    public int getDamage(ItemStack stack) {
        return getCustomDamage(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        if (getMaxCustomDamage(stack) > 0)
            tooltip.add(Localization.translate("ic2.reactoritem.durability") + " " + (this.getMaxCustomDamage(stack) - this.getCustomDamage(stack)) + "/" + this.getMaxCustomDamage(stack));
    }

    /**
     * Apply radiation effect to whom without protection
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving)) {
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
            }
        }

    }

    /**
     * display fuel name
     *
     * @param stack
     * @return cell name and fuel name
     */
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format(this.getTranslationKey() + ".name", I18n.format(getFuelTranslationKey(stack)));
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s:fuel_cell", this.getRegistryName().getNamespace());
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (IFuel fuel : fuels.values()) {
                int id = fuel.getID();
                ItemStack cell = new ItemStack(this, 1, id << 4 | 1);
                ItemStack dual_cell = new ItemStack(this, 1, id << 4 | 2);
                ItemStack quad_cell = new ItemStack(this, 1, id << 4 | 4);
                items.add(setFuelDurability(cell, fuel.getDurability()));
                items.add(setFuelDurability(dual_cell, fuel.getDurability()));
                items.add(setFuelDurability(quad_cell, fuel.getDurability()));
            }
        }
    }
}
