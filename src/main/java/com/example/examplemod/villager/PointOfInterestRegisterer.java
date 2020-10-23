package com.example.examplemod.villager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import net.minecraft.village.PointOfInterestType;

/**
 * Gives access to {@link PointOfInterestType#register(String, Set, int, int)}.
 * @author CAS_ual_TY
 *
 */
public class PointOfInterestRegisterer
{
    private static Method blockStatesInjector = null;
    
    /**
     * Gives access to {@link PointOfInterestType#register(String, Set, int, int)}.
     * Use {@link #register(PointOfInterestType)} to register your POIs.
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void initialize() throws NoSuchMethodException, SecurityException
    {
        PointOfInterestRegisterer.initialize("func_226359_a_ ");
    }
    
    /**
     * Alternative to {@link #initialize()} in case the srg method name has changed.
     */
    public static void initialize(String methodName) throws NoSuchMethodException, SecurityException
    {
        PointOfInterestRegisterer.blockStatesInjector = PointOfInterestType.class.getDeclaredMethod(methodName, PointOfInterestType.class);
        PointOfInterestRegisterer.blockStatesInjector.setAccessible(true);
    }
    
    /**
     * Register your POI. You <b>must</b> call {@link #initialize()} once before calling this.
     * @param poiType
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void register(PointOfInterestType poiType) throws NullPointerException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        PointOfInterestRegisterer.blockStatesInjector.invoke(null, poiType);
    }
}
