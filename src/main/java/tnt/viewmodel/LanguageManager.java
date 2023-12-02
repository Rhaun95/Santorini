package tnt.viewmodel;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import lombok.Data;

import java.util.Locale;
import java.util.ResourceBundle;
@Data
public class LanguageManager {

    private static boolean thirdplayer = false;

    public static void setThirdplayer(boolean x) {
    thirdplayer = x;
    }
    public static boolean getThirdplayer() {
    return thirdplayer;
    }
    

    SettingController settingController = new SettingController();
    private static ResourceBundle translater;
    private static String currentLanguage = "en";

    public static String getCurrentLanguage(){
        return currentLanguage;
    }
    public static void setCurrentLanguage(String language){
        currentLanguage = language;
    }
    public static void setLanguage(String languageCode){
        Locale locale = new Locale(languageCode);
        translater = ResourceBundle.getBundle("language.translate", locale);
        currentLanguage = languageCode;
    }
    public static String getText(String key){
        return translater.getString(key);
    }

}
