package com.example.examplemod.client.screen.widget;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

/**
 * A colored button which you can dynamically change to red or blue.
 * 
 * @author CAS_ual_TY
 *
 */
public class ColoredButton extends Button
{
    public static final int BLUE_OFFSET = 0;
    public static final int RED_OFFSET = 60;
    
    public static final ResourceLocation RESOURCE = new ResourceLocation(ExampleMod.MOD_ID, "textures/gui/colored_button.png");
    public int offset;
    
    public ColoredButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction)
    {
        this(x, y, width, height, title, pressedAction, Button.field_238486_s_);
    }
    
    public ColoredButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction, ITooltip onTooltip)
    {
        super(x, y, width, height, title, pressedAction, onTooltip);
        this.offset = 0;
    }
    
    @Override
    public void renderButton(MatrixStack ms, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.getTextureManager().bindTexture(ColoredButton.RESOURCE);
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
    
    public ColoredButton setBlue()
    {
        this.offset = ColoredButton.BLUE_OFFSET;
        return this;
    }
    
    public ColoredButton setRed()
    {
        this.offset = ColoredButton.RED_OFFSET;
        return this;
    }
}
