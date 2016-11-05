package com.teamwizardry.inhumanresources.common.utils.helper;

import java.util.ArrayList;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

/**
 * Created by SirShadow on 14.8.2016.
 */
public class TextHelper {

    public  static final ArrayList<String>COLORS = new ArrayList<String>();
    public  static final ArrayList<String>COMPONENT= new ArrayList<String>();


    public static boolean displayShiftForDetail = true;
    public static boolean displayStackCount = false;

    public static boolean isShiftKeyDown() {
        return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
    }

    /**
     * Used for debugging
     *
     * @param text
     * @return
     */
    public static String debugText(String text) {
        return BLUE + "[" + LIGHT_BLUE + "debug" + BLUE + "]" + END + ": " + text;
    }

    public static String localise(String text)
    {
        return I18n.format(text);
    }

    /**
     * @param text your very special text!
     * @param textColor use to set the string color not to add text(use: TextHelper.BLUE or any other string color)!!
     * @return
     */
    public static String specialText(String text,String textColor) {
        if(textColor.equals(TextHelper.getStringFromName(textColor)))
        {
            return textColor + text;
        }else {
            return text;
        }
    }

    /**
     * Sets the given int to string!
     *
     * @param i the int
     * @return the int to string!
     */
    public static String intText(int i)
    {
        return Integer.toString(i);
    }

    /**
     * Sets the float to string!
     * @param f
     * @return
     */
    public static String floatText(float f)
    {
        return Float.toString(f);
    }


    /**
     * Registers all the colors and components
      */
    public static void addColorsAndComponents() {
        COLORS.add(BLACK);
        COLORS.add(GREEN);
        COLORS.add(BLUE);
        COLORS.add(GREEN);
        COLORS.add(TEAL);
        COLORS.add(RED);
        COLORS.add(PURPLE);
        COLORS.add(ORANGE);
        COLORS.add(LIGHT_GRAY);
        COLORS.add(GRAY);
        COLORS.add(LIGHT_BLUE);
        COLORS.add(BRIGHT_GREEN);
        COLORS.add(BRIGHT_BLUE);
        COLORS.add(LIGHT_RED);
        COLORS.add(PINK);
        COLORS.add(YELLOW);
        COLORS.add(WHITE);

        COMPONENT.add(OBFUSCATED);
        COMPONENT.add(BOLD);
        COMPONENT.add(STRIKETHROUGH);
        COMPONENT.add(UNDERLINE);
        COMPONENT.add(ITALIC);
        COMPONENT.add(END);
    }

    /**
     * Gets the color from color name
     * @param name
     * @return
     */
    public static String getStringFromName(String name){
        for (int i = 0; i < COLORS.size(); i ++){
            if (COLORS.get(i).intern().equals(name)){
                return COLORS.get(i);
            }
        }
        return null;
    }

    /**
     * Gets component from component name
     * @param name
     * @return
     */
    public static String getComponentFromName(String name){
        for (int i = 0; i < COMPONENT.size(); i ++){
            if (COMPONENT.get(i).intern().equals(name)){
                return COMPONENT.get(i);
            }
        }
        return null;
    }

    /**
     * Colors
     */
    public static final String BLACK = TextFormatting.BLACK.toString();
    public static final String BLUE = TextFormatting.BLUE.toString();
    public static final String GREEN = TextFormatting.GREEN.toString();
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = TextFormatting.RED.toString();
    public static final String PURPLE = TextFormatting.LIGHT_PURPLE.toString();
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = TextFormatting.GRAY.toString();
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = TextFormatting.YELLOW.toString();
    public static final String WHITE = TextFormatting.WHITE.toString();

    /**
     * Components
     */
    public static final String OBFUSCATED = TextFormatting.OBFUSCATED.toString();
    public static final String BOLD = TextFormatting.BOLD.toString();
    public static final String STRIKETHROUGH = TextFormatting.STRIKETHROUGH.toString();
    public static final String UNDERLINE = TextFormatting.UNDERLINE.toString();
    public static final String ITALIC = TextFormatting.ITALIC.toString();
    public static final String END = (char) 167 + "r";
}
