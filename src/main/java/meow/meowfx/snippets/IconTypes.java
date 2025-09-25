package meow.meowfx.snippets;

public enum IconTypes {
    PEN("✏"),
    CHECK("✔"),
    CROSS("✖"),
    WARNING("⚠"),
    HIGH_VOLTAGE("⚡"),
    BULLET("•"),
    INFO("ℹ"),
    ARROW_UP("⬆"),
    ARROW_DOWN("⬇"),
    ARROW_LEFT("⬅"),
    ARROW_RIGHT("➡"),
    DOUBLE_ARROW_LEFT("⇐"),
    DOUBLE_ARROW_RIGHT("⇒"),
    DOUBLE_ARROW_UP("⇑"),
    DOUBLE_ARROW_DOWN("⇓"),
    LEFT_RIGHT_ARROW("↔"),
    UP_DOWN_ARROW("↕"),
    SETTINGS("⚙"),
    SEARCH("⌕"),
    MENU("☰"),
    RELOAD("↻"),
    COPY("⎘"),
    CUT("✂"),
    PASTE("⎙"),
    SQUARE("□"),
    MENU_VERTICAL("⋮"),
    FLAG("⚑"),
    QUESTION_MARK("❓"),
    EXCLAMATION_MARK("❗"),
    MENU_HORIZONTAL("⋯"),
    PAUSE("⏸"),
    PLAY("▶"),
    STOP("⏹"),
    HEART("❤"),
    STAR("★"),
    CLOUD("☁"),
    ERROR("⛔"),
    COPYRIGHT("©"),
    REGISTERED("®"),
    TRADEMARK("™"),
    CLOCK("⏰"),
    HOURGLASS("⌛"),
    INFINITY("∞"),
    DAGGER("†"),
    DOUBLE_DAGGER("‡"),
    SNOWFLAKE("❄"),
    YIN_YANG("☯"),
    SMILING_FACE("☺"),
    BLACK_SMILING_FACE("☻"),
    HOT_BEVERAGE("☕"),
    SKULL_AND_CROSSBONES("☠"),
    RADIOACTIVE_SIGN("☢"),
    BIOHAZARD_SIGN("☣");

    private final String unicode;

    IconTypes(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}