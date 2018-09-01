package AutomatedProcesses.Common.doEncryptNotif;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import com.ingrian.security.nae.IngrianProvider;
import com.ingrian.security.nae.NAEKey;
import com.ingrian.security.nae.NAEPublicKey;
import com.ingrian.security.nae.NAESession;
public class doEncryptNotifEncryptData<N>{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected String in_data = "";
	protected String in_keyName = "";
	protected String in_algName = "";
	protected String in_login = "";
	protected String in_passwd = "";
	protected byte[] out_var_1 = null;
	GetModuleProperty<N> obj = null;
	public String getin_data() {
		return in_data;
	}
	public void setin_data(String val) {
		in_data = val;
	}
	public String getin_keyName() {
		return in_keyName;
	}
	public void setin_keyName(String val) {
		in_keyName = val;
	}
	public String getin_algName() {
		return in_algName;
	}
	public void setin_algName(String val) {
		in_algName = val;
	}
	public String getin_login() {
		return in_login;
	}
	public void setin_login(String val) {
		in_login = val;
	}
	public String getin_passwd() {
		return in_passwd;
	}
	public void setin_passwd(String val) {
		in_passwd = val;
	}
	public byte[] getout_var_1() {
		return out_var_1;
	}
	public void setout_var_1(byte[] val) {
		out_var_1 = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/
	public doEncryptNotifEncryptData() {
		obj = new GetModuleProperty<N>();
	}
	public void invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : String in_data
	In  : String in_keyName
	In  : String in_algName
	In  : String in_login
	In  : String in_passwd
	Out : byte[] out_var_1
* Available Variables: DO NOT MODIFY *****/


try
{
       // create NAE session using the user name and
        // password passed in as parameters.
       NAESession session = null;

	String safeNetPwd = obj.getModulePropertyValue("SafeNet/NotifConfig/password");
    //   String safeNetPwd = com.tibco.pe.plugin.PluginProperties.getProperty("tibco.clientVar.SafeNet/NotifConfig/password");
	//System.out.println("PASSWORD \"" + safeNetPwd + "\"");
	
	// Convert String to Char array.  String version of api call has been depricated.
	//char[] cpass = in_passwd.toCharArray();
	// Password no longer passed in.
	char[] cpass = safeNetPwd.toCharArray();

        java.security.Security.addProvider(new IngrianProvider());

	session = NAESession.getSession(in_login, cpass);
	//System.out.println("-------------------------");
	//System.out.println("GetSession Done.");
	//System.out.println("-------------------------");

	//System.out.println("Encrypting....");

	String dataToEncrypt = in_data;
	//System.out.println("Data to encrypt \"" + dataToEncrypt + "\"");

	    // get RSA public key to encrypt data 
	    // (just a key handle , key data does not leave the server)
	    NAEPublicKey pubKey = NAEKey.getPublicKey(in_keyName, session );
	    
	     // get a cipher
	    Cipher cipher = Cipher.getInstance(in_algName, "IngrianProvider");

// added today
	    String safeNetIvspec = "1234567812345678";
	    byte[] iv = safeNetIvspec.getBytes ();
            IvParameterSpec cc_ivSpec = new IvParameterSpec (iv);
// end of add

	    // initialize cipher to encrypt.
	    cipher.init(Cipher.ENCRYPT_MODE, pubKey, cc_ivSpec);

	    // encrypt data
	    byte[] outbuf = cipher.doFinal(dataToEncrypt.getBytes());

	    //System.out.println("Encrypted data  \"" + new String(outbuf) + "\"");
/*
	if (session.isClosed()) {
		System.out.println("Session is currently Closed");
	}
	else {
		System.out.println("Session is currently Open");
	}

	System.out.println("Closing Session");
*/
	session.closeSession();
/*
	if (session.isClosed()) {
		System.out.println("Session Closed");
	}
	else {
		System.out.println("Session Open");
	}
*/
        out_var_1 = outbuf;

} catch (Exception e) {
	    System.out.println("-------------------------");
	    System.out.println("doEncryptNotif ERROR: " + e.getMessage() + ".");
	    System.out.println("-------------------------");
	    throw e;
} 
}
}
