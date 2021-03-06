package speiger.src.tinymodularthings.common.plugins.BC;

import speiger.src.tinymodularthings.common.plugins.BC.actions.ActionOneSlotChange;
import speiger.src.tinymodularthings.common.plugins.BC.actions.GateChangeToSlot;
import speiger.src.tinymodularthings.common.plugins.BC.triggers.TriggerFuel;
import speiger.src.tinymodularthings.common.plugins.BC.triggers.TriggerHasWork;
import buildcraft.api.gates.IAction;
import buildcraft.api.gates.ITrigger;

public class BCVariables
{
	// Triggers
	public static ITrigger hasWork = new TriggerHasWork(true);
	public static ITrigger noWork = new TriggerHasWork(false);
	public static ITrigger needFuel = new TriggerFuel();
	
	// Actions
	public static IAction[] changeToSlot = new IAction[64];
	public static IAction[] changeOneTime = new IAction[2];
	public static IAction[] bcFiller = new IAction[2];
}
