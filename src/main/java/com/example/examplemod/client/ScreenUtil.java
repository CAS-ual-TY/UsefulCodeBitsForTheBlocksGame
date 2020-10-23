package com.example.examplemod.client;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;

/**
 * 
 * @author CAS_ual_TY
 *
 */
public class ScreenUtil
{
    /**
     * Draw an outline rectangle at the given coordinates, with the given line width in a given RGBA (0F up to 1F) color.
     * The line width goes towards the inside of the rect.
     * @param x Rect x coord
     * @param y Rect y coord
     * @param w Rect width
     * @param h Rect height
     * @param lineWidth The line width
     * @param r Red
     * @param g Green
     * @param b Blue
     * @param a Alpha (0F = Transparent)
     * 
     * @see #drawRect(MatrixStack, float, float, float, float, float, float, float, float)
     */
    public static void drawLineRect(MatrixStack ms, float x, float y, float w, float h, float lineWidth, float r, float g, float b, float a)
    {
        ScreenUtil.drawRect(ms, x, y, w, lineWidth, r, g, b, a); //top
        ScreenUtil.drawRect(ms, x, y + h - lineWidth, w, lineWidth, r, g, b, a); //bot
        ScreenUtil.drawRect(ms, x, y, lineWidth, h, r, g, b, a); //left
        ScreenUtil.drawRect(ms, x + w - lineWidth, y, lineWidth, h, r, g, b, a); //right
    }
    
    /**
     * Draw a rectangle at the given coordinates, with the given width in a given RGBA (0F up to 1F) color
     * @param ms
     * @param x Rect x coord
     * @param y Rect y coord
     * @param w Rect width
     * @param h Rect height
     * @param r Red
     * @param g Green
     * @param b Blue
     * @param a Alpha (0F = Transparent)
     */
    public static void drawRect(MatrixStack ms, float x, float y, float w, float h, float r, float g, float b, float a)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        GlStateManager.enableBlend();
        GlStateManager.disableTexture();
        
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        
        RenderSystem.color4f(r, g, b, a);
        
        Matrix4f m = ms.getLast().getMatrix();
        
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(m, x, y + h, 0F).endVertex(); // BL
        bufferbuilder.pos(m, x + w, y + h, 0F).endVertex(); // BR
        bufferbuilder.pos(m, x + w, y, 0F).endVertex(); // TR
        bufferbuilder.pos(m, x, y, 0F).endVertex(); // TL
        tessellator.draw();
        
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }
}
