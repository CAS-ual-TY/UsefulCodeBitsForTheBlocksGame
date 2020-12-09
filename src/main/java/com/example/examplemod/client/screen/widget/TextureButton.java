package com.example.examplemod.client.screen.widget;

import com.example.examplemod.client.screen.BlitUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

/**
 * <p>Simple class to render a texture over a button.</p>
 * 
 * <p>The texture is drawn from the top left of the button.</p>
 * 
 * @author CAS_ual_TY
 *
 */
public class TextureButton extends Button
{
    public ResourceLocation textureLocation;
    
    public int texX;
    public int texY;
    public int texW;
    public int texH;
    
    public TextureButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction, ITooltip onTooltip)
    {
        super(x, y, width, height, title, pressedAction, onTooltip);
        this.textureLocation = null;
    }
    
    public TextureButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction)
    {
        super(x, y, width, height, title, pressedAction);
        this.textureLocation = null;
    }
    
    public TextureButton setTexture(ResourceLocation textureLocation, int texX, int texY, int texW, int texH)
    {
        this.textureLocation = textureLocation;
        this.texX = texX;
        this.texY = texY;
        this.texW = texW;
        this.texH = texH;
        return this;
    }
    
    @Override
    public void renderButton(MatrixStack ms, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.getTextureManager().bindTexture(Widget.WIDGETS_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(ms, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height / 2);
        this.blit(ms, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height / 2);
        this.blit(ms, this.x, this.y + this.height / 2, 0, 46 + (i + 1) * 20 - this.height / 2, this.width / 2, this.height / 2);
        this.blit(ms, this.x + this.width / 2, this.y + this.height / 2, 200 - this.width / 2, 46 + (i + 1) * 20 - this.height / 2, this.width / 2, this.height / 2);
        this.renderBg(ms, minecraft, mouseX, mouseY);
        
        this.drawTexture(ms, mouseX, mouseY, partialTicks);
        
        int j = this.getFGColor();
        AbstractGui.drawCenteredString(ms, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        
        if(this.isHovered())
        {
            this.renderToolTip(ms, mouseX, mouseY);
        }
    }
    
    public void drawTexture(MatrixStack ms, int mouseX, int mouseY, float partialTicks)
    {
        if(this.textureLocation != null)
        {
            Minecraft.getInstance().getTextureManager().bindTexture(this.textureLocation);
            BlitUtil.advancedBlit(ms, this.x, this.y, this.width, this.height, this.texX, this.texY, this.texW, this.texH, 256, 256);
        }
    }
}