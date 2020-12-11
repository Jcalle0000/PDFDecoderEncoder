import org.apache.commons.codec.binary.Base64;

import java.io.*;


public class encodingPDFS {

    // this is a function
    private static String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file); // This is where the second function is being used
        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        return encodedString;
    }

    public static  void print(String input){
        String [] parts= input.split("(?<=\\G.{100})");
        for(int i=0;i<parts.length;i++){
            System.out.println( "Row "+ i + " " + parts[i] );
        }
        System.out.println( "Beginning \n" +parts[0] );
        System.out.println("Checking to see if they match the beginning");
        for(int i=0;i<100;i++){
            System.out.print( input.charAt(i) );
        }
        System.out.println( "End \n" +parts[parts.length-1] );
        System.out.println("Checking to see if they match the end");

        System.out.print( input.charAt(input.length()-3 ) );
        System.out.print( input.charAt(input.length()-2 ) );
        System.out.print( input.charAt(input.length()-1 ) );
    }


    public static void main(String [] args){

        // encode try
        try {
            String output;
//            output= encodeFileToBase64Binary("adobePDFSample.pdf");
            output= encodeFileToBase64Binary("NodeJS.pdf");
//            System.out.println(output);
            // now we have an encode base64 String
            int length=output.length();
            System.out.println("Length of base64 string is "+length);
//            print(output);

            String nameOfPDF= "Beginning3.pdf";
            File file   = new File(nameOfPDF); // the name we want to give it
            try (FileOutputStream fos = new FileOutputStream(file)  ) {
                // decoding process from base64
                byte[] decoder = java.util.Base64.getDecoder().decode(output);
                // how does this work
                fos.write(decoder);
                System.out.println(" PDF was saved");
            }
            catch ( Exception e){
                e.printStackTrace();
                System.out.println("File was not saved");
            }



        }
        // encode catch
        catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR encoding the PDF File");
        }
    }

//    public static void decodeAndSavePDF ()

    // so what byte[] functions return bytes?
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
            System.out.println("File is too large");
//            return
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

}

// Download the JAR FILE
//http://www.java2s.com/Code/Jar/o/Downloadorgapachecommonscodecjar.htm

