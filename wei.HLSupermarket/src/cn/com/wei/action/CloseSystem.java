package cn.com.wei.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

public class CloseSystem extends Action {
	  private IWorkbenchWindow window;

			public CloseSystem(IWorkbenchWindow window) {
				this.window = window;
			}
	         public void run(){
	        	 window.close();
	         }

}
