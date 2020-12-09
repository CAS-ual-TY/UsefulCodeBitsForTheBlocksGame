package com.example.examplemod.client.screen.widget;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

/**
 * <p>Same as {@link FramedTextWidget} but colored red or blue instead. The color may change dynamically.</p>
 * 
 * @author CAS_ual_TY
 * 
 * @see ColoredButton
 *
 */
public class ColoredFramedTextWidget extends FramedTextWidget
{
    public static final int BLUE_OFFSET = 0;
    public static final int RED_OFFSET = 60;
    
    public static final ResourceLocation RESOURCE = new ResourceLocation(ExampleMod.MOD_ID, "textures/gui/colored_button.png");
    
    public int offset;
    
    public ColoredFramedTextWidget(int xIn, int yIn, int widthIn, int heightIn, Supplier<ITextComponent> msgGetter)
    {
        super(xIn, yIn, widthIn, heightIn, msgGetter);
    }
    
    public ColoredFramedTextWidget(int xIn, int yIn, int widthIn, int heightIn, Supplier<ITextComponent> msgGetter, ITooltip2 tooltip)
    {
        super(xIn, yIn, widthIn, heightIn, msgGetter, tooltip);
    }
    
    @Override
    public void renderButton(MatrixStack ms, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.getTextureManager().bindTexture(ColoredFramedTextWidget.RESOURCE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(ms, this.x, this.y, 0, this.offset + i * 20, this.width / 2, this.height / 2);
        this.blit(ms, this.x + this.width / 2, this.y, 200 - this.width / 2, this.offset + i * 20, this.width / 2, this.height / 2);
        this.blit(ms, this.x, this.y + this.height / 2, 0, this.offset + (i + 1) * 20 - this.height / 2, this.width / 2, this.height / 2);
        this.blit(ms, this.x + this.width / 2, this.y + this.height / 2, 200 - this.width / 2, this.offset + (i + 1) * 20 - this.height / 2, this.width / 2, this.height / 2);
        this.renderBg(ms, minecraft, mouseX, mouseY);
        
        int j = this.getFGColor();
        AbstractGui.drawCenteredString(ms, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        
        if(this.isHovered())
        {
            this.renderToolTip(ms, mouseX, mouseY);
        }
    }
    
    public ColoredFramedTextWidget setBlue()
    {
        this.offset = ColoredFramedTextWidget.BLUE_OFFSET;
        return this;
    }
    
    public ColoredFramedTextWidget setRed()
    {
        this.offset = ColoredFramedTextWidget.RED_OFFSET;
        return this;
    }
}