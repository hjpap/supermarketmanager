package cn.com.wei.tools;

public class IsNumerberTools {
	
	/*�ж��ַ����Ƿ������ֵķ���*/
	 public static boolean isNumeric(String str){
		for (int i = str.length(); --i >= 0;) {
			int count=0;
			if(str.charAt(i)=='.'){
				count+=count;
			}else if (!Character.isDigit(str.charAt(i))) {
				return false;
			}else if(count>1){
				return false;
			}
		}
		return true;
	}
}
