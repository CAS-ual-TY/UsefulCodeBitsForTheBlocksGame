package com.example.examplemod.client.screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

/**
 * <p>This class allows you to switch to different container screens without the container closing.
 * This is very handy if your container has very different states in which the screen is very different.
 * You can then split up the rendering of each state into multiple classes and have them switch on state change.</p>
 * 
 * @author CAS_ual_TY
 */
public abstract class SwitchableContainerScreen<T extends Container> extends ContainerScreen<T>
{
    private boolean isClosedByPlayer;
    
    public SwitchableContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn)
    {
        super(screenContainer, inv, titleIn);
        this.isClosedByPlayer = true;
    }
    
    @Override
    public void onClose()
    {
        if(this.isClosedByPlayer)
        {
            super.onClose();
        }
    }
    
    /**
     * Switches to another screen. Make sure you pass on the same container, player inventory and title.
     * @param screen The new ContainerScreen instance to switch to
     * @see ContainerScreen#getContainer()
     * @see ContainerScreen#playerInventory
     * @see ContainerScreen#getTitle()
     */
    public void switchScreen(ContainerScreen<T> screen)
    {
        this.isClosedByPlayer = false;
        this.minecraft.displayGuiScreen(screen);
    }
}
