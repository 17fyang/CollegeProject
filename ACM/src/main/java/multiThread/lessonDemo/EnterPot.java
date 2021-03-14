package multiThread.lessonDemo;

public class EnterPot {
	public int product=7;
	
	public void getAndPut(int number) {
		if(product<number*-1) {
			System.out.println("库存不足！");
			return;
		}
		
		String s="产品数量变动："+number+",库存剩余：";
		this.product=this.product+number;
		System.out.println(s.concat(String.valueOf(product)));
		
//		StringBuffer s=new StringBuffer();
//		s.append("产品数量变动："+number+",库存剩余：");
//		this.product=this.product+number;
//		s.append(product);
//		System.out.println(s);
		
	}
	
}
