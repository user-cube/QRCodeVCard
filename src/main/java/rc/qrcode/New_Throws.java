package rc.qrcode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Enum with all throws.
 * 
 * @author rc
 */
public enum New_Throws {
    NOT_FOUND_404("File not found!"),
    INVALID_PATH("Invalid path to store the file");
    
    private final String name;

    New_Throws(String name) {
        this.name = name;
    }

    /**
     * Enum to String.
     *
     * @return string to be displayed.
     */
    
    @Override
    public String toString() {
        return name;
    }
    
}
