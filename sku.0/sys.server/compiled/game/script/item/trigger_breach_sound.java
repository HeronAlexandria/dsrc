package script.item;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class trigger_breach_sound extends script.base_script
{
    public trigger_breach_sound()
    {
    }
    public static final string_id SID_TURN_ON = new string_id("spam", "turn_on_sound_object");
    public static final string_id SID_TURN_OFF = new string_id("spam", "turn_off_sound_object");
    public static final String OBJVAR_SOUND_FILE = "soundFile";
    public static final String OBJVAR_IS_TURNED_ON = "itemIsOn";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!utils.isInHouseCellSpace(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasTriggerVolume(self, "itemBreach"))
        {
            createTriggerVolume("itemBreach", 5.f, true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id whoTriggeredMe) throws InterruptedException
    {
        if (!hasObjVar(self, OBJVAR_IS_TURNED_ON))
        {
            return SCRIPT_CONTINUE;
        }
        if (getIntObjVar(self, OBJVAR_IS_TURNED_ON) == 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("itemBreach"))
        {
            if (!isPlayer(whoTriggeredMe))
            {
                return SCRIPT_CONTINUE;
            }
            if (!hasObjVar(self, OBJVAR_SOUND_FILE))
            {
                return SCRIPT_CONTINUE;
            }
            String soundFile = getStringObjVar(self, OBJVAR_SOUND_FILE);
            playClientEffectObj(whoTriggeredMe, soundFile, self, "", new transform(), "");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!utils.isInHouseCellSpace(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, OBJVAR_IS_TURNED_ON) || getIntObjVar(self, OBJVAR_IS_TURNED_ON) == 0)
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, SID_TURN_ON);
        }
        else 
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, SID_TURN_OFF);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        sendDirtyObjectMenuNotification(self);
        if (!utils.isInHouseCellSpace(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (!hasObjVar(self, OBJVAR_IS_TURNED_ON) || getIntObjVar(self, OBJVAR_IS_TURNED_ON) == 0)
            {
                toggleSound(self, true);
            }
            else 
            {
                toggleSound(self, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public boolean toggleSound(obj_id item, boolean turnOn) throws InterruptedException
    {
        if (turnOn)
        {
            setObjVar(item, OBJVAR_IS_TURNED_ON, true);
            if (!hasTriggerVolume(item, "itemBreach"))
            {
                createTriggerVolume("itemBreach", 5.f, true);
            }
        }
        else 
        {
            setObjVar(item, OBJVAR_IS_TURNED_ON, false);
            if (hasTriggerVolume(item, "itemBreach"))
            {
                removeTriggerVolume("itemBreach");
            }
        }
        return true;
    }
}
