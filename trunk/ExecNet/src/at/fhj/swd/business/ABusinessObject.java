package at.fhj.swd.business;

import org.apache.log4j.Logger;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;


public class ABusinessObject {
	  
	private IRuntimeContext _rt;
	protected static final Logger logger = Logger.getLogger("BusinessLayer");


	public IRuntimeContext getRuntimeContext() {
		return _rt;
	}

	public void setRuntimeContext(IRuntimeContext _rt) {
		this._rt = _rt;
	}
	
	public ABusinessObject (){
		this._rt = Application.getInstance().getRuntime();
	}
	
	  
}
