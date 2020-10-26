package com.example.examplemod.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.util.text.ITextComponent;

/**
 * <p>Basically just a normal {@link Screen} class, but contains the useful screen-dimension-fields (xSize, ySize, guiLeft, guiTop) and foreground/background render methods of the {@link ContainerScreen} class.</p>
 * <p>Useful for anyone who wants to use these fields/methods or who changed a bunch of container screens to "normal" screens (like I did).</p>
 * 
 * @author CAS_ual_TY
 *
 */
public class SizedScreen extends Screen
{
    protected int xSize = 176;
    protected int ySize = 166;
    protected int guiLeft;
    protected int guiTop;
    
    protected SizedScreen(ITextComponent titleIn)
    {
        super(titleIn);
    }
    
    @Override
    protected void init()
    {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
    
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        int i = this.guiLeft;
        int j = this.guiTop;
        
        this.drawGuiBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
        
        RenderSystem.disableDepthTest();
        
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        
        matrixStack.push();
        matrixStack.translate((double)i, (double)j, 0);
        
        this.drawGuiForegroundLayer(matrixStack, partialTicks, mouseX, mouseY);
        
        matrixStack.pop();
        
        RenderSystem.enableDepthTest();
    }
    
    protected void drawGuiForegroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
    }
    
    protected void drawGuiBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
    }
    
    public int getGuiLeft()
    {
        return this.guiLeft;
    }
    
    public int getGuiTop()
    {
        return this.guiTop;
    }
    
    public int getXSize()
    {
        return this.xSize;
    }
    
    public int getYSize()
    {
        return this.ySize;
    }
}
