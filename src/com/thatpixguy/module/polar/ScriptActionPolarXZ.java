package com.thatpixguy.module.polar;

import net.eq2online.macros.core.Log;
import net.eq2online.macros.scripting.ScriptCore;
import net.eq2online.macros.scripting.VariableExpander;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IMacroActionStackEntry;
import net.eq2online.macros.scripting.ScriptAction;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.src.IStatType;
import net.minecraft.src.MathHelper;

@APIVersion(9)
public class ScriptActionPolarXZ extends ScriptAction
{
    

    public ScriptActionPolarXZ()
    {
        super("polarxz");
    }

    public String Execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params)
    {

        Minecraft mc = Minecraft.getMinecraft();

        if (params.length > 2 && mc != null && mc.thePlayer != null)
        {
            float posX = (float)TryParseInt(ParseVars(provider, macro, params[0]), 0) + 0.5F;
            float posZ = (float)TryParseInt(ParseVars(provider, macro, params[1]), 0) + 0.5F;
            double deltaX = (double)posX - mc.thePlayer.posX;
            double deltaZ = (double)posZ - mc.thePlayer.posZ;
            double normXZ = (double)MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
            
            int yaw;
            for (yaw = (int)(Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI - 90.0D); yaw < 0; yaw += 360);

            this.SetVariable(provider, macro, params[2], yaw);

            if (params.length > 3) this.SetVariable(provider, macro, params[3], (int)normXZ);
        }

        return null;
    }

    public void OnInit() {
        ScriptAction.RegisterAction(this);
    }

}
