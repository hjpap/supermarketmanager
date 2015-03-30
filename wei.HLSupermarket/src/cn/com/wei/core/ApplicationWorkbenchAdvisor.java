package cn.com.wei.core;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.wei.shell.LoginShell;
import cn.com.wei.view.Cashier;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "wei.HLSupermarket.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        LoginShell.start();
        
        if(LoginShell.isLogin()){
        	return new ApplicationWorkbenchWindowAdvisor(configurer);
        }else{
        	System.exit(0);
        	return null;
        }
    	/*return new ApplicationWorkbenchWindowAdvisor(configurer);*/
		
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
