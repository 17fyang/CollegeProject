package multiThread.ProducerConsumer;
/*
 * ���߳̾��䰸��������������������
 * ������һ���������������޵ĳ��ӣ��������ˣ�һ���������ߣ���һ���������ߡ���Ҫ��������������
 * 1�������߲�����Դ����������ӣ�ǰ���ǳ���û����������������ˣ�����������ͣ������ֱ���Լ��������ܷ��³��ӡ�
 * 2�����������ĳ��������Դ��ǰ���ǳ��ӵ���Դ��Ϊ�գ�������������ͣ���ģ�����ȴ�ֱ������������Դ�������Լ�������
 */
public class demo {
	public static void main(String[] args) {
		EnterPot  pot=new EnterPot();
		Producer p=new Producer(pot);
		Consumer c=new Consumer(pot);
		p.start();
		c.start();
	}
}
