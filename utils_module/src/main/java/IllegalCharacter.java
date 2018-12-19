class IllegalCharacter extends Exception {
    IllegalCharacter( String ch) {
        super( "'" + ch + "' is not allowed." );
    }
}