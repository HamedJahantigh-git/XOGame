package client.graphic.myComponent;

import client.enums.ClientPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyFont {
    private Font font;
    private String  fontName;
    private int fontSize;

    public MyFont(String fontName, int fontSize){
           this.fontName = fontName;
           this.fontSize = fontSize;
           loadFont();
    }

    private void loadFont(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(
                    ClientPath.fontFolder.getPath()+ "/"+fontName+".ttf")).deriveFont((float) fontSize);
            //create the font to use. Specify the size!
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }


    public Font getFont() {
        return font;
    }
}
