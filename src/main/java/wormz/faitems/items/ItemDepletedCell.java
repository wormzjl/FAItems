package wormz.faitems.items;

import ic2.api.reactor.IReactor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import wormz.faitems.IFuel;

import java.util.List;

public class ItemDepletedCell<T extends Enum<T> & IFuel> extends ItemFuelCell {

    public ItemDepletedCell(Class<T> enumm, String name) {
        super(enumm, name);
        //fuel_type -> use different content texture
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 2);
    }

    //IC2 - Start
    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        return false;
    }

    @Override
    public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
        return false;
    }
    //IC2 - End


    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List tooltip, ITooltipFlag advanced) {
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s:cell_depleted", this.getRegistryName().getNamespace());
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList items) {
        if (this.isInCreativeTab(tab)) {
            for (Object fuel : super.fuels.values()) {
                IFuel _fuel = (IFuel)fuel;
                int id = _fuel.getID();
                ItemStack cell = new ItemStack(this, 1, id << 4 | 1);
                ItemStack dual_cell = new ItemStack(this, 1, id << 4 | 2);
                ItemStack quad_cell = new ItemStack(this, 1, id << 4 | 4);
                items.add(cell);
                items.add(dual_cell);
                items.add(quad_cell);
            }
        }
    }
}
