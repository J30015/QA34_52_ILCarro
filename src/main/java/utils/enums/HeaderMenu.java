package utils.enums;

public enum HeaderMenu {
    LOGO("//img[@alt='logo']"),
    SEARCH("//a[text()=' Search ']"),
    LET_THE_CAR_WORK("//a[@href='let-car-work']"),
    TERMS_OF_USE("//a[@href=[terms-of-use']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOGIN("//a[text()=' Log in ']"),
    DELETE_ACCOUNT("//div[@class='header']//a[text()='Delete account']"),
    LOGOUT("//a[@href='/logout?url=%2Fsearch']");
    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
