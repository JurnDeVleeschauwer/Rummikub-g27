package i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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

    /**locale instellen
     * 
     * @param taal taal
     * @return true als locale gezet is geworden
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

    /** key omzetten in string
     * 
     * @param key key
     * @return string van key
     */
    public static String UIText(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(TAAL_PROPERTIES, locale);

        return bundle.getString(key);
    }

    /** meerdere keys omzetten in meerdere strings
     * 
     * @param key key
     * @param placeholders placeholders
     * @return List of strings van keys
     */
    public static String UIText(String key, Object[] placeholders) {
        ResourceBundle bundle = ResourceBundle.getBundle(TAAL_PROPERTIES, locale);
        MessageFormat formatter = new MessageFormat(bundle.getString(key), locale);

        return formatter.format(placeholders);
    }

}
