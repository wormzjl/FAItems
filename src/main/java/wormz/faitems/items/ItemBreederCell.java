package wormz.faitems.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wormz.faitems.IFuel;

import java.util.ArrayDeque;
import java.util.Queue;

public class ItemBreederCell<T extends Enum<T> & IFuel> extends ItemFuelCell {
    int heat;
    public ItemBreederCell(Class<T> enumm, String name) {
        super(enumm, name);
        //fuel_type -> use different content texture
        this.addPropertyOverride(new ResourceLocation("fuel_type"), (stack, worldIn, entityIn) -> 1);
    }

    //IC2 - Start
    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
        if(heatRun && heat > 0){
            //handle heat transfer
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
                heat = 0;
            }
        }
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            //higher heat higher speed
            int damage = 4 * reactor.getHeat() / reactor.getMaxHeat() + 1;

            applyCustomDamage(stack, damage, null);

            if (getCustomDamage(stack) >= getMaxCustomDamage(stack)) {
                //todo:handle fission recipe
            }
        }else{
            //fission react produce heat
            heat += getFuelHeat(stack);
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
