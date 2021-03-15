package i18n;

import java.text.MessageFormat;
import java.util.*;

/**
 * 
 * @author Jurn
 *
 */
public class UITextHelper {

    private static final String TAAL_PROPERTIES = "i18n.Rummikub";

    private static Locale LOCALE_NL = new Locale("nl");
    private static Locale LOCALE_FR = new Locale("fr");
    private static Locale LOCALE_EN = new Locale("en");

    private static Locale locale = LOCALE_NL;

    /**
     * 
     * @param taal
     * @return true als locale is ge set is geworden
     */
    public static boolean setLocale(String taal) {
        switch (taal) {
            case "nl":
                locale = LOCALE_NL;
                break;

            case "fr":
                locale = LOCALE_FR;
                break;

            case "en":
                locale = LOCALE_EN;
                break;

            default:
                return false;
        }

        return true;
    }

    /**
     * 
     * @param key
     * @return string van key
     */
    public static String UIText(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(TAAL_PROPERTIES, locale);

        return bundle.getString(key);
    }

    /**
     * 
     * @param key
     * @param placeholders
     * @return List of strings van keys
     */
    public static String UIText(String key, Object[] placeholders) {
        ResourceBundle bundle = ResourceBundle.getBundle(TAAL_PROPERTIES, locale);
        MessageFormat formatter = new MessageFormat(bundle.getString(key), locale);

        return formatter.format(placeholders);
    }

}
