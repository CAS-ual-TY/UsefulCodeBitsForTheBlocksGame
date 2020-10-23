package com.example.examplemod.client;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;

/**
 * 
 * @author CAS_ual_TY
 *
 */
public class FontRendererUtil
{
    /**
     * Draws a list of text components (each representing a new line), split to a given max width at the given coordinates.
     * @param ms MatrixStack
     * @param fontRenderer FontRenderer
     * @param list A list of ITextComponent each representing a new line
     * @param x The x coord to draw at
     * @param y The y coord to draw it
     * @param maxWidth The max width, list entries which are bigger are split into more lines
     * @param color The color to draw with
     */
    public static void drawSplitString(MatrixStack ms, FontRenderer fontRenderer, List<ITextComponent> list, float x, float y, int maxWidth, int color)
    {
        for(ITextComponent t : list)
        {
            if(t.getUnformattedComponentText().isEmpty() && t.getSiblings().isEmpty())
            {
                y += fontRenderer.FONT_HEIGHT;
            }
            else
            {
                for(IReorderingProcessor p : fontRenderer.trimStringToWidth(t, maxWidth))
                {
                    fontRenderer.func_238407_a_(ms, p, x, y, color);
                    y += fontRenderer.FONT_HEIGHT;
                }
            }
        }
    }
}
