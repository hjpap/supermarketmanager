package cn.com.wei.action;

import org.eclipse.jface.action.Action;

import cn.com.wei.shell.Help;

public class ShowHelp extends Action{

	@Override
	public void run() {
		Help.start();
	}

	
}
