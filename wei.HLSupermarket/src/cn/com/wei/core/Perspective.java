package cn.com.wei.core;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.com.wei.shell.LoginShell;
import cn.com.wei.view.Boss;
import cn.com.wei.view.Cashier;
import cn.com.wei.view.Manager;


public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		if(LoginShell.getLimits().equals("收银员")){
			layout.addView(Cashier.ID, layout.LEFT, 0.28f, layout.getEditorArea());
		}else if(LoginShell.getLimits().equals("管理员")){
			layout.addView(Manager.ID, layout.LEFT, 0.28f, layout.getEditorArea());
		}else{
			layout.addView(Boss.ID, layout.LEFT, 0.28f, layout.getEditorArea());
		}
		
	}
}
