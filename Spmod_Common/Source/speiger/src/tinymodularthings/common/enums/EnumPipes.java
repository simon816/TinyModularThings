package speiger.src.tinymodularthings.common.enums;

import speiger.src.tinymodularthings.common.utils.PipeInformation;

public enum EnumPipes
{
	Small(6, 60, 4, "pipe.basic.small", "basic/smallPipe"), Normal(12, 125, 8, "pipe.basic.normal", "basic/normalPipe"), Medium(25, 250, 16, "pipe.basic.medium", "basic/mediumPipe"), Big(50, 500, 24, "pipe.basic.big", "basic/bigPipe"), Bigger(100, 1000, 32, "pipe.basic.bigger", "basic/biggerPipe"), Large(200, 2000, 48, "pipe.basic.large", "basic/largePipe"), Huge(500, 5000, 64, "pipe.basic.huge", "basic/hugePipe");
	
	int par1;
	int par2;
	int par3;
	String par4;
	String par5;
	
	private EnumPipes(int mj, int fluid, int items, String name, String texture)
	{
		par1 = mj;
		par2 = fluid;
		par3 = items;
		par4 = name;
		par5 = texture;
	}
	
	public PipeInformation getPipeInformation()
	{
		return new PipeInformation(par1, par2, par3, par4, par5);
	}
	
}
