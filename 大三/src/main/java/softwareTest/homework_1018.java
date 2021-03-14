package softwareTest;

public class homework_1018 {
	public static void main(String[] args) {
		
	}
	
	//投保人测试用例
	public static void work1() {
		int age[]= {6,4,2};
		int gender[]= {5,3};
		int marry[]= {5,3};
		int number[]={-1,-2,-3};
		
		String ageString[]= {"20-39","40-59","其他"};
		String genderString[]= {"男","女"};
		String marryString[]= {"已婚","未婚"};
		String numberString[]= {"1-2人","3-4人","五人及以上"};
		
		for(int i1=0;i1<3;i1++) {
			for(int i2=0;i2<2;i2++) {
				for(int i3=0;i3<2;i3++) {
					for(int i4=0;i4<3;i4++) {
						int result=age[i1]+gender[i2]+marry[i3]+number[i4];
						System.out.println(ageString[i1]+"   "+genderString[i2]+"   "+marryString[i3]+"   "+numberString[i4]+": "+result);
					}
				}
			}
		}
	}
}
