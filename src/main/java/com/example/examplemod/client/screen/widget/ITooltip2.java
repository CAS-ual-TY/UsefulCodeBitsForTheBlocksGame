package com.example.examplemod.client.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;

/**
 * <p>This interface is used by some custom widgets to display their tooltips.
 * Allows you to pass a method from your Screen class to render the tooltip in.</p>
 * 
 * <p>Buttons already used their own {@link Button.ITooltip} interface for this,
 * but that one only works for {@link Button} objects, which is why this interface exists.</p>
 * 
 * @author CAS_ual_TY
 *
 */
public interface ITooltip2
{
    public static final ITooltip2 EMPTY_TOOLTIP = (widget, ms, mouseX, mouseY) ->
    {};
    
    void onTooltip(Widget widget, MatrixStack ms, int mouseX, int mouseY);
}