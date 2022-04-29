package examUI;

import java.util.Random;

public class RandomNum {//生成不重复的随机数，随机答题时从题库中选择这些题号
	public int[] getRand(int n,int m) {//范围为0-n，包括0，不包括n，生成m个

		Random rand = new Random();
		boolean[]  bool = new boolean[n];//记录某个随机数是否已经生成，避免重复
		int [] arr = new int [m];//记录生产的随机数，返回
		int randInt = 0;

		for(int i = 0; i < m ; i++) {
			do {
				randInt  = rand.nextInt(n);
				}while(bool[randInt]);
			bool[randInt] = true;//true代表已经生成了				
			arr[i]=randInt;
		}
		return arr;
	}
}
