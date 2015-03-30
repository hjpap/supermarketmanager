package cn.com.wei.editor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.wei.shell.AlterBuyCount;
import cn.com.wei.shell.FinishSale;
import cn.com.wei.shell.LoginShell;
import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;

public class Guadan extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Guadan"; //$NON-NLS-1$
	private Text bianhaot;
	private Table table;
	private Text text_1;
	private Text vipt;
	private Text vipleixingt;
	private Text vipzhekout;
	private Text text;
	private Text text_3;
	private Label moneyLabel;
	private Label countLabel;
	private Label yuanjiaLabel;
	private Label birthdayLabel;
	
	private double zhekou=1;//折扣
	private String vipcode;
	private boolean isvip=false;

	
	private String billcode;// 订单号 /账单流水号
	private String loginUser = LoginShell.getLoginUsername();// 获取登录的用户名
	

	MessageBox m = new MessageBox(new Shell(), SWT.ICON_INFORMATION);

	private Text vipnamet;
	private Text viphuodongt;
	private Text text_2;
	private Text text_4;
	
	public Guadan() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		container.setBackgroundMode(SWT.INHERIT_FORCE);

		Group group = new Group(container, SWT.NONE);
		group.setBounds(10, 82, 880, 72);

		Label label = new Label(group, SWT.NONE);
		label.setBounds(24, 30, 101, 21);
		label.setText("\u5546\u54C1\u7F16\u53F7/\u6761\u5F62\u7801\uFF1A");

		bianhaot = new Text(group, SWT.BORDER);
		bianhaot.setFocus();
		

		bianhaot.setBounds(131, 27, 221, 23);

		Label label_10 = new Label(group, SWT.NONE);
		label_10.setBounds(640, 30, 72, 17);
		label_10.setText("\u8D26\u5355\u6D41\u6C34\u53F7\uFF1A");

		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(718, 27, 152, 23);
		orderCode() ;//产生订单号方法
		text.setText(billcode);

		Label label_11 = new Label(group, SWT.NONE);
		label_11.setBounds(372, 30, 42, 17);
		label_11.setText("\u6570\u91CF\uFF1A");

		text_3 = new Text(group, SWT.BORDER);
		text_3.addKeyListener(new KeyAdapter() {
			//数量框 回车 按钮
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					if (bianhaot.getText().equals("")
							|| text_3.getText().equals("")) {
						m.setMessage("请正确输入商品号和数量！");
						m.open();
						// 还可以加其他输入限制条件
					} else {
						querySp();
						
						showTable();
						
						showTotal();
					}

				}
				
			}
		});
		text_3.setText("1");
		text_3.setBounds(417, 27, 73, 23);

		Button button = new Button(group, SWT.NONE);

		button.setBounds(504, 25, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Label label_15 = new Label(container, SWT.NONE);
		label_15.setImage(SWTResourceManager.getImage(Guadan.class, "/img/jiezhangtitle.png"));
		label_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_15.setBounds(33, 0, 80, 82);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 160, 880, 234);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(142);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7/\u6761\u5F62\u7801");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(142);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(111);
		tableColumn_2.setText("\u51FA\u552E\u4EF7");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(111);
		tblclmnNewColumn.setText("\u6253\u6298\u7387\uFF08%\uFF09");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(111);
		tableColumn_3.setText("\u6570\u91CF");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(111);
		tableColumn_4.setText("\u6838\u7B97");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(111);
		tableColumn_5.setText("\u4E0A\u67B6\u6570");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("\u4FEE\u6539\u6570\u91CF");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		
		menuItem_1.setText("\u5220\u9664\u5546\u54C1");

		Group group_2 = new Group(container, SWT.NONE);
		group_2.setBounds(398, 555, 492, 112);

		Label label_5 = new Label(group_2, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_5.setBounds(10, 31, 90, 28);
		label_5.setText("\u5B9E\u6536\u91D1\u989D\uFF1A");

		text_1 = new Text(group_2, SWT.BORDER);
		
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_1.setBounds(10, 65, 145, 37);

		Button button_1 = new Button(group_2, SWT.NONE);
		
		button_1.setBounds(190, 66, 90, 38);
		button_1.setText("\u7ED3\u8D26");

		Button button_4 = new Button(group_2, SWT.NONE);

		button_4.setBounds(286, 66, 90, 38);
		button_4.setText("\u91CD\u7F6E");
		
		Button btnNewButton = new Button(group_2, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//退出
			public void widgetSelected(SelectionEvent e) {
				//关闭editor
				getSite().getWorkbenchWindow().getActivePage().closeEditor(Guadan.this, true);
			}
		});
		btnNewButton.setBounds(382, 66, 90, 38);
		btnNewButton.setText("\u9000\u51FA");

		Group group_3 = new Group(container, SWT.NONE);
		group_3.setBounds(10, 400, 880, 112);

		Label lblid = new Label(group_3, SWT.NONE);
		lblid.setBounds(28, 31, 43, 17);
		lblid.setText("\u4F1A\u5458ID:");

		vipt = new Text(group_3, SWT.BORDER);
		
		vipt.setBounds(90, 28, 127, 23);

		Label label_7 = new Label(group_3, SWT.NONE);
		label_7.setBounds(557, 29, 58, 17);
		label_7.setText("\u4F1A\u5458\u7C7B\u578B\uFF1A");

		vipleixingt = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		vipleixingt.setBounds(621, 26, 73, 23);

		Label label_9 = new Label(group_3, SWT.NONE);
		label_9.setBounds(713, 27, 48, 17);
		label_9.setText("\u6298\u6263\u7387\uFF1A");

		vipzhekout = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		vipzhekout.setBounds(767, 26, 73, 23);

		Button button_3 = new Button(group_3, SWT.NONE);
		
		button_3.setBounds(223, 26, 80, 27);
		button_3.setText("\u786E\u5B9A");

		Label label_8 = new Label(group_3, SWT.NONE);
		label_8.setBounds(371, 29, 61, 17);
		label_8.setText("\u4F1A\u5458\u59D3\u540D\uFF1A");

		vipnamet = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		vipnamet.setBounds(438, 26, 95, 23);

		viphuodongt = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		viphuodongt.setBounds(90, 83, 127, 23);
		
		Label label_6 = new Label(group_3, SWT.NONE);
		label_6.setBounds(554, 84, 61, 17);
		label_6.setText("\u6D88\u8D39\u6B21\u6570\uFF1A");

		Label label_12 = new Label(group_3, SWT.NONE);
		label_12.setBounds(28, 86, 58, 17);
		label_12.setText("\u4F1A\u5458\u5907\u6CE8:");
		
		birthdayLabel = new Label(group_3, SWT.NONE);
		birthdayLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		birthdayLabel.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		birthdayLabel.setBounds(240, 71, 293, 36);
		
		Label label_13 = new Label(group_3, SWT.NONE);
		label_13.setBounds(702, 83, 61, 17);
		label_13.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");
		
		text_2 = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(621, 81, 73, 23);
		
		text_4 = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBounds(769, 81, 73, 23);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		lblNewLabel.setText("\u6302\u5355-\u65B0\u7ED3\u8D26\u5355");
		lblNewLabel.setBounds(131, 20, 206, 45);
		
		Label label_14 = new Label(container, SWT.NONE);
		label_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_14.setBounds(0, 0, 949, 82);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBounds(10, 518, 382, 149);
		
				Label label_1 = new Label(composite, SWT.NONE);
				label_1.setLocation(38, 10);
				label_1.setSize(72, 54);
				label_1.setFont(SWTResourceManager.getFont("微软雅黑", 26, SWT.BOLD));
				label_1.setText("\u552E\uFF1A");
				
						moneyLabel = new Label(composite, SWT.NONE);
						moneyLabel.setLocation(114, 10);
						moneyLabel.setSize(133, 54);
						moneyLabel.setFont(SWTResourceManager.getFont("微软雅黑", 23, SWT.BOLD));
						
								Label label_2 = new Label(composite, SWT.NONE);
								label_2.setLocation(249, 20);
								label_2.setSize(80, 40);
								label_2.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
								label_2.setText("\u5143");
								
								Group group_4 = new Group(composite, SWT.NONE);
								group_4.setLocation(10, 70);
								group_4.setSize(362, 69);
								
										Label label_3 = new Label(group_4, SWT.NONE);
										label_3.setLocation(30, 22);
										label_3.setSize(85, 24);
										label_3.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
										label_3.setText("\u5546\u54C1\u6570\u91CF\uFF1A");
										
												countLabel = new Label(group_4, SWT.NONE);
												countLabel.setLocation(121, 22);
												countLabel.setSize(66, 24);
												countLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
												
														Label label_4 = new Label(group_4, SWT.NONE);
														label_4.setLocation(212, 22);
														label_4.setSize(54, 24);
														label_4.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
														label_4.setText("\u539F\u4EF7\uFF1A");
														
																yuanjiaLabel = new Label(group_4, SWT.NONE);
																yuanjiaLabel.setLocation(272, 22);
																yuanjiaLabel.setSize(87, 24);
																yuanjiaLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
		
	
		
		
		
		button_1.addSelectionListener(new SelectionAdapter() {
			//结账 确定 按钮
			public void widgetSelected(SelectionEvent e) {
				/*实现功能 1.讲账单添加进销售明细 和销售账单表 addBill()  2上架数量相应减少 updateGrounding()
				3如果是会员 会员消费额增加 消费次数增加 （自动判断能否升级）--updateVip()   4收银员业绩增加updateShou()
				5 清除收银表和挂单表中的信息
				*/
				JDBCTools jt=new JDBCTools();
				String sql1="select * from cashier2";
				ResultSet rs=jt.query(sql1);
				try {
					
					if(rs.absolute(1)){
						if(IsNumerberTools.isNumeric(text_1.getText())&&text_1.getText()!=""){
							if(Double.parseDouble(text_1.getText())>=Double.parseDouble(moneyLabel.getText())){
								
									//计算找零
								double zhaoling=(Double.parseDouble(text_1.getText())-Double.parseDouble(moneyLabel.getText()));
									
								
								addBill();
								updateGrounding();
								updateVip();
								updateVipKind();//会员升级
								updateShou();
							
								
								FinishSale.start(text_1.getText(),Double.toString(zhaoling),billcode);
								//m.setMessage(" 实收金额："+text_1.getText()+"元\n 找零："+Double.toString(zhaoling)+"元\n 小票号码："+billcode+"\n 结账成功!");
								//m.open();
								
								
								// 清空table
								text_1.setText("");
								table.removeAll();
								//产生新的订单流水号
								orderCode() ;
								text.setText(billcode);
								
								// 统计项显示初始化
								moneyLabel.setText("");
								yuanjiaLabel.setText("");
								countLabel.setText("");
								//会员信息清空
								isvip=false;
								vipcode="";
								zhekou=1;
								vipnamet.setText("");
								vipleixingt.setText("");
								vipzhekout.setText("");
								viphuodongt.setText("");
								vipt.setText("");
								birthdayLabel.setText("");
								text_2.setText("");
								text_4.setText("");
								//清空数据库
								
								String sql="DELETE  FROM cashier2";
								jt.update(sql);
								
								getSite().getWorkbenchWindow().getActivePage().closeEditor(Guadan.this, true);
							}else{
								m.setMessage("请正确输入金额！");
								m.open();	
							}
						}else{
							m.setMessage("请正确输入金额！");
							m.open();
						}
						
					}else{
						m.setMessage("还没有录入商品信息，不能结账！");
						m.open();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				jt.close(rs);
			}
		});
		
		
		bianhaot.addKeyListener(new KeyAdapter() {
			// 条形码 输入框 按回车
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					text_3.setFocus();
				}else if(e.keyCode==16777218){
					vipt.setFocus();
				}
			}
		});
		
		
		menuItem.addSelectionListener(new SelectionAdapter() {
			//table右键 修改数量 按钮
			public void widgetSelected(SelectionEvent e) {
				AlterBuyCount.start(table.getSelection()[0]);
				//System.out.println(table.getSelection()[0].getText(0));
				moneyLabel.setText("");
				countLabel.setText("");
				yuanjiaLabel.setText("");
				showTable();
				showTotal();
			}
		});
		
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			//table鼠标右键 删除按钮
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				String sql="delete from cashier2 where code='"+table.getSelection()[0].getText()+"'";
				jt.update(sql);
				jt.close(null);
				
				showTable();
				showTotal();
				
			}
		});
		
		button_4.addSelectionListener(new SelectionAdapter() {
			// 重置按钮
			public void widgetSelected(SelectionEvent e) {
				// 清空table
				table.removeAll();
				
				// 统计项显示初始化
				moneyLabel.setText("");
				yuanjiaLabel.setText("");
				countLabel.setText("");
				//会员信息清空
				isvip=false;
				vipcode="";
				zhekou=1;
				vipnamet.setText("");
				vipleixingt.setText("");
				vipzhekout.setText("");
				viphuodongt.setText("");
				vipt.setText("");
				birthdayLabel.setText("");
				text_2.setText("");
				text_4.setText("");
				//清空数据库
				JDBCTools jt=new JDBCTools();
				String sql="DELETE  FROM cashier2";
				jt.update(sql);
				jt.close(null);

			}
		});
		
		
		button_3.addSelectionListener(new SelectionAdapter() {
			//输入会员 确定按钮  
			public void widgetSelected(SelectionEvent e) {
				isVip();
				showTable();
				showTotal();
				bianhaot.setFocus();
			}
		});
		
		vipt.addKeyListener(new KeyAdapter() {
			// 会员框 按回车
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					isVip();
					showTable();
					showTotal();
					text_1.setFocus();

				}else if(e.keyCode == 16777218){
					text_1.setFocus();
				}else if(e.keyCode==16777217){
					bianhaot.setFocus();
				}

			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			// 商品编号确定按钮
			public void widgetSelected(SelectionEvent e) {
				if (bianhaot.getText().equals("")
						|| text_3.getText().equals("")) {
					m.setMessage("请正确输入商品号和数量！");
					m.open();
					// 还可以加其他输入限制条件
				} else {
					querySp();
					
					showTable();
					
					showTotal();
				}

			}
		});

		
		text_1.addKeyListener(new KeyAdapter() {
			//回车
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					/*实现功能 1.讲账单添加进销售明细 和销售账单表 addBill()  2上架数量相应减少 updateGrounding()
					3如果是会员 会员消费额增加 消费次数增加 （自动判断能否升级）--updateVip()   4收银员业绩增加updateShou()
					5 清除收银表和挂单表中的信息
					*/
					JDBCTools jt=new JDBCTools();
					String sql1="select * from cashier2";
					ResultSet rs=jt.query(sql1);
					try {
						
						if(rs.absolute(1)){
							if(IsNumerberTools.isNumeric(text_1.getText())&&text_1.getText()!=""){
								if(Double.parseDouble(text_1.getText())>=Double.parseDouble(moneyLabel.getText())){
									
										//计算找零
									double zhaoling=(Double.parseDouble(text_1.getText())-Double.parseDouble(moneyLabel.getText()));
										
									
									addBill();
									updateGrounding();
									updateVip();
									updateVipKind();//会员升级
									updateShou();
								
									
									FinishSale.start(text_1.getText(),Double.toString(zhaoling),billcode);
									//m.setMessage(" 实收金额："+text_1.getText()+"元\n 找零："+Double.toString(zhaoling)+"元\n 小票号码："+billcode+"\n 结账成功!");
									//m.open();
									
									
									// 清空table
									text_1.setText("");
									table.removeAll();
									//产生新的订单流水号
									orderCode() ;
									text.setText(billcode);
									
									// 统计项显示初始化
									moneyLabel.setText("");
									yuanjiaLabel.setText("");
									countLabel.setText("");
									//会员信息清空
									isvip=false;
									vipcode="";
									zhekou=1;
									vipnamet.setText("");
									vipleixingt.setText("");
									vipzhekout.setText("");
									viphuodongt.setText("");
									vipt.setText("");
									birthdayLabel.setText("");
									//清空数据库
									
									String sql="DELETE  FROM cashier2";
									jt.update(sql);
									
									
								}else{
									m.setMessage("请正确输入金额！");
									m.open();	
								}
							}else{
								m.setMessage("请正确输入金额！");
								m.open();
							}
							
						}else{
							m.setMessage("还没有录入商品信息，不能结账！");
							m.open();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					jt.close(rs);
				}else if(e.keyCode==16777217){
					vipt.setFocus();
				}
				}
			
		});
		
		

	}

	
	
	public void querySp() {
		//将商品添加到收银台表格中
		// 同种商品只修改数量 不重复出现
		JDBCTools jt = new JDBCTools();

		String sql = "select * from grounding where code='"
				+ bianhaot.getText() + "'";
		ResultSet rs = jt.query(sql);
		
		try {
			if(rs.absolute(1)){
				//是否有该商品
				String sql1="select * from cashier2 where code='"+bianhaot.getText()+"'";
				ResultSet rs2=jt.query(sql1);
				 if(rs2.absolute(1)){
					 //收银台中是否有该商品--有(该商品数量编号加上新加数量)
					  if(IsNumerberTools.isNumeric(text_3.getText())){
						  if(Integer.parseInt(text_3.getText())>=0){
							  if(Integer.parseInt(text_3.getText())+rs2.getInt("count")>rs2.getInt("sjcount")){
								  //超过上架数提醒
								  m.setMessage("商品数量超过商品上架数量！");
								  m.open();
							  }else{
								  String sql2="update cashier2 set count=count+"+Integer.parseInt(text_3.getText())+",zhekou="+zhekou+",total=retail*count*zhekou,sjcount="+rs.getString("sjcount")+"  where code='"+bianhaot.getText()+"'";
									
						           jt.update(sql2); 
							  }
							   
						  }else{
							  m.setMessage("请输入正确的个数(正整数)!");
							  m.open();
						  }
						
					 }else{
						 m.setMessage("请输入数字！");
						 m.open();
					 }
					 
					
				 }else{
					 //收银台中没有该商品（增加该商品到收银表）
					 if(IsNumerberTools.isNumeric(text_3.getText())){
						  if(Integer.parseInt(text_3.getText())>0){
							  if(Integer.parseInt(text_3.getText())<=rs.getInt("sjcount")){
								  String sql3="insert into cashier2 values('"+rs.getString("code")+"','"+rs.getString("name")+"',"+rs.getDouble("retail")+","+zhekou+","+Integer.parseInt(text_3.getText())+","+(rs.getDouble("retail")*Integer.parseInt(text_3.getText())*zhekou)+","+rs.getInt("sjcount")+")";
								 jt.update(sql3);
							  }else{
								  m.setMessage("超过上架商品数量！");
								  m.open();
							  }
							  
						  }else{
							  m.setMessage("请输入正确的个数(正整数)!");
							  m.open();
						  }
						  
					 }else{
						 m.setMessage("请输入数字！");
						 m.open();
					 }
					 
					 
				 }
				 jt.close(rs2);
			}else{
				m.setMessage("没有该商品！");
				m.open();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		bianhaot.setText("");
		text_3.setText("1");
		bianhaot.setFocus();
		jt.close(rs);	
	}
	
	
	//将购买的商品信息显示在table中
		public void showTable(){
			JDBCTools jt=new JDBCTools();
			String sql="select * from cashier2";
			ResultSet rs=jt.query(sql);
			try {
				table.removeAll();
				while(rs.next()){
					TableItem tableItem=new TableItem(table,SWT.None);
					String[] temp=new String[7];
					temp[0]=rs.getString("code");
					temp[1]=rs.getString("name");
					temp[2]=rs.getString("retail");
					temp[3]=rs.getString("zhekou");
					temp[4]=rs.getString("count");
					temp[5]=rs.getString("total");
					temp[6]=rs.getString("sjcount");
					tableItem.setText(temp);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
		}
		
		
		//显示统计信息
		public void showTotal(){
			JDBCTools jt=new JDBCTools();
			String sql="select * from cashier2";
			ResultSet rs=jt.query(sql);
			
			double money=0;
			double yuanjia=0;
			int count=0;
			String mon = null;
			try {
				while(rs.next()){
					
					money=money+rs.getDouble("total");//计算应付的钱
					
					//保留两位小数显示的方法
					DecimalFormat dec = new DecimalFormat("0.##");
					mon = dec.format(money);
					//System.out.println(mon);
					
					yuanjia=yuanjia+(rs.getDouble("retail")*rs.getDouble("count"));
					count=count+rs.getInt("count");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			moneyLabel.setText(mon);
			yuanjiaLabel.setText(Double.toString(yuanjia));
			countLabel.setText(Integer.toString(count));
		}
		
		
		
		public void isVip() {
			// 会员登录
			if(vipt.getText().equals("")){
				m.setMessage("请输入会员ID");
				m.open();
			}else{
				JDBCTools jt=new JDBCTools();
				String sql="select * from vip where hy_code='"+vipt.getText()+"'";
				ResultSet rs=jt.query(sql);
				
				try {
					
					if(rs.absolute(1)){
						//是否有该ID信息
						String name=rs.getString("hy_name");
						String leixing=rs.getString("hy_kind");
						String zhekouj=rs.getString("hy_zhekou");
						String huodong=rs.getString("hy_bz");
						vipnamet.setText(name);
						vipleixingt.setText(leixing);
						vipzhekout.setText(zhekouj);
						viphuodongt.setText(huodong);
						text_2.setText(rs.getString("hy_count"));
						text_4.setText(rs.getString("hy_expense"));
						isvip=true;
						vipcode=vipt.getText();
						
						
						SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
						String birthday=sf.format(rs.getDate("hy_birthday"));
						
						if(birthday.equals(DateTools.getMonthDay())){
							birthdayLabel.setText("今天"+DateTools.getMonthDay()+"  是该会员生日!");
						}
						//System.out.println(birthday);
						//System.out.println(DateTools.getMonthDay());
						
					
					
						String sql1="update cashier2 set zhekou="+rs.getDouble("hy_zhekou")+",total=retail*zhekou*count";
						jt.update(sql1);
						zhekou=rs.getDouble("hy_zhekou");
						
					}else{
						m.setMessage("没有该会员信息，请重新输入会员ID!");
						m.open();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				jt.close(rs);
				
			}

		}
		
		
		
		// 产生一个订单号 方法
		public void orderCode() {
			int i=new Random().nextInt(99999);
			String j=DateTools.getDateTimeBillcode();
			String z=i+j;
			billcode=z;
			

		}
		
		// 会员消费信息更新方法(可添加自动升级)
		public void updateVip() {
			if(isvip){
				JDBCTools jt=new JDBCTools();
				String sql="UPDATE vip SET hy_expense=hy_expense+"+Double.parseDouble(moneyLabel.getText())+",hy_count=hy_count+1 WHERE hy_code='"+vipcode+"'";
				jt.update(sql);
				jt.close(null);
			}
			
			
		}
		//会员升级
		public void updateVipKind(){
			if(isvip){
				JDBCTools jt=new JDBCTools();
				String sql="select * from vip_kind";
				String sql1="select * from vip where hy_code='"+vipcode+"'";
				ResultSet rs1=jt.query(sql1);
				ResultSet rs=jt.query(sql);
				try {
					
					while(rs.next()){
						rs1.absolute(1);
						
						if(rs1.getDouble("hy_expense")>=rs.getDouble("Hcondition")){
							//System.out.println(rs1.getString("hy_expense")+"/"+rs.getDouble("Hcondition"));
							String sql2="UPDATE vip SET hy_kind='"+rs.getString("kind")+"',hy_zhekou='"+rs.getString("zhekou")+"' WHERE hy_code='"+vipcode+"'";
							jt.update(sql2);
							
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				jt.close(rs);
				jt.close(rs1);
			}
		}
		
		//更新收银员业绩
		public void updateShou(){
			JDBCTools jt=new JDBCTools();
			String sql="update staff set income=income+"+Double.parseDouble(moneyLabel.getText())+" where user_name='"+LoginShell.getLoginUsername()+"'";
			jt.update(sql);
			jt.close(null);
		}
		
		
		//账单添加进销售明细 和销售账单表 addBill()  
		public void addBill(){
			JDBCTools jt=new JDBCTools();
			//添加进销售账单表
			if(isvip){
				String sql="insert into sale_bill (billcode,isvip,cost,total,DATE) values('"+billcode+"','"+vipcode+"','"+yuanjiaLabel.getText()+"','"+moneyLabel.getText()+"','"+DateTools.getDateTime()+"')";
				 jt.update(sql);
				
			}else{
				String sql="insert into sale_bill (billcode,isvip,cost,total,DATE) values('"+billcode+"','普通顾客','"+yuanjiaLabel.getText()+"','"+moneyLabel.getText()+"','"+DateTools.getDateTime()+"')";
				jt.update(sql);
				
			}
			//添加进明细表
			String sql1="select * from cashier2";
			ResultSet rs=jt.query(sql1);
			try {
				while(rs.next()){
					String sql2="insert into sale_mingxi (code,name,retail,zhekou,count,total,date,billcode) values('"+rs.getString("code")+"','"+rs.getString("name")+"','"+rs.getString("retail")+"','"+rs.getString("zhekou")+"','"+rs.getString("count")+"','"+rs.getString("total")+"','"+DateTools.getDateTime()+"','"+billcode+"')";
					jt.update(sql2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs);
			
		}
		
		//上架数量相应减少 updateGrounding()
		public void updateGrounding(){
			JDBCTools jt=new JDBCTools();
			String sql="select * from cashier2";
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					String sql1="update grounding set sjcount=sjcount-"+rs.getString("count")+"  where code='"+rs.getString("code")+"'";
					jt.update(sql1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
		}

		
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
