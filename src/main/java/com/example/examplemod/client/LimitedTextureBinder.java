package com.example.examplemod.client;

import java.util.Iterator;
import java.util.LinkedList;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * <p>Instances of this class are used to bind textures with a set limit. Once the limit is overstepped,
 * the bound texture that has not been used for the most time (in comparison to all other bound textures) is deleted.
 * Should save memory if you have to work with a lot of textures in Guis (over 10k in my case).</p>
 * 
 * <p>Usage: Create a permanent instance of this class and call {@link #bindTexture(ResourceLocation)} (instead of the one in the texture manager) to bind a texture.</p>
 * 
 * @author CAS_ual_TY
 *
 */
public class LimitedTextureBinder
{
    private final Minecraft mc;
    public final int size;
    private final LinkedList<ResourceLocation> list;
    
    public LimitedTextureBinder(Minecraft minecraft, int size)
    {
        this.mc = minecraft;
        this.size = size;
        this.list = new LinkedList<>();
    }
    
    public void bindTexture(ResourceLocation rl)
    {
        // if the list isnt full, just add the new rl
        if(this.list.size() >= this.size)
        {
            // list is already full
            // if its already in, remove it (itll be re-added in front)
            // else remove the last one
            
            Iterator<ResourceLocation> it = this.list.iterator();
            boolean found = false;
            
            while(it.hasNext())
            {
                if(rl.equals(it.next()))
                {
                    // its already in, remove it
                    it.remove();
                    found = true;
                    break;
                }
            }
            
            // wasnt in, remove the last one
            if(!found)
            {
                // size must be >= 1, so this can only be called if the list size is >= size >= 1
                this.unbind(this.list.removeLast());
            }
        }
        
        // (re-)add it in front
        this.list.addFirst(rl);
        this.bind(rl);
    }
    
    private void bind(ResourceLocation rl)
    {
        this.mc.getTextureManager().bindTexture(rl);
    }
    
    private void unbind(ResourceLocation rl)
    {
        this.mc.getTextureManager().deleteTexture(rl);
    }
}
