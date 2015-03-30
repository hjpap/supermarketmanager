package cn.com.wei.core;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.com.wei.action.AlterPerson;
import cn.com.wei.action.CloseSystem;
import cn.com.wei.action.OpenBoss;
import cn.com.wei.action.OpenCashier;
import cn.com.wei.action.OpenManager;
import cn.com.wei.action.Restart;
import cn.com.wei.action.ShowHelp;
import cn.com.wei.shell.LoginShell;
import org.eclipse.wb.swt.ResourceManager;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
		
		private OpenCashier openCashier;//打开收银菜单
		private OpenManager openManager;//打开管理员菜单
		private OpenBoss openBoss;//打开经理菜单
		private CloseSystem closeSystem;//关闭系统
		private Restart reStart;//切换用户
		private AlterPerson alterPerson;//个人设置
		private ShowHelp showHelp;
		
		
		
		
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);    
    }

    protected void makeActions(IWorkbenchWindow window) {
    	openCashier=new OpenCashier(window);
    	openCashier.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun3.png"));
    	openCashier.setToolTipText("打开收银员菜单");
    	openCashier.setText("收银员菜单");
    	
    	openManager=new OpenManager(window);
    	openManager.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun2.png"));
    	openManager.setToolTipText("打开管理员菜单");
    	openManager.setText("管理员菜单");
    	
    	openBoss=new OpenBoss(window);
    	openBoss.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun1.png"));
    	openBoss.setText("经理菜单");
    	openBoss.setToolTipText("打开经理菜单");
    	
    	alterPerson=new AlterPerson();
    	alterPerson.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/shezhi.png"));
    	alterPerson.setText("个人设置");
    	alterPerson.setToolTipText("个人设置");
    	
    	reStart=new Restart(window);
    	reStart.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/qiehuanyonghu.png"));
    	reStart.setText("切换用户");
    	reStart.setToolTipText("切换用户");
    	
    	showHelp=new ShowHelp();
    	showHelp.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/help.png"));
    	showHelp.setText("帮助");
    	showHelp.setToolTipText("帮助");
    	
    	
    	closeSystem=new CloseSystem(window);
    	closeSystem.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/close.png"));
    	closeSystem.setText("退出");
    	closeSystem.setToolTipText("退出系统");
    	
    	
    	
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    }

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		if(LoginShell.getLimits().equals("收银员")){
			IToolBarManager opencashier=new ToolBarManager();
		opencashier.add(openCashier);
		coolBar.add(opencashier);
		}
		
		if(LoginShell.getLimits().equals("管理员")){
			IToolBarManager openmanager=new ToolBarManager();
		    openmanager.add(openManager);
		    coolBar.add(openmanager);
		}
		if(LoginShell.getLimits().equals("经理")){
			IToolBarManager openboss=new ToolBarManager();
		    openboss.add(openBoss);
		    coolBar.add(openboss);
		    
		    IToolBarManager opencashier=new ToolBarManager();
			opencashier.add(openCashier);
			coolBar.add(opencashier);
			
			IToolBarManager openmanager=new ToolBarManager();
		    openmanager.add(openManager);
		    coolBar.add(openmanager);
		}
		
		IToolBarManager alterperson=new ToolBarManager();
		alterperson.add(alterPerson);
		coolBar.add(alterperson);
		
		
		IToolBarManager restart=new ToolBarManager();
		restart.add(reStart);
		coolBar.add(restart);
		
		IToolBarManager showhelp=new ToolBarManager();
		showhelp.add(showHelp);
		coolBar.add(showhelp);
		
		
		IToolBarManager closesystem=new ToolBarManager();
		closesystem.add(closeSystem);
		coolBar.add(closesystem);
		
		
	}
    
}
