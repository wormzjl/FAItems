package wormz.faitems;

public interface IFuel {
    String getName();

    /**
     * Fuel id - unique to each isotope or different oxide
     * different element will use different main item id so the metadata will not conflict
     * ex:uranium-233 id -> 0
     *    uranium-238 id -> 1
     *    uranium-233_oxide id -> 2
     * @return fuel id
     */
    byte getID();

    /**
     * Pulse - neutron pulse per tick
     * @return pulse value
     */
    int getPulse();

    /**
     * power in EU
     * @return how many EU it produce every react
     */
    int getPower();
    /**
     *  Return the heat the fuel generate each nuclear reaction
     *  Caution: the fuel may react multiple times every tick
     */
    int getHeat();
    /**
     *  Return the durability of the fuel
     *  decrease 1 every nuclear reaction
     *  Caution: the fuel may react multiple times every tick
     */
    int getDurability();
    /**
     *  Return the color of the fuel
     *  use for icon display
     *  In ARGB Format - Alpha channel has no effect
     */
    int getColor();
    /**
     *  Return the key of the fuel name
     */
    String getTranslationKey();
}
