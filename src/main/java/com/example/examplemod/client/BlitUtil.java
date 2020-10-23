package com.example.examplemod.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;

/**
 * This class contains static methods which are an extension of {@link AbstractGui} "blit" methods.
 * 
 * @author CAS_ual_TY
 * @see AbstractGui
 */
public class BlitUtil
{
    public static int blitOffset = 0;
    
    /**
     * Draws the entire texture on the given coordinates.
     * @see #advancedBlit(MatrixStack, int, int, int, int, int, int, int, int, int, int)
     */
    public static void fullBlit(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight)
    {
        BlitUtil.advancedBlit(ms, renderX, renderY, renderWidth, renderHeight, 0, 0, 1, 1, 1, 1);
    }
    
    /**
     * Draws the entire texture on the given coordinates, rotated by 90 degrees.
     * @see fullBlit(MatrixStack, int, int, int, int)
     */
    public static void fullBlit90Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight)
    {
        BlitUtil.advancedBlit90Degrees(ms, renderX, renderY, renderWidth, renderHeight, 0, 0, 1, 1, 1, 1);
    }
    
    /**
     * Draws the entire texture on the given coordinates, rotated by 180 degrees.
     * @see fullBlit(MatrixStack, int, int, int, int)
     */
    public static void fullBlit180Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight)
    {
        BlitUtil.advancedBlit180Degrees(ms, renderX, renderY, renderWidth, renderHeight, 0, 0, 1, 1, 1, 1);
    }
    
    /**
     * Draws the entire texture on the given coordinates, rotated by 270 degrees.
     * @see fullBlit(MatrixStack, int, int, int, int)
     */
    public static void fullBlit270Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight)
    {
        BlitUtil.advancedBlit270Degrees(ms, renderX, renderY, renderWidth, renderHeight, 0, 0, 1, 1, 1, 1);
    }
    
    /**
     * Advanced blit method.
     * 
     * Param 1-4: Where to and how big to draw on the screen.
     * Param 5-8: What part of the texture file to cut out and draw.
     * Param 9-10: How big the entire texture file is in general (pow2 only).
     * 
     * @param ms MatrixStack
     * @param renderX Where to draw on the screen
     * @param renderY Where to draw on the screen
     * @param renderWidth How big to draw on the screen
     * @param renderHeight How big to draw on the screen
     * @param textureX The left coordinate of the rect that is taken from the texture file and drawn
     * @param textureY The top coordinate of the rect that is taken from the texture file and drawn
     * @param textureWidth The width of the rect that is taken from the texture file and drawn
     * @param textureHeight The height of the rect that is taken from the texture file and drawn
     * @param totalTextureFileWidth The total texture file width
     * @param totalTextureFileHeight The total texture file height
     * 
     * @see #fullBlit(MatrixStack, int, int, int, int)
     */
    public static void advancedBlit(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureFileWidth, int totalTextureFileHeight)
    {
        BlitUtil.customInnerBlit(ms.getLast().getMatrix(), renderX, renderX + renderWidth, renderY, renderY + renderHeight, BlitUtil.blitOffset, textureX / (float)totalTextureFileWidth, (textureX + textureWidth) / (float)totalTextureFileWidth, textureY / (float)totalTextureFileHeight, (textureY + textureHeight) / (float)totalTextureFileHeight);
    }
    
    /**
     * Advanced blit method, rotated by 90 degrees.
     * 
     * Param 1-4: Where to and how big to draw on the screen.
     * Param 5-8: What part of the texture file to cut out and draw.
     * Param 9-10: How big the entire texture file is in general (pow2 only).
     * 
     * @param ms MatrixStack
     * @param renderX Where to draw on the screen
     * @param renderY Where to draw on the screen
     * @param renderWidth How big to draw on the screen
     * @param renderHeight How big to draw on the screen
     * @param textureX The left coordinate of the rect that is taken from the texture file and drawn
     * @param textureY The top coordinate of the rect that is taken from the texture file and drawn
     * @param textureWidth The width of the rect that is taken from the texture file and drawn
     * @param textureHeight The height of the rect that is taken from the texture file and drawn
     * @param totalTextureFileWidth The total texture file width
     * @param totalTextureFileHeight The total texture file height
     * 
     * @see #fullBlit90Degrees(MatrixStack, int, int, int, int)
     */
    public static void advancedBlit90Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureFileWidth, int totalTextureFileHeight)
    {
        float x1 = textureX / (float)totalTextureFileWidth;
        float y1 = textureY / (float)totalTextureFileHeight;
        float x2 = (textureX + textureWidth) / (float)totalTextureFileWidth;
        float y2 = (textureY + textureHeight) / (float)totalTextureFileHeight;
        BlitUtil.customInnerBlit(ms.getLast().getMatrix(), renderX, renderX + renderWidth, renderY, renderY + renderHeight, BlitUtil.blitOffset, x2, y1, x2, y2, x1, y2, x1, y1);
    }
    
    /**
     * Advanced blit method, rotated by 180 degrees.
     * 
     * Param 1-4: Where to and how big to draw on the screen.
     * Param 5-8: What part of the texture file to cut out and draw.
     * Param 9-10: How big the entire texture file is in general (pow2 only).
     * 
     * @param ms MatrixStack
     * @param renderX Where to draw on the screen
     * @param renderY Where to draw on the screen
     * @param renderWidth How big to draw on the screen
     * @param renderHeight How big to draw on the screen
     * @param textureX The left coordinate of the rect that is taken from the texture file and drawn
     * @param textureY The top coordinate of the rect that is taken from the texture file and drawn
     * @param textureWidth The width of the rect that is taken from the texture file and drawn
     * @param textureHeight The height of the rect that is taken from the texture file and drawn
     * @param totalTextureFileWidth The total texture file width
     * @param totalTextureFileHeight The total texture file height
     * 
     * @see #fullBlit180Degrees(MatrixStack, int, int, int, int)
     */
    public static void advancedBlit180Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureFileWidth, int totalTextureFileHeight)
    {
        float x1 = textureX / (float)totalTextureFileWidth;
        float y1 = textureY / (float)totalTextureFileHeight;
        float x2 = (textureX + textureWidth) / (float)totalTextureFileWidth;
        float y2 = (textureY + textureHeight) / (float)totalTextureFileHeight;
        BlitUtil.customInnerBlit(ms.getLast().getMatrix(), renderX, renderX + renderWidth, renderY, renderY + renderHeight, BlitUtil.blitOffset, x2, y2, x1, y2, x1, y1, x2, y1);
    }
    
    /**
     * Advanced blit method, rotated by 270 degrees.
     * 
     * Param 1-4: Where to and how big to draw on the screen.
     * Param 5-8: What part of the texture file to cut out and draw.
     * Param 9-10: How big the entire texture file is in general (pow2 only).
     * 
     * @param ms MatrixStack
     * @param renderX Where to draw on the screen
     * @param renderY Where to draw on the screen
     * @param renderWidth How big to draw on the screen
     * @param renderHeight How big to draw on the screen
     * @param textureX The left coordinate of the rect that is taken from the texture file and drawn
     * @param textureY The top coordinate of the rect that is taken from the texture file and drawn
     * @param textureWidth The width of the rect that is taken from the texture file and drawn
     * @param textureHeight The height of the rect that is taken from the texture file and drawn
     * @param totalTextureFileWidth The total texture file width
     * @param totalTextureFileHeight The total texture file height
     * 
     * @see #fullBlit270Degrees(MatrixStack, int, int, int, int)
     */
    public static void advancedBlit270Degrees(MatrixStack ms, int renderX, int renderY, int renderWidth, int renderHeight, int textureX, int textureY, int textureWidth, int textureHeight, int totalTextureFileWidth, int totalTextureFileHeight)
    {
        float x1 = textureX / (float)totalTextureFileWidth;
        float y1 = textureY / (float)totalTextureFileHeight;
        float x2 = (textureX + textureWidth) / (float)totalTextureFileWidth;
        float y2 = (textureY + textureHeight) / (float)totalTextureFileHeight;
        BlitUtil.customInnerBlit(ms.getLast().getMatrix(), renderX, renderX + renderWidth, renderY, renderY + renderHeight, BlitUtil.blitOffset, x1, y2, x1, y1, x2, y1, x2, y2);
    }
    
    protected static void customInnerBlit(Matrix4f matrix, int posX1, int posX2, int posY1, int posY2, int posZ, float texX1, float texX2, float texY1, float texY2)
    {
        BlitUtil.customInnerBlit(matrix, posX1, posX2, posY1, posY2, posZ, texX1, texY1, texX2, texY1, texX2, texY2, texX1, texY2);
    }
    
    protected static void customInnerBlit(Matrix4f matrix, int posX1, int posX2, int posY1, int posY2, int posZ, float topLeftX, float topLeftY, float topRightX, float topRightY, float botRightX, float botRightY, float botLeftX, float botLeftY)
    {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(matrix, (float)posX1, (float)posY2, (float)posZ).tex(botLeftX, botLeftY).endVertex();
        bufferbuilder.pos(matrix, (float)posX2, (float)posY2, (float)posZ).tex(botRightX, botRightY).endVertex();
        bufferbuilder.pos(matrix, (float)posX2, (float)posY1, (float)posZ).tex(topRightX, topRightY).endVertex();
        bufferbuilder.pos(matrix, (float)posX1, (float)posY1, (float)posZ).tex(topLeftX, topLeftY).endVertex();
        bufferbuilder.finishDrawing();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.draw(bufferbuilder);
    }
}
