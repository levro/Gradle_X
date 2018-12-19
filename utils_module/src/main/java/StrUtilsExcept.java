public class StrUtilsExcept {

    public static int checkString( String str ) throws IllegalCharacter {
        if (str.contains( "a" )) {
            throw new IllegalCharacter( "a" );
        }
        else if (str.contains( "b" )) {
            throw new IllegalCharacter( "b" );
        }
        else {
            return str.length();
        }
    }
}
