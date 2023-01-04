package com.user.servlet.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {

        Locale locale = new Locale("ru", "RU");
        var aDefault = Locale.getDefault();
        System.out.println(aDefault);

        var translations = ResourceBundle.getBundle("translations", locale);
        System.out.println(translations.getString("page.login.password"));

    }

}
