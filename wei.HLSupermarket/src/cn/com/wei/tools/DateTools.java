package cn.com.wei.tools;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



/**
 * @author 
 */
public class DateTools {

	public static final String SIGN = "-";
	public static final String SP = "";
	/**
	 * ������ʱ
	 * @return String ���͵�ʱ��
	 */
	public static String getDateTimeToHour() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
		return sf.format(date);
	}
	/**
	 * ��
	 * @return String ���͵�ʱ��
	 */
	public static String getDateYear() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		return sf.format(date);
	}
	/**
	 * ��
	 * @return String ���͵�ʱ��
	 */
	public static String getDateMonth() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MM");
		return sf.format(date);
	}
	/**
	 * ��
	 * @return String ���͵�ʱ��
	 */
	public static String getDateDay() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd");
		return sf.format(date);
	}
	
	/**
	 * ������ʱ����
	 * @return String ���͵�ʱ��
	 */
	public static String getDateTimeToSecond() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	
	/**
	 * ������ʱ����
	 * @return String ���͵�ʱ��
	 */
	public static String getDateTimeBillcode() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd-ss");
		return sf.format(date);
	}
	
	/**
	 * ������
	 * @return String ���͵�ʱ��
	 */
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}
	/**
	 * ����
	 * @return String ���͵�ʱ��
	 */
	public static String getMonthDay() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		return sf.format(date);
	}
	

	/**
	 * ����32λ����
	 * @return String  code
	 * */
	public static synchronized String getPk() {
		String code = null;
		try {
			code = UUID.randomUUID().toString();
			code = code.replaceAll(SIGN, SP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	public static void main(String[] args) {
		System.out.println(DateTools.getDateTime());
		System.out.println(DateTools.getDateTimeToHour());
		System.out.println(DateTools.getDateTimeToSecond());
		System.out.println(DateTools.getPk());
	}
	
}
