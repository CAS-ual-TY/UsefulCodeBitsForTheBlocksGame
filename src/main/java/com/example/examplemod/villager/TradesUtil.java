package com.example.examplemod.villager;

import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;

/**
 * 
 * @author CAS_ual_TY
 *
 */
public class TradesUtil
{
    /**
     * Builds a simple fixed trade. See the wiki for example values.
     * @param wanted1 The ItemStack the villager is buying (first slot in trade Gui)
     * @param given The ItemStack the villager is selling (third slot in trade Gui)
     * @param tradesUntilDisabled How often this trade can be done before the villager has to refresh the trade again at their work station
     * @param xpToVillager The amount of XP given to the villager per trade
     * @param priceMultiplier The price multiplier
     * @return An ITrade instance matching the given criteria
     * @see #buildFixedTrade(ItemStack, ItemStack, ItemStack, int, int, float)
     * @see RandomTradeBuilder
     */
    public static ITrade buildFixedTrade(ItemStack wanted1, ItemStack given, int tradesUntilDisabled, int xpToVillager, float priceMultiplier)
    {
        return TradesUtil.buildFixedTrade(wanted1, ItemStack.EMPTY, given, tradesUntilDisabled, xpToVillager, priceMultiplier);
    }
    
    /**
     * Builds a simple fixed trade. See the wiki for example values.
     * @param wanted1 The 1st ItemStack the villager is buying (first slot in trade Gui)
     * @param wanted2 The 2nd ItemStack the villager is buying (second slot in trade Gui)
     * @param given The ItemStack the villager is selling (third slot in trade Gui)
     * @param tradesUntilDisabled How often this trade can be done before the villager has to refresh the trade again at their work station
     * @param xpToVillager The amount of XP given to the villager per trade
     * @param priceMultiplier The price multiplier
     * @return An ITrade instance matching the given criteria
     * @see #buildFixedTrade(ItemStack, ItemStack, int, int, float)
     * @see RandomTradeBuilder
     */
    public static ITrade buildFixedTrade(ItemStack wanted1, ItemStack wanted2, ItemStack given, int tradesUntilDisabled, int xpToVillager, float priceMultiplier)
    {
        return (entity, random) -> new MerchantOffer(wanted1, wanted2, given, tradesUntilDisabled, xpToVillager, priceMultiplier);
    }
}
