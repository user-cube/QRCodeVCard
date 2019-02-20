/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.qrcode;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;
import net.glxn.qrgen.javase.QRCode;
import java.io.*;

/**
 * Generate VCard by QRCode.
 * 
 * @author rc
 */
public class QrCode {
    
    /**
     * Main function.
     * 
     * CSV structure:
     *      Name, Address, Company, Phone Number, Email, Website
     * 
     * 
     * @throws Exception
     *          - NOT_FOUND_404 - If file not founded.
     *          - INVALID_PATH - If the path to store the file is invalid.
     */
    public static void main(String... args) throws Exception{
        
        /**
         * File identifier.
         */
        int counter = 000;
        
        /**
         * Path to the CSV File.
         */
        String csvFile = "insertFullPath";
        
        /**
         * Line to be added and splited.
         */
        String line;
        
        /**
         * Split the String.
         */
        String cvsSplitBy = ",";
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFile))) {

            while ((line = csvReader.readLine()) != null) {
                
                /**
                 * Store the personal info from the CSV File.
                 */
                String[] personalInfo = line.split(cvsSplitBy);
                
                System.out.println(
                    "ID " + counter + " - " + personalInfo[0] + ","
                    + personalInfo[1] + "," + personalInfo[2] + "," 
                    + personalInfo[3] + "," + personalInfo[4] + "," 
                    + personalInfo[5]
                );
               
                /**
                 * Generate VCard.
                 */
                VCard vCard = new VCard();
                vCard.setName(personalInfo[0]);
                vCard.setAddress(personalInfo[1]);
                vCard.setCompany(personalInfo[2]);
                vCard.setPhoneNumber(personalInfo[3]);
                vCard.setEmail(personalInfo[4]);
                vCard.setWebsite(personalInfo[5]);

                /**
                 * Store VCard into the QRCode.
                 */
                ByteArrayOutputStream bout =
                    QRCode.from(vCard)
                            .withSize(250, 250)
                            .to(ImageType.PNG)
                            .stream();
                
                /**
                 * Store the QRCodes into the computer File System.
                 */
                try {
                    try (OutputStream out = new FileOutputStream(counter+".png")) {
                        bout.writeTo(out);
                        out.flush();
                    } 
                    } catch (FileNotFoundException e){
                        throw new Exception(New_Throws.NOT_FOUND_404.toString());
                    }
                catch (IOException e) {
                    throw new Exception(New_Throws.INVALID_PATH.toString());
                }
                counter ++; //increment the counter
            }
        } catch (IOException e) {
            throw new Exception(New_Throws.NOT_FOUND_404.toString()); // if file not found
        }
    }
}