package script.theme_park.gating.rebel;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.factions;

public class exar_kun_block1 extends script.base_script
{
    public exar_kun_block1()
    {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isIdValid(item) || !isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!factions.isRebel(item))
        {
            String fromCell = getCellName(getLocation(item).cell);
            if (fromCell == null || !fromCell.equals("r4"))
            {
                string_id warning = new string_id("theme_park_rebel/warning", "foyer1");
                sendSystemMessage(item, warning);
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
