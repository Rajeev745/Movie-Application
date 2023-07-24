package com.example.moviesapp.enums;

public class EnumUtilityFunctions {

    private static EnumUtilityFunctions instance;

    public EnumUtilityFunctions getInstance() {
        if (instance == null) {
            instance = new EnumUtilityFunctions();
        }
        return instance;
    }

    public String getLanguageName(Language language) {
        switch (language) {
            case ENGLISH:
                return "English";
            case SPANISH:
                return "Spanish";
            case FRENCH:
                return "French";
            case GERMAN:
                return "German";
            case ITALIAN:
                return "Italian";
            case PORTUGUESE:
                return "Portuguese";
            case DUTCH:
                return "Dutch";
            case SWEDISH:
                return "Swedish";
            case DANISH:
                return "Danish";
            case NORWEGIAN:
                return "Norwegian";
            case FINNISH:
                return "Finnish";
            case POLISH:
                return "Polish";
            case CZECH:
                return "Czech";
            case SLOVAK:
                return "Slovak";
            case RUSSIAN:
                return "Russian";
            case UKRAINIAN:
                return "Ukrainian";
            case GREEK:
                return "Greek";
            case TURKISH:
                return "Turkish";
            case ARABIC:
                return "Arabic";
            case HEBREW:
                return "Hebrew";
            case HINDI:
                return "Hindi";
            case BENGALI:
                return "Bengali";
            case JAPANESE:
                return "Japanese";
            case CHINESE:
                return "Chinese";
            case KOREAN:
                return "Korean";
            case THAI:
                return "Thai";
            case VIETNAMESE:
                return "Vietnamese";
            case INDONESIAN:
                return "Indonesian";
            case MALAY:
                return "Malay";
            case TAGALOG:
                return "Tagalog";
            case SWAHILI:
                return "Swahili";
            case CATALAN:
                return "Catalan";
            case CROATIAN:
                return "Croatian";
            case SERBIAN:
                return "Serbian";
            case ROMANIAN:
                return "Romanian";
            case BULGARIAN:
                return "Bulgarian";
            case HUNGARIAN:
                return "Hungarian";
            case SLOVENIAN:
                return "Slovenian";
            case MACEDONIAN:
                return "Macedonian";
            case ALBANIAN:
                return "Albanian";
            case ESTONIAN:
                return "Estonian";
            case LATVIAN:
                return "Latvian";
            case LITHUANIAN:
                return "Lithuanian";
            case BELARUSIAN:
                return "Belarusian";
            case GEORGIAN:
                return "Georgian";
            case ARMENIAN:
                return "Armenian";
            case AZERBAIJANI:
                return "Azerbaijani";
            default:
                return "Unknown language";
        }
    }
}
