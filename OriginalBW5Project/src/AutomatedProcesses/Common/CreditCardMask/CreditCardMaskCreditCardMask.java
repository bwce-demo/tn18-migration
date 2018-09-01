package AutomatedProcesses.Common.CreditCardMask;
import java.util.*;
import java.io.*;
public class CreditCardMaskCreditCardMask{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected String xmlData = "";
	protected String xmlElementName = "";
	protected String xmlAttributeName = "";
	protected String newvalue = "";
	protected String out_var_1 = "";
	public String getxmlData() {
		return xmlData;
	}
	public void setxmlData(String val) {
		xmlData = val;
	}
	public String getxmlElementName() {
		return xmlElementName;
	}
	public void setxmlElementName(String val) {
		xmlElementName = val;
	}
	public String getxmlAttributeName() {
		return xmlAttributeName;
	}
	public void setxmlAttributeName(String val) {
		xmlAttributeName = val;
	}
	public String getnewvalue() {
		return newvalue;
	}
	public void setnewvalue(String val) {
		newvalue = val;
	}
	public String getout_var_1() {
		return out_var_1;
	}
	public void setout_var_1(String val) {
		out_var_1 = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/
	public CreditCardMaskCreditCardMask() {
	}
	public void invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : String xmlData
	In  : String xmlElementName
	In  : String xmlAttributeName
	In  : String newvalue
	Out : String out_var_1
* Available Variables: DO NOT MODIFY *****/
    final String aInput;
    final String aOldPattern;
    final String aNewPattern;
    final String aDefaultMask =  "****************";

    String aEndTag;
    String aBeginTag;
    int iEndTagLen;
    int iOldPLen;

try {

    /*
	Set some variables based on whether we are dealing with an XML
	attribute or element.
    */
    if (xmlElementName != "") {
	//<creditCardNumber>1234093524934032<ns1:creditCardNumber>
        aBeginTag = "<";
        aEndTag = ">";
    	aOldPattern = xmlElementName +aEndTag;
	// New value blank, default to asterisks
	if (newvalue == "") {
    		aNewPattern = aDefaultMask;
	}
	else {
		aNewPattern = newvalue;
	}
    }
    else {
	// CardNumber="1234567890120008" 
        aBeginTag = " ";
        aEndTag = "=\"";
	aOldPattern = xmlAttributeName + aEndTag;
	// New value blank, default to asterisks
	if (newvalue == "") {
		// Need to include end quote for Attributes to pattern
    		aNewPattern = aDefaultMask + "\"";
	}
	else {
    		aNewPattern = newvalue + "\"";
	}
    }

    iEndTagLen = aEndTag.length();
    iOldPLen = aOldPattern.length();

    aInput = xmlData;

     final StringBuffer result = new StringBuffer();
     //startIdx and idxOld delimit various chunks of aInput; these
     //chunks always end where aOldPattern begins
     int startIdx = 0;
     int idxOld = 0;
     while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
       		//grab a part of aInput which does not include aOldPattern
       		result.append( aInput.substring(startIdx, idxOld) );
      		 //add aNewPattern to take place of aOldPattern
		//result.append("creditCardNumber>");
		result.append(aOldPattern);
       		result.append( aNewPattern );
      		startIdx = idxOld + iOldPLen;
 		// look for end tag
		startIdx = aInput.indexOf(aBeginTag, startIdx);
		idxOld = aInput.indexOf(aEndTag, startIdx);
		// only need this logic for Elements due to the face that they end with a "close" tag.
		// attribute data ends with a double-quote.
    		if (xmlElementName != "") {
       			result.append( aInput.substring(startIdx, (idxOld + iEndTagLen)) );
     			//reset the startIdx to just after the current match, to see if there are any further matches
      			startIdx = idxOld + iEndTagLen;
		}
     }

     	//the final chunk will go to the end of aInput
     	result.append( aInput.substring(startIdx) );

	out_var_1 = result.toString();

}
catch (Exception e) {
	    System.out.println("-------------------------");
	    System.out.println("CreditCardMask ERROR: " + e.getMessage() + ".");
	    System.out.println("-------------------------");
	    throw e;
} }
}
