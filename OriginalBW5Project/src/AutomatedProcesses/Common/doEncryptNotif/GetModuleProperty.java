package AutomatedProcesses.Common.doEncryptNotif;

import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;

public class GetModuleProperty<N> {

	private ActivityContext<N> activityContext;
	private String outputStrMP= null;

	public String getModulePropertyValue(String moduleProperty) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException  
	{
		this.activityContext.registerModuleProperty(moduleProperty);
		this.outputStrMP = this.activityContext.getModuleProperty(moduleProperty);

		return outputStrMP;
	}


	@BWActivityContext
	public void setActivityContext(ActivityContext<N> act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		this.activityContext =  act;
	}
	
}
