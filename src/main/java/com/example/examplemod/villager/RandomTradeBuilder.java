package com.example.examplemod.villager;

import java.util.Random;
import java.util.function.Function;

import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

/**
 * Builder class to create advanced random trades.
 * @author CAS_ual_TY
 * @see TradesUtil
 */
public class RandomTradeBuilder
{
    protected Function<Random, ItemStack> price;
    protected Function<Random, ItemStack> price2;
    protected Function<Random, ItemStack> forSale;
    
    protected final int maxTrades;
    protected final int xp;
    protected final float priceMult;
    
    /**
     * @param tradesUntilDisabled How often this trade can be done before the villager has to refresh the trade again at their work station
     * @param xpToVillager The amount of XP given to the villager per trade
     * @param priceMultiplier The price multiplier
     */
    public RandomTradeBuilder(int tradesUntilDisabled, int xpToVillager, float priceMultiplier)
    {
        this.price = null;
        this.price2 = (random) -> ItemStack.EMPTY;
        this.forSale = null;
        this.maxTrades = tradesUntilDisabled;
        this.xp = xpToVillager;
        this.priceMult = priceMultiplier;
    }
    
    public RandomTradeBuilder setPrice(Function<Random, ItemStack> price)
    {
        this.price = price;
        return this;
    }
    
    /**
     * Sets the first price Item with stack size within a fixed range.
     * @param item The price Item
     * @param min The minimum amount to pay (included)
     * @param max The maximum amount to pay (included)
     * @see #setEmeraldPrice(int, int)
     */
    public RandomTradeBuilder setPrice(Item item, int min, int max)
    {
        return this.setPrice(RandomTradeBuilder.createFunction(item, min, max));
    }
    
    public RandomTradeBuilder setPrice2(Function<Random, ItemStack> price2)
    {
        this.price2 = price2;
        return this;
    }
    
    /**
     * Sets the second price Item with stack size within a fixed range.
     * By not calling this, there is no 2nd Item to pay (you only pay with one item).
     * @param item The price Item
     * @param min The minimum amount to pay (included)
     * @param max The maximum amount to pay (included)
     */
    public RandomTradeBuilder setPrice2(Item item, int min, int max)
    {
        return this.setPrice2(RandomTradeBuilder.createFunction(item, min, max));
    }
    
    public RandomTradeBuilder setForSale(Function<Random, ItemStack> forSale)
    {
        this.forSale = forSale;
        return this;
    }
    
    /**
     * Sets the sold Item with stack size within a fixed range.
     * @param item The price Item
     * @param min The minimum amount to pay (included)
     * @param max The maximum amount to pay (included)
     */
    public RandomTradeBuilder setForSale(Item item, int min, int max)
    {
        return this.setForSale(RandomTradeBuilder.createFunction(item, min, max));
    }
    
    /**
     * Sets the price to be an amount of emeralds (within a single stack, so 1-64)
     */
    public RandomTradeBuilder setEmeraldPrice(int emeralds)
    {
        return this.setPrice((random) -> new ItemStack(Items.EMERALD, emeralds));
    }
    
    /**
     * Convenience method to set a set amount of an Item for sale for a set amount of emeralds.
     * @param emeralds The set amount of emeralds to buy the Item with
     * @param item The Item to buy
     * @param amt The amount the Item is bought by per trade
     */
    public RandomTradeBuilder setEmeraldPriceFor(int emeralds, Item item, int amt)
    {
        this.setEmeraldPrice(emeralds);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }
    
    /**
     * Convenience method to set a single Item for sale for a set amount of emeralds.
     * @param emeralds The set amount of emeralds to buy the Item with
     * @param item The Item to buy
     */
    public RandomTradeBuilder setEmeraldPriceFor(int emeralds, Item item)
    {
        return this.setEmeraldPriceFor(emeralds, item, 1);
    }
    
    /**
     * Convenience method to set the price to an amount of emeralds within a fixed range.
     * @param emeraldsMin The minimum amount of emeralds
     * @param emeraldsMax The maximum amount of emeralds
     * @param item The Item to buy
     */
    public RandomTradeBuilder setEmeraldPrice(int emeraldsMin, int emeraldsMax)
    {
        return this.setPrice(Items.EMERALD, emeraldsMin, emeraldsMax);
    }
    
    /**
     * Convenience method to set a set amount of an Item for sale for an amount of emeralds within a fixed range.
     * @param emeraldsMin The minimum amount of emeralds (included)
     * @param emeraldsMax The maximum amount of emeralds (included)
     * @param item The Item to buy
     * @param amt The amount the Item is bought by per trade
     */
    public RandomTradeBuilder setEmeraldPriceFor(int emeraldsMin, int emeraldsMax, Item item, int amt)
    {
        this.setEmeraldPrice(emeraldsMin, emeraldsMax);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }
    
    /**
     * Convenience method to set a single Item for sale for an amount of emeralds within a fixed range.
     * @param emeraldsMin The minimum amount of emeralds (included)
     * @param emeraldsMax The maximum amount of emeralds (included)
     * @param item The Item to buy
     */
    public RandomTradeBuilder setEmeraldPriceFor(int emeraldsMin, int emeraldsMax, Item item)
    {
        return this.setEmeraldPriceFor(emeraldsMin, emeraldsMax, item, 1);
    }
    
    public boolean canBuild()
    {
        return this.price != null && this.forSale != null;
    }
    
    /**
     * Builds the trade.
     * @return An ITrade instance reflecting all the set parameters
     */
    public ITrade build()
    {
        if(!this.canBuild())
        {
            throw new IllegalStateException("Missing builder parameters.");
        }
        
        return (entity, random) -> new MerchantOffer(this.price.apply(random), this.price2.apply(random), this.forSale.apply(random), this.maxTrades, this.xp, this.priceMult);
    }
    
    protected static Function<Random, ItemStack> createFunction(Item item, int min, int max)
    {
        return (random) -> new ItemStack(item, random.nextInt(max - min + 1) + min);
    }
}
