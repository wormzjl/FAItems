package wormz.faitems.items;

import ic2.api.reactor.IReactor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wormz.faitems.IFuel;

public class ItemBreederCell<T extends Enum<T> & IFuel> extends ItemFuelCell {

    public ItemBreederCell(Class<T> enumm, String name) {
        super(enumm, name);
        //fuel_type -> use different content texture
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 1);
    }

    //IC2 - Start
    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (heatrun) {
            //higher heat higher speed
            int damage = 4 * reactor.getHeat() / reactor.getMaxHeat() + 1;

            applyCustomDamage(stack, damage, null);

            if (getCustomDamage(stack) == getMaxCustomDamage(stack)) {
                //todo:handle fission recipe
            }
        }
        return true;
    }
    //IC2 - End

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1 - getCustomDamage(stack) / getMaxCustomDamage(stack);
    }

    @Override
    public String getTranslationKey() {
        return String.format("item.%s:cell_breeder", this.getRegistryName().getNamespace());
    }
}
