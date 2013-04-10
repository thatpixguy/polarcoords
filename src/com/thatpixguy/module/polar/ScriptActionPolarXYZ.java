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
public class ScriptActionPolarXYZ extends ScriptAction
{
    

    public ScriptActionPolarXYZ()
    {
        super("polarxyz");
    }

    public String Execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params)
    {

        Minecraft mc = Minecraft.getMinecraft();

        if (params.length > 3 && mc != null && mc.thePlayer != null)
        {
            float posX = (float)TryParseInt(ParseVars(provider, macro, params[0]), 0) + 0.5F;
            float posY = (float)TryParseInt(ParseVars(provider, macro, params[1]), 0) + 0.5F;
            float posZ = (float)TryParseInt(ParseVars(provider, macro, params[2]), 0) + 0.5F;
            double deltaX = (double)posX - mc.thePlayer.posX;
            double deltaY = (double)posY - mc.thePlayer.posY;
            double deltaZ = (double)posZ - mc.thePlayer.posZ;
            double normXZsquared = deltaX * deltaX + deltaZ * deltaZ;
            double normXZ = MathHelper.sqrt_double(normXZsquared);
            double normXYZ = MathHelper.sqrt_double(deltaY * deltaY + normXZsquared);

            int yaw;
            for (yaw = (int)(Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI - 90.0D); yaw < 0; yaw += 360);

            int pitch;
            for (pitch = (int)(Math.atan2(deltaY, normXZ) * (-180.0D) / Math.PI); pitch < 0; pitch += 360);

            this.SetVariable(provider, macro, params[3], yaw);

            if (params.length > 4) this.SetVariable(provider, macro, params[4], pitch);
            if (params.length > 5) this.SetVariable(provider, macro, params[5], (int)normXYZ);
        }

        return null;
    }

    public void OnInit() {
        ScriptAction.RegisterAction(this);
    }

}
