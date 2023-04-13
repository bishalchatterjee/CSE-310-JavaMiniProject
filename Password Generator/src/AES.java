import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;  
import java.security.InvalidAlgorithmParameterException;  
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.KeySpec;  
import java.util.Base64;  
import javax.crypto.BadPaddingException;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException; 

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.util.*;
public class AES
{  
    private static final String SECRET_KEY = "123456789";  
    private static final String SALTVALUE = "abcdefg";  
   
    public static String encrypt(String strToEncrypt)   
    {  
    try   
    {  
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
      IvParameterSpec ivspec = new IvParameterSpec(iv);        
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
      SecretKey tmp = factory.generateSecret(spec);  
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
      return Base64.getEncoder()  
.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
    }   
    catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
    {  
      System.out.println("Error occured during encryption: " + e.toString());  
    }  
    return null;  
    }  
    
    public static String decrypt(String strToDecrypt)   
    {  
    try   
    {  
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
      IvParameterSpec ivspec = new IvParameterSpec(iv);  
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
      SecretKey tmp = factory.generateSecret(spec);  
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
    }   
    catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
    {  
      System.out.println("Error occured during decryption: " + e.toString());  
    }  
    return null;  
    }  
    public static void performEncyption()   
    {  
        Scanner sc=new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter a string to encrypt/decrypt :");
        String originalval=sc.nextLine();  
        System.out.println();
        System.out.println("Select an operation");
        System.out.println("-------------------");
        System.out.println("1 - For Encryption");
        System.out.println("2 - For Decryption");
        System.out.println();
      
        System.out.println("Enter your Choice: ");

        char choice = sc.next().charAt(0);
        String  encryptedval=encrypt(originalval);;
        String decryptedval = decrypt(encryptedval);
        
        
        switch(choice)
        {
          case '1' : 
          {
            System.out.println();
            System.out.println("Encrypted value: " + encryptedval); 
            StringSelection selection = new StringSelection(encryptedval);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            System.out.println();
            System.out.println("Encrypted String copied to clipboard."); 
            try {
              Thread.sleep(2000); // 4 seconds delay
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
            break;
          }
           

          case '2' : 
          {
              System.out.println();
              System.out.println("Decrypted value: " + decryptedval);
              StringSelection selection = new StringSelection(decryptedval);
              Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
              clipboard.setContents(selection, null);
              System.out.println();
              System.out.println("Decrypted String copied to clipboard."); 
              try {
                Thread.sleep(2000); // 4 seconds delay
                System.out.println("Delay loop finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
              break;
          }

          default :
          {
                System.out.println("Please enter valid choice!");
                System.out.println();
                break;
          }
        }
    }  
}  