// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScriptActionPolarXZ.java

package net.eq2online.module.polar;

//import ays;
//import ke;
import net.eq2online.macros.scripting.ScriptCore;
import net.eq2online.macros.scripting.VariableExpander;
import net.eq2online.macros.scripting.api.*;
import net.minecraft.client.Minecraft;

public class ScriptActionPolarXYZ
    implements IScriptAction
{

    public ScriptActionPolarXYZ()
    {
    }

    public String toString()
    {
        return "polarxyz";
    }

    public String Execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String params[])
    {
        Minecraft mc = Minecraft.x();
        if(params.length > 3 && mc != null && mc.g != null)
        {
            float xPos = (float)TryParseInt(ParseVars(provider, macro, params[0]), 0) + 0.5F;
            float yPos = (float)TryParseInt(ParseVars(provider, macro, params[1]), 0) + 0.5F;
            float zPos = (float)TryParseInt(ParseVars(provider, macro, params[2]), 0) + 0.5F;
            double deltaX = (double)mc.g.t - xPos;
            double deltaY = (double)mc.g.u - yPos;
            double deltaZ = (double)mc.g.v - zPos;
            // I don't use the ke.a helper  because I couldn't work out how to import ke ;)
            //double distance = ke.a(deltaX * deltaX + deltaZ * deltaZ);
            double distanceXZsq = deltaX * deltaX + deltaZ * deltaZ;
            double distanceXZ = Math.sqrt(distanceXZsq);
            double distanceXYZ = Math.sqrt(deltaY * deltaY + distanceXZsq);
            int yaw;
            for(yaw = (int)((Math.atan2(deltaZ, deltaX) * 180D) / 3.1415926535897931D - 90D); yaw < 0; yaw += 360);
            int pitch;
            for(pitch = (int)((Math.atan2(deltaY, distanceXZ) * 180D) / 3.1415926535897931D); pitch < 0; pitch += 360);
            //provider.ActionAddChatMessage("params: "+params.length);
            //provider.ActionAddChatMessage("distanceXZ: "+distanceXZ);
            SetVariable(provider, macro, params[3], yaw);
            if(params.length > 4) {

                SetVariable(provider, macro, params[4], pitch);
                //provider.ActionAddChatMessage("returning pitch: "+pitch+" as:"+params[4]);
            }
            if(params.length > 5) {
                SetVariable(provider, macro, params[5], (int)distanceXYZ);
                //provider.ActionAddChatMessage("returning distanceXYZ: "+distanceXYZ+" as:"+params[5]);
            }
        }
        return null;
    }

    public boolean IsStackPushOperator()
    {
        return false;
    }

    public boolean IsStackPopOperator()
    {
        return false;
    }

    public boolean CanBePoppedBy(IScriptAction action)
    {
        return false;
    }

    public boolean ExecuteStackPush(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String s, String as[])
    {
        return false;
    }

    public boolean ExecuteStackPop(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String s, String as[], IMacroAction imacroaction)
    {
        return false;
    }

    public boolean IsConditionalOperator()
    {
        return false;
    }

    public boolean IsConditionalElseOperator(IScriptAction action)
    {
        return false;
    }

    public boolean MatchesConditionalOperator(IScriptAction action)
    {
        return false;
    }

    public boolean ExecuteConditional(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String s, String as[])
    {
        return false;
    }

    public void ExecuteConditionalElse(IScriptActionProvider iscriptactionprovider, IMacro imacro, IMacroAction imacroaction, String s, String as[], IMacroActionStackEntry imacroactionstackentry)
    {
    }

    public boolean CanExecuteNow(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String s, String as[])
    {
        return true;
    }

    public int Tick(IScriptActionProvider provider)
    {
        return 0;
    }

    public void OnInit()
    {
        ScriptCore.RegisterScriptAction(this);
    }

    public boolean IsClocked()
    {
        return true;
    }

    public boolean IsPermissable()
    {
        return false;
    }

    public String GetPermissionGroup()
    {
        return null;
    }

    public void RegisterPermissions(String s, String s1)
    {
    }

    public boolean CheckExecutePermission()
    {
        return true;
    }

    public boolean CheckPermission(String actionName, String permission)
    {
        return true;
    }

    public int GetAPIVersionSupported()
    {
        return 8;
    }

    public void OnStopped(IScriptActionProvider iscriptactionprovider, IMacro imacro, IMacroAction imacroaction)
    {
    }

    public void SetVariable(IScriptActionProvider provider, IMacro macro, String variableName, int variableValue)
    {
        provider.SetMacroVariable(macro, variableName, String.valueOf(variableValue), variableValue, variableValue != 0);
    }

    protected static String ParseVars(IScriptActionProvider provider, IMacro macro, String text)
    {
        return (new VariableExpander(provider, macro, text, false)).toString();
    }

    protected static int TryParseInt(String value, int defaultValue)
    {
        if(value == null)
            return defaultValue;
        try {
            return Integer.parseInt(value.trim().replaceAll(",", ""));
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }
}