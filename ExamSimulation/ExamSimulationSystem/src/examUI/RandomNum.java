package examUI;

import java.util.Random;

public class RandomNum {//���ɲ��ظ�����������������ʱ�������ѡ����Щ���
	public int[] getRand(int n,int m) {//��ΧΪ0-n������0��������n������m��

		Random rand = new Random();
		boolean[]  bool = new boolean[n];//��¼ĳ��������Ƿ��Ѿ����ɣ������ظ�
		int [] arr = new int [m];//��¼�����������������
		int randInt = 0;

		for(int i = 0; i < m ; i++) {
			do {
				randInt  = rand.nextInt(n);
				}while(bool[randInt]);
			bool[randInt] = true;//true�����Ѿ�������				
			arr[i]=randInt;
		}
		return arr;
	}
}
