package speiger.src.spmodapi.common.config;

import java.util.UUID;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import speiger.src.spmodapi.common.config.ModObjects.APIUtils;
import speiger.src.spmodapi.common.creativeTabs.TabCrafting;
import speiger.src.spmodapi.common.creativeTabs.TabHemp;
import speiger.src.spmodapi.common.creativeTabs.TabHempDeko;
import speiger.src.spmodapi.common.fluids.hemp.FluidHempResin;

public class APIUtilsConfig
{
	private static APIUtils utils;
	
	public static void register()
	{
		APIUtils.jumpBoost = new RangedAttribute("generic.jump", 1.0D, 0.0D, 1024.0D).func_111117_a("generic.jump").setShouldWatch(true);
		APIUtils.hempArmor = EnumHelper.addArmorMaterial("Hemp Armor", 0, new int[] { 0, 0, 0, 0 }, 0);
		utils.hempResin = new FluidHempResin();
		utils.tabHemp = new TabHemp();
		utils.tabHempDeko = new TabHempDeko();
		utils.tabCrafing = new TabCrafting();
		OreDictionary.registerOre("bone", Item.bone);
		
	}
}
