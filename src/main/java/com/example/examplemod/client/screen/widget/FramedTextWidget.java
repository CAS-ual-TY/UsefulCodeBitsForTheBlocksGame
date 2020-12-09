package com.example.examplemod.client.screen.widget;

import java.util.function.Supplier;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * <p>A widget to display text. The text is gotten from a message getter, so it can be dynamic.
 * Easier to use that to constantly change the message via {@link #setMessage(ITextComponent)},
 * this does nothing now.</p>
 * 
 * <p>The widget is using a disabled button as background, but the text is not grayed out;
 * this way it is distinguishable from actual disabled buttons</p>.
 * 
 * @author CAS_ual_TY
 *
 */
public class FramedTextWidget extends Widget
{
    public Supplier<ITextComponent> msgGetter;
    public ITooltip2 tooltip;
    
    public FramedTextWidget(int xIn, int yIn, int widthIn, int heightIn, Supplier<ITextComponent> msgGetter, ITooltip2 tooltip)
    {
        super(xIn, yIn, widthIn, heightIn, StringTextComponent.EMPTY);
        this.msgGetter = msgGetter;
        this.active = false;
        this.tooltip = tooltip;
    }
    
    public FramedTextWidget(int xIn, int yIn, int widthIn, int heightIn, Supplier<ITextComponent> msgGetter)
    {
        this(xIn, yIn, widthIn, heightIn, msgGetter, ITooltip2.EMPTY_TOOLTIP);
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
        
        int j = this.getFGColor();
        AbstractGui.drawCenteredString(ms, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
        
        if(this.isHovered())
        {
            this.renderToolTip(ms, mouseX, mouseY);
        }
    }
    
    @Override
    public int getFGColor()
    {
        return 16777215; //From super class, we set this color to be always light
    }
    
    @Override
    public ITextComponent getMessage()
    {
        return this.msgGetter.get();
    }
    
    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.tooltip.onTooltip(this, matrixStack, mouseX, mouseY);
    }
}