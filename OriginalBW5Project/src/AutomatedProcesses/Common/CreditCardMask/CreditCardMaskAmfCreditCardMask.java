package AutomatedProcesses.Common.CreditCardMask;
import java.util.*;
import java.io.*;
public class CreditCardMaskAmfCreditCardMask{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected String amfData = "";
	protected String amfTag = "";
	protected String newValue = "";
	protected String out_var_1 = "";
	public String getamfData() {
		return amfData;
	}
	public void setamfData(String val) {
		amfData = val;
	}
	public String getamfTag() {
		return amfTag;
	}
	public void setamfTag(String val) {
		amfTag = val;
	}
	public String getnewValue() {
		return newValue;
	}
	public void setnewValue(String val) {
		newValue = val;
	}
	public String getout_var_1() {
		return out_var_1;
	}
	public void setout_var_1(String val) {
		out_var_1 = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/
	public CreditCardMaskAmfCreditCardMask() {
	}
	public static String getAmfField(String field, String message) {
		String data = null;
		if (null != field && null != message) {
			int index1 = message.indexOf("|" + field);
			if (index1 > 0) {
				int index2 = message.indexOf("|", index1 + 1);
				if (index2 > 0) {
					data = message.substring(index1 + 1 + field.length(),
							index2);
				}
			}
		}

		return (data);
	}

	public static String setAmfField(String field, String message,
			String newData) {
		if (null == message) {
			return (null);
		}
		if (null == field) {
			return (message);
		}

		int index1 = message.indexOf("|" + field);
		int index2 = 0;
		// Keep looping until we don't find anymore of this field
		while (index1 > 0) {
			index2 = message.indexOf("|", index1 + 1);
			if (index2 > 0) {
				message = message.substring(0, index1 + 4) + newData
						+ message.substring(index2);
			}
			// set the starting index one character after the end pipe of the last find
			index1 = message.indexOf("|" + field, index2+1);
		}
		return (message);
	}

	public static String maskCreditCard(String messageIn, String amfTag, String newValue) {
		String messageOut = messageIn;
		String ccnum = getAmfField(amfTag, messageIn);
		// Default the mask if not supplied.
		if (newValue == "") {
			newValue = "****************";
		}
		if (null != ccnum) {
			if (ccnum.length() > 6) {
				//ccnum = ccnum.substring(0, 6) + "XXXXXXXX";
				ccnum = newValue;
			}
			// this means we've found a credit card number
			messageOut = setAmfField(amfTag, messageIn, ccnum);
			//messageOut = setAmfField("EXP", messageOut, "XXXX");

		}
		return (messageOut);
	}
	public void invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : String amfData
	In  : String amfTag
	In  : String newValue
	Out : String out_var_1
* Available Variables: DO NOT MODIFY *****/
try {

	// newValue can contain a Mask or be a valid credit card number 
	// Leaving newValue blank will default to a mask of asterisks
	out_var_1 = maskCreditCard(amfData, amfTag, newValue);

}
catch (Exception e) {
	    System.out.println("-------------------------");
	    System.out.println("AMFCreditCardMask ERROR: " + e.getMessage() + ".");
	    System.out.println("-------------------------");
	    throw e;
} }
}
