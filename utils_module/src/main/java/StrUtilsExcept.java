public class StrUtilsExcept {

    public static void checkString( String str ) throws IllegalCharacter {
        if (str.contains( "a" )) {
            throw new IllegalCharacter( "a" );
        }
        else if (str.contains( "b" )) {
            throw new IllegalCharacter( "b" );
        }
        else {
            System.out.println("Good string");
        }
    }
}
