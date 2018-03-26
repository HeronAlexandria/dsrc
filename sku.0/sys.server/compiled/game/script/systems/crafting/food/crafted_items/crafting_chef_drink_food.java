package script.systems.crafting.food.crafted_items;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;
import script.library.consumable;

public class crafting_chef_drink_food extends script.systems.crafting.food.crafted_items.crafting_chef_food
{
    public crafting_chef_drink_food()
    {
    }
    public void fillStomach(obj_id prototype, int filling) throws InterruptedException
    {
        int[] stomach = 
        {
            0,
            filling,
            0
        };
        setObjVar(prototype, consumable.VAR_CONSUMABLE_STOMACH_VALUES, stomach);
    }
}
