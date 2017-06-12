package com.company;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.math.BigInteger;
import java.io.*;
import java.net.URL;
import org.apache.commons.*;
import java.nio.file.Paths;
//key is 123456712345671234567

public class Main {
    /*
    public static String toHex(String arg) {
        try {
            return String.format("%040x", new BigInteger(1, arg.getBytes("UTF-16")));
        }catch(UnsupportedEncodingException e){
            System.out.println("placeholdererror");
        }
        return "";
    }
    */
    public static void main(String[] args) {
        String seed = "313233343536373132333435363731323334353637";//hex of base32 of key
        String b32 = "GEZDGNBVGY3TCMRTGQ2TMNZRGIZTINJWG4======";//base32 of key
        if(argc==2){
            Base32 base32 = new Base32();
            base32 = base32.encodeAsString(args[1].getBytes());
            System.out.println(base32);
        }
        //b32 = toHex(b32);
        //System.out.println("hextest: " + b32);
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" +
                "313233343536373839303132";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "31323334";
        if(args[0].equalsIgnoreCase("--get-otp")) {
            // Seed for HMAC-SHA1 - 20 bytes

            long T0 = 0;
            long X = 30;

            String steps = "0";
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = new Date();
            System.out.println("Date: " + date);
            long T1 = (long) date.getTime() - T0;
            T1 = T1 / 1000;
            long T2 = T1;
            T1 = T1 / X;
            long testTime[] = {T2, 1111111109L, 1111111111L,
                    1234567890L, 2000000000L, 20000000000L};
            steps = Long.toHexString(T1).toUpperCase();
            while (steps.length() < 16) steps = "0" + steps;
            System.out.println(steps);
            System.out.println(TOTP.generateTOTP(seed, steps, "6", "HmacSHA1"));
        } else if(args[0].equalsIgnoreCase("--generate-qr")){
            String uri = "https://chart.googleapis.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/Example:alice@google.com?secret="+ b32 + "&issuer=Example";
            try {
                URL url = new URL(uri);
                InputStream in = new BufferedInputStream(url.openStream());
                OutputStream out = new BufferedOutputStream(new FileOutputStream("qrCode.jpg"));

                for (int i; (i = in.read()) != -1; ) {
                    out.write(i);
                }
                in.close();
                out.close();
            }catch(java.net.MalformedURLException e){
                System.out.println("doing nothing");
            }catch(java.io.IOException i){
                System.out.println("doing nothing");
            }
        } else {
            System.out.println("please either enter --generate-qr or --get-otp as an argument");
        }
        /*
        try {
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");
            System.out.println(
                    "|  Time(sec)    |   Time (UTC format)   " +
                            "| Value of T(Hex)  |  TOTP  | Mode   |");
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");

            for (int i=0; i<testTime.length; i++) {
                long T = (testTime[i] - T0)/X;
                steps = Long.toHexString(T).toUpperCase();
                while (steps.length() < 16) steps = "0" + steps;
                String fmtTime = String.format("%1$-11s", testTime[i]);
                String utcTime = df.format(new Date(testTime[i]*1000));
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(TOTP.generateTOTP(seed, steps, "6",
                        "HmacSHA1") + "| SHA1   |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(TOTP.generateTOTP(seed32, steps, "6",
                        "HmacSHA256") + "| SHA256 |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(TOTP.generateTOTP(seed64, steps, "6",
                        "HmacSHA512") + "| SHA512 |");

                System.out.println(
                        "+---------------+-----------------------+" +
                                "------------------+--------+--------+");
            }
        }catch (final Exception e){
            System.out.println("Error : " + e);
        }
    */
    }
}
