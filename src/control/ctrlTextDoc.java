package control;

import java.util.Date;
import javax.swing.text.*;

public class ctrlTextDoc extends PlainDocument {
    private int limit=0;
    // optional uppercase conversion
    private boolean toUppercase = false;
    private String toCharacter="";
    private String toDesimal="";

    public static boolean KAPITAL = true;
    public static String  DESIMAL = "DESIMAL"; 
    public static String  CHARACTER= "CHARACTER"; 

    public ctrlTextDoc() {
    }
    
    public ctrlTextDoc(int limit) {
        super();
        this.limit = limit;
    }

    public ctrlTextDoc(boolean upper, int limit, String Karakter) {
        super();
        this.toUppercase = upper;
        this.limit = limit;
        this.toCharacter = Karakter;
    }

    public ctrlTextDoc(boolean upper) {
        super();
        this.toUppercase = upper;
    }

    public ctrlTextDoc(String des) {
        super();
        this.toDesimal = des;
    }

    public ctrlTextDoc(int limit,String des) {
        super();
        this.limit=limit;
        this.toDesimal = des;
    }

    public ctrlTextDoc(int limit, boolean upper) {
        super();
        this.limit = limit;
        toUppercase = upper;
    }

    public void insertString(int offset, String  str, AttributeSet attr)
        throws BadLocationException {

            if (str == null) return;

            if(this.limit == 0) {
                if(toUppercase) 
                    str = str.toUpperCase();
                if(toCharacter.equals(ctrlTextDoc.CHARACTER)) {
                    if(!Character.isSpaceChar(str.charAt(0)) && !Character.isLetter(str.charAt(0)))
                        return;
                }
                if(toDesimal.equals(ctrlTextDoc.DESIMAL)) {
                    if(!Character.isDigit(str.charAt(0)))
                        return;
                }
                super.insertString(offset, str, attr);
            }
            else if((getLength() + str.length()) <= limit) {
                if (toUppercase) 
                    str = str.toUpperCase();
                if(toCharacter.equals(ctrlTextDoc.CHARACTER)) {
                    if(!Character.isSpaceChar(str.charAt(0)) && !Character.isLetter(str.charAt(0)))
                        return;
                }
                if(toDesimal.equals(ctrlTextDoc.DESIMAL)) {
                    if(!Character.isDigit(str.charAt(0)))
                        return;
                }
                super.insertString(offset, str, attr);
            }
    }
    
    public Date setActiveDate() {
        Date tgl = new Date();
        return tgl;        
    } 
}

