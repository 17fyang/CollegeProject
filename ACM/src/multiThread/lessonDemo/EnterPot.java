package multiThread.lessonDemo;

public class EnterPot {
	public int product=7;
	
	public void getAndPut(int number) {
		if(product<number*-1) {
			System.out.println("��治�㣡");
			return;
		}
		
		String s="��Ʒ�����䶯��"+number+",���ʣ�ࣺ";
		this.product=this.product+number;
		System.out.println(s.concat(String.valueOf(product)));
		
//		StringBuffer s=new StringBuffer();
//		s.append("��Ʒ�����䶯��"+number+",���ʣ�ࣺ");
//		this.product=this.product+number;
//		s.append(product);
//		System.out.println(s);
		
	}
	
}
