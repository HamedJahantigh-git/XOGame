package client.enums;

public enum MessageEnum {
    EMPTY_IMPORT("<html><center>Please Fill Empty Fields.</center><br><center></center><br><center></center></html>"),
    REPEATED_USERNAME("<html><center>Repeated Username.</center>"+
            "<br><center>Please Enter other Username.</center><br><center></center><br><center></center></html>"),
    USER_NO_EXIST("<html><center>Username is Wrong.</center><br><center></center><br><center></center></html>"),
    WRONG_PASSWORD("<html><center>Password is Wrong.</center><br><center></center><br><center></center></html>"),
    WINING("<html><center>You Win :)</center></html>"),
    LOSING("<html><center>Your Opponent Win :(</center></html>"),
    EQUALITY("<html><center>Equality :|</center></html>");

    private String text;

    private MessageEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
