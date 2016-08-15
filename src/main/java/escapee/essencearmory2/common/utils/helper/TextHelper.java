package escapee.essencearmory2.common.utils.helper;

import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static String localiseText(String text) {
        return I18n.format(text);
    }


    /**
     * Used for debugging
     *
     * @param text
     * @return
     */
    public static String debugText(String text) {
        return BLUE + "[" + LIGHT_BLUE + "DeBug" + BLUE + "]" + END + ": " + text;
    }

    /**
     * @param text      your very special text!
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
    public static final String BLACK = (char) 167 + "0";
    public static final String BLUE = (char) 167 + "1";
    public static final String GREEN = (char) 167 + "2";
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = (char) 167 + "4";
    public static final String PURPLE = (char) 167 + "5";
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = (char) 167 + "8";
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = (char) 167 + "e";
    public static final String WHITE = (char) 167 + "f";

    /**
     * Components
     */
    public static final String OBFUSCATED = (char) 167 + "k";
    public static final String BOLD = (char) 167 + "l";
    public static final String STRIKETHROUGH = (char) 167 + "m";
    public static final String UNDERLINE = (char) 167 + "n";
    public static final String ITALIC = (char) 167 + "o";
    public static final String END = (char) 167 + "r";
}
