package speiger.src.spmodapi.common.items.hemp;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import speiger.src.api.items.DisplayItem;
import speiger.src.api.language.LanguageRegister;
import speiger.src.api.util.SpmodMod;
import speiger.src.api.util.SpmodModRegistry;
import speiger.src.spmodapi.SpmodAPI;
import speiger.src.spmodapi.common.config.ModObjects.APIUtils;
import speiger.src.spmodapi.common.items.SpmodItem;
import speiger.src.spmodapi.common.lib.SpmodAPILib;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHemp extends SpmodItem
{
	
	public ItemHemp(int par1)
	{
		super(par1);
		this.setCreativeTab(APIUtils.tabHemp);
	}
	
	@Override
	public void registerItems(int id, SpmodMod par0)
	{
		if (!SpmodModRegistry.areModsEqual(par0, SpmodAPI.instance))
		{
			return;
		}
		
		LanguageRegister.getLanguageName(new DisplayItem(id), "hemp", par0);
	}
	
	@Override
	public String getDisplayName(ItemStack par1, SpmodMod par0)
	{
		return LanguageRegister.getLanguageName(new DisplayItem(par1.getItem()), "hemp", par0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(SpmodAPILib.ModID.toLowerCase() + ":hemp/hempDrop");
	}
	
}
