package com.pmcc.soft.core.utils;
/**
 * md5加密工具类
 * @date 2011-06-07
 * @author wangxx
 */
public class EncryptMD5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(EncryptMD5.getMD5("123456".getBytes()));
	}
	/**
	 * md5的加密方法
	 * @date 2011-06-07
	 * @author wangxx
	 * @param source 需要加密的参数
	 * @return 加密后的字符串
	 */
	public static String getMD5(byte[] source) {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			byte tmp[] = md.digest(); 
			// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
			char str[] = new char[16 * 2]; 
			int k = 0; // 表示转换结果中对应的字符位置
			// 从第一个字节开始，对 MD5 的每一个字节,转换成 16 进制字符的转换
			for (int i = 0; i < 16; i++) { 
				byte byte0 = tmp[i]; // 取第 i 个字节
				// 取字节中高 4 位的数字转换,>>>为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
