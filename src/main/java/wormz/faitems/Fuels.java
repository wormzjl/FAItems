package wormz.faitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class Fuels {
    //location color
    private static Map<String, Integer> colors = new HashMap();

    @SideOnly(Side.CLIENT)
    public static int getFuelColor(String fuel_name, String path) {
        String location = String.format("nuclearcraft:items/%s_%s", path, fuel_name);
        if (!colors.containsKey(location)) {
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
                        colors.put(location, pixel_color);
                        return pixel_color;
                    }
                }
            }
            //pixel/texture not exist or invisible
            faitems.logger.warn(String.format("Failed to init %s's fuel color", fuel_name));
            colors.put(location, -1);
            return -1;
        } else {
            return colors.get(location);
        }
    }

    public enum ThoriumFuelType implements IFuel {
        TBU("tbu", 0, 20000,1,4,1),
        TBU_OXIDE("tbu_oxide", 1, 20000,2,8,2);
        public String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        ThoriumFuelType(String name, int id, int durability,int power,int heatGen,int pulse) {
            this.name = name;
            this.id = id;
            this.durability = durability;
            this.heatGen = heatGen;
            this.power = power;
            this.pulse = pulse;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum UraniumFuelType implements IFuel {
        LEU_233("leu_233", 0),
        LEU_233_OXIDE("leu_233_oxide", 1),
        HEU_233("heu_233", 2),
        HEU_233_OXIDE("heu_233_oxide", 3),
        LEU_235("leu_235", 4),
        LEU_235_OXIDE("leu_235_oxide", 5),
        HEU_235("heu_235", 6),
        HEU_235_OXIDE("heu_235_oxide", 7);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        UraniumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }

    }

    public enum NeptuniumFuelType implements IFuel {
        LEN_236("len_236", 0),
        LEN_236_OXIDE("len_236_oxide", 1),
        HEN_236("hen_236", 2),
        HEN_236_OXIDE("hen_236_oxide", 3);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        NeptuniumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum PlutoniumFuelType implements IFuel {
        LEP_239("lep_239", 0),
        LEP_239_OXIDE("lep_239_oxide", 1),
        HEP_239("hep_239", 2),
        HEP_239_OXIDE("hep_239_oxide", 3),
        LEP_241("lep_241", 4),
        LEP_241_OXIDE("lep_241_oxide", 5),
        HEP_241("hep_241", 6),
        HEP_241_OXIDE("hep_241_oxide", 7);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        PlutoniumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum MixedFuelType implements IFuel {
        MOX_239("mox_239", 0),
        MOX_241("mox_241", 1);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "") + "_oxide";
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        MixedFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum AmericiumFuelType implements IFuel {
        LEA_242("lea_242", 0),
        LEA_242_OXIDE("lea_242_oxide", 1),
        HEA_242("hea_242", 2),
        HEA_242_OXIDE("hea_242_oxide", 3);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        AmericiumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum CuriumFuelType implements IFuel {
        LEC_243("lec_243", 0),
        LEC_243_OXIDE("lec_243_oxide", 1),
        HEC_243("hec_243", 2),
        HEC_243_OXIDE("hec_243_oxide", 3),
        LEC_245("lec_245", 4),
        LEC_245_OXIDE("lec_245_oxide", 5),
        HEC_245("hec_245", 6),
        HEC_245_OXIDE("hec_245_oxide", 7),
        LEC_247("lec_247", 8),
        LEC_247_OXIDE("lec_247_oxide", 9),
        HEC_247("hec_247", 10),
        HEC_247_OXIDE("hec_247_oxide", 11);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        CuriumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum BerkeliumFuelType implements IFuel {
        LEB_248("leb_248", 0),
        LEB_248_OXIDE("leb_248_oxide", 1),
        HEB_248("heb_248", 2),
        HEB_248_OXIDE("heb_248_oxide", 3);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        BerkeliumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }

    public enum CaliforniumFuelType implements IFuel {
        LEC_249("lec_249", 0),
        LEC_249_OXIDE("lec_249_oxide", 1),
        HEC_249("hec_249", 2),
        HEC_249_OXIDE("hec_249_oxide", 3),
        LEC_251("lec_251", 4),
        LEC_251_OXIDE("lec_251_oxide", 5),
        HEC_251("hec_251", 6),
        HEC_251_OXIDE("hec_251_oxide", 7);

        String path = "fuel_" + this.getClass().getSimpleName().toLowerCase().replaceFirst("fueltype", "");
        private String name;
        private int id, pulse, heatGen, durability, color, power;

        CaliforniumFuelType(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte getID() {
            return (byte) id;
        }

        @Override
        public int getPulse() {
            return pulse;
        }

        @Override
        public int getPower() {
            return power;
        }

        @Override
        public int getHeat() {
            return heatGen;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public int getColor() {
            return getFuelColor(name, path);
        }

        @Override
        public String getTranslationKey() {
            return "item.nuclearcraft." + path + "." + name + ".name";
        }
    }
}
