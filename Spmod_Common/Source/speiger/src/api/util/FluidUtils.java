package speiger.src.api.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidUtils
{
	
	public static int getTankForFluidFilter(ItemStack[] par1, FluidStack stack)
	{
		for (int i = 0; i < par1.length; i++)
		{
			ItemStack item = par1[i];
			if (item != null && FluidContainerRegistry.isFilledContainer(item))
			{
				FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);
				if (fluid.isFluidEqual(stack))
				{
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public static Fluid getMobEssens()
	{
		return FluidRegistry.getFluid("mobessence");
	}
	
	public static Fluid getLiquidExp()
	{
		return FluidRegistry.getFluid("xpjuice");
	}
	
	public static boolean MFRExp()
	{
		return getMobEssens() != null;
	}
	
	public static boolean OpenBlocksExp()
	{
		return getLiquidExp() != null;
	}
	
	/**
	 * My Filled Tank finder (internal tanks)
	 */
	public static FluidTank getFirstFilledTank(FluidTank[] tanks)
	{
		if (tanks == null || tanks.length == 0)
		{
			return null;
		}
		FluidTank possibleTank = null;
		
		for (int i = 0; i < tanks.length; i++)
		{
			FluidTank cuTank = tanks[i];
			if (cuTank != null && cuTank.getFluidAmount() > 0)
			{
				possibleTank = cuTank;
				break;
			}
		}
		
		return possibleTank;
		
	}
	
	/**
	 * My FluidTank finder for my Hopper so the hopper Automaticly try to fill
	 * tanks which contains Its a helper for my Hopper
	 */
	public static FluidTank getPossibleTankFromFluid(FluidTank[] tanks, FluidStack stack)
	{
		FluidTank tank = null;
		
		for (int i = 0; i < tanks.length; i++)
		{
			FluidTank cuTank = tanks[i];
			if (cuTank != null && cuTank.getFluid() != null && cuTank.getCapacity() > cuTank.getFluid().amount)
			{
				FluidStack stacks = cuTank.getFluid();
				if (stacks != null && stacks.isFluidEqual(stack))
				{
					tank = cuTank;
					break;
				}
			}
		}
		
		if (tank == null)
		{
			tank = getFirstEmptyTank(tanks);
		}
		
		return tank;
		
	}
	
	/**
	 * The Function to find the first empty tank.
	 */
	
	public static FluidTank getFirstEmptyTank(FluidTank[] tanks)
	{
		FluidTank tank = null;
		for (int i = 0; i < tanks.length; i++)
		{
			FluidTank cuTank = tanks[i];
			if (cuTank != null && cuTank.getFluid() == null)
			{
				tank = cuTank;
				break;
			}
		}
		return tank;
	}
	
	public static FluidTank getPossibleTankFromFluidAndFilter(FluidTank[] tanks, FluidStack stack, ItemStack[] items)
	{
		FluidTank tank = null;
		
		for (int i = 0; i < tanks.length; i++)
		{
			FluidTank cuTank = tanks[i];
			if (cuTank != null && cuTank.getFluid() != null && cuTank.getCapacity() > cuTank.getFluid().amount)
			{
				FluidStack stacks = cuTank.getFluid();
				if (((items[i] != null && FluidContainerRegistry.isFilledContainer(items[i]) && FluidContainerRegistry.getFluidForFilledItem(items[i]).isFluidEqual(stack)) || items[i] == null))
				{
					if (stacks != null && stacks.isFluidEqual(stack))
					{
						tank = cuTank;
						break;
					}
				}
			}
		}
		
		if (tank == null)
		{
			tank = getFirstValidTank(tanks, stack, items);
		}
		
		return tank;
	}
	
	public static FluidTank getFirstValidTank(FluidTank[] tanks, FluidStack input, ItemStack[] items)
	{
		for (int i = 0; i < tanks.length; i++)
		{
			FluidStack fluid = tanks[i].getFluid();
			ItemStack filter = items[i];
			if (fluid != null && fluid.getFluid() == null && (filter == null || (filter != null && FluidContainerRegistry.isFilledContainer(filter) && FluidContainerRegistry.getFluidForFilledItem(filter).isFluidEqual(input))))
			{
				return tanks[i];
			}
			
		}
		
		return null;
	}
}
