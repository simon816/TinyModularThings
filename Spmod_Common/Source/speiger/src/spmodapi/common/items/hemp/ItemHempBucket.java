package speiger.src.spmodapi.common.items.hemp;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import speiger.src.api.items.DisplayItem;
import speiger.src.api.items.LanguageItem;
import speiger.src.api.language.LanguageRegister;
import speiger.src.api.util.SpmodMod;
import speiger.src.api.util.SpmodModRegistry;
import speiger.src.spmodapi.SpmodAPI;
import speiger.src.spmodapi.common.config.ModObjects.APIBlocks;
import speiger.src.spmodapi.common.config.ModObjects.APIUtils;
import speiger.src.spmodapi.common.lib.SpmodAPILib;

public class ItemHempBucket extends ItemBucket implements LanguageItem
{

	public ItemHempBucket(int par1)
	{
		super(par1, APIBlocks.fluidHempResin.blockID);
		this.setCreativeTab(APIUtils.tabHemp);
	}

	
	
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return getDisplayName(par1ItemStack, SpmodAPI.instance);
	}



	@Override
	public String getDisplayName(ItemStack par1, SpmodMod par0)
	{
		return LanguageRegister.getLanguageName(new DisplayItem(par1.getItem()), "bucket.liquid.hemp", par0);
	}

	@Override
	public void registerItems(int id, SpmodMod par0)
	{
		if(!SpmodModRegistry.areModsEqual(par0, SpmodAPI.instance))
		{
			return;
		}
		
		LanguageRegister.getLanguageName(new DisplayItem(id), "bucket.liquid.hemp", par0);
	}



	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1)
	{
		this.itemIcon = par1.registerIcon(SpmodAPILib.ModID.toLowerCase()+":hemp/hemp.resin.bucket");
	}
	
	
	
}
