package client.enums;

public enum FontName {

    //todo
    textField("times new roman"),
    passwordField("times new roman"),
    button("AvQest"),
    accountText("FORTE"),
    MESSAGE_TEXT("FORTE"),
    MESSAGE_TITLE("FORTE"),
    accountTitle("EnglishTowne");

    private String name;

    private FontName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
