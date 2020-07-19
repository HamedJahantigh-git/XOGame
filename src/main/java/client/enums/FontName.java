package client.enums;

public enum FontName {

    //todo
    textField("Times New Roman"),
    passwordField("Times New Roman"),
    button("Belwe Bd BT Bold"),
    accountText("FORTE"),
    accountTitle("englishTowne");

    private String name;

    private FontName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
