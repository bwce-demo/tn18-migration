package AutomatedProcesses.Common.doEncryptNotif;
import java.util.*;
import java.io.*;
import java.lang.*;
public class doEncryptNotifdeEncode{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected byte[] in_data = null;
	protected String out_data = "";
	public byte[] getin_data() {
		return in_data;
	}
	public void setin_data(byte[] val) {
		in_data = val;
	}
	public String getout_data() {
		return out_data;
	}
	public void setout_data(String val) {
		out_data = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/
	public doEncryptNotifdeEncode() {
	}
protected static int toDigit(char ch, int index) throws RuntimeException {
            int digit = Character.digit(ch, 16);
            if (digit == -1) {
                  throw new RuntimeException("Illegal hexadecimal charcter " + ch + " at index " + index);
            }
            return digit;
      }

public static byte[] decodeHex(String dataString) throws RuntimeException {

	    char[] data = dataString.toCharArray();
            int len = data.length;

            if ((len & 0x01) != 0) {
                  throw new RuntimeException("Odd number of characters.");
            }

            byte[] out = new byte[len >> 1];

            // two characters form the hex value.
            for (int i = 0, j = 0; j < len; i++) {
                  int f = toDigit(data[j], j) << 4;
                  j++;
                  f = f | toDigit(data[j], j);
                  j++;
                  out[i] = (byte) (f & 0xFF);
            }

            return out;
      }

	public void invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : byte[] in_data
	Out : String out_data
* Available Variables: DO NOT MODIFY *****/
	char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	 
try
{
           int l = in_data.length;

           char[] out = new char[l << 1];

           // two characters form the hex value.
           for (int i = 0, j = 0; i < l; i++) {
             out[j++] = DIGITS[(0xF0 & in_data[i]) >>> 4];
             out[j++] = DIGITS[0x0F & in_data[i]];
           }

	out_data =  new String(out);

} catch (Exception e) {
	    System.out.println("-------------------------");
	    System.out.println("doEncryptNotif ERROR: " + e.getMessage() + ".");
	    System.out.println("-------------------------");
	    throw e;
} 

	}
}
	


