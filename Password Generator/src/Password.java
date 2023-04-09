
public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    public int CharType(char C) {
        int val;

        // Char is Uppercase Letter
        if ((int) C >= 65 && (int) C <= 90)
            val = 1;

        // Char is Lowercase Letter
        else if ((int) C >= 97 && (int) C <= 122) {
            val = 2;
        }

        // Char is Digit
        else if ((int) C >= 48 && (int) C <= 57) {
            val = 3;
        }

        // Char is Symbol
        else {
            val = 4;
        }

        return val;
    }

    public boolean PasswordStrength() {
        String s = this.Value;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }
        if((s.length() >=8)==true) 
           if(UsedUpper && UsedLower && UsedNum && UsedSym) return true;

           return false;
    }

    public String calculateScore() {
        boolean Score = this.PasswordStrength();
        if (!Score) {
        return "Invalid! There should be atleast 8 characters in the password and it should include uppercase,lowercase,symbols and numbers";
        }
        return "Secure Password!";

    }

    @Override
    public String toString() {
        System.out.println();
        return Value;
    }
}
