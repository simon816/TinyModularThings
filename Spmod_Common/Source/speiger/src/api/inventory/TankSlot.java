package speiger.src.api.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.FluidTank;
import speiger.src.api.blocks.BlockStack;
import speiger.src.api.items.InfoStack;
import speiger.src.api.language.LanguageRegister;
import speiger.src.spmodapi.common.util.proxy.LangProxy;
import speiger.src.tinymodularthings.TinyModularThings;

public class TankSlot
{
	FluidTank tank;
	int x;
	int y;
	
	public TankSlot(FluidTank par0, int xCoord, int yCoord)
	{
		tank = par0;
		x = xCoord;
		y = yCoord;
	}
	
	public FluidTank getTank()
	{
		return tank;
	}
	
	public int getXCoord()
	{
		return x;
	}
	
	public int getYCoord()
	{
		return y;
	}
	
	public List getTankInfo()
	{
		ArrayList<String> texts = new ArrayList<String>();
		if (tank != null)
		{
			texts.add("Fluid Tank");
			texts.add(LangProxy.getStored(TinyModularThings.instance) + ": " + getFluidName());
			texts.add(LangProxy.getAmount(TinyModularThings.instance) + ": " + tank.getFluidAmount() + "mB / " + tank.getCapacity() + "mB");
		}
		return texts;
	}
	
	public String getFluidName()
	{
		if (tank != null)
		{
			if (tank.getFluid() != null)
			{
				if (tank.getFluid().getFluid().canBePlacedInWorld())
				{
					return new BlockStack(tank.getFluid().getFluid().getBlockID()).getBlockDisplayName();
				}
				else
				{
					if (tank.getFluid().getFluid().getLocalizedName().startsWith("tile."))
					{
						return LanguageRegister.getLanguageName(new InfoStack(), "tank.stored.unknowen", TinyModularThings.instance);
					}
					else
					{
						return tank.getFluid().getFluid().getLocalizedName();
					}
				}
			}
		}
		return LangProxy.getStoredNothing(TinyModularThings.instance);
	}
}
