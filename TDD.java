public class TDD {
	
	public String input(String str){
		String[] buf = str.split(" ");
		char cBlack[][] = new char[5][2]; 
		char cWhite[][] = new char[5][2]; 
		int tmpBlack[][],tmpWhite[][];
		for(int i=0; i<5; i++){
			cBlack[i][0] = buf[i+1].charAt(0);
			cWhite[i][0] = buf[i+7].charAt(0);
			cBlack[i][1] = buf[i+1].charAt(1);
			cWhite[i][1] = buf[i+7].charAt(1);
		}
		tmpBlack = ctoi(cBlack);
		tmpWhite = ctoi(cWhite);
		
		int rank_b = rank(tmpBlack);
		int rank_w = rank(tmpWhite);
		if(rank_b > rank_w)return "Black win!";
		else if(rank_b < rank_w)return "White win!";
		else{
			int ans = equal_rank(tmpBlack, tmpWhite, rank_w);
			if(ans == 1)return "Black win!";
			else if(ans == -1)return "White win!";
			else return "Tie!";
		}
	}
	int[][] ctoi(char c[][]){
		int[][] array = new int[5][2];
		for(int i=0; i<5; i++){
			for(int j = 0; j < 2; j++){
				if(c[i][j]<='9' && c[i][j]>='2')
					array[i][j] = c[i][j]-'0';
				else {
					switch(c[i][j]){
						case 'T':array[i][j]=10;break;
						case 'J':array[i][j]=11;break;
						case 'Q':array[i][j]=12;break;
						case 'K':array[i][j]=13;break;
						case 'A':array[i][j]=14;break;
						case 'C':array[i][j]=1;break;
						case 'D':array[i][j]=2;break;
						case 'H':array[i][j]=3;break;
						case 'S':array[i][j]=4;break;
					}
				}
			}
		}
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 4-i; j++){
				if(array[j][0] > array[j+1][0]){
					int tmp=array[j][0];
					array[j][0]=array[j+1][0];
					array[j+1][0]=tmp;
				}
			}
		}
		return array;
	}
	int rank(int c[][]){
		boolean one = c[0][0] == c[1][0];
		boolean two = c[1][0] == c[2][0];
		boolean three = c[2][0] == c[3][0];
		boolean four = c[3][0] == c[4][0];
		boolean shunzi = false , xt = false;// xt == 相同花色
		int i=0;
		while(i<4){//检验是否为顺子
			if(c[i][0]+1 != c[i+1][0]){
				shunzi = false;
				break;
			}
			i++;
			if(i == 4)
				shunzi = true;
		}
		i=0;
		while(i<4){
			if(c[i][0] != c[i+1][0]){
				xt = false;
				break;
			}
			i++;
			if(i == 4)
				xt = true;
		}
		if(one || two || three || four){
			if((one && two && three) || (two && three && four))
				return 8;
			if((one && two && four) || (one && three && four))
				return 7;
			if((one && two) || (two && three) || (three && four)){
				if(xt)return 6;
				else return 4;
			}
			if((one && three) || (two && four) || (one && four)){
				if(xt)return 6;
				else return 3;
			}
			if(xt) return 6;
			else return 2;
		}
		else if(shunzi){
			if(xt) return 9;
			return 5;
		}else{
			if(xt) return 6;
			return 1;
		}
	}
	int equal_rank(int black[][], int white[][], int rank){
		int pair_b[] = new int[2];
		int pair_w[] = new int[2];
		int remain_b[] = new int[3];
		int remain_w[] = new int[3];
		int three_kind_b = 0, three_kind_w = 0, four_kind_b = 0, four_kind_w = 0;
		if(rank == 1 || rank == 6){
			for(int i = 4; i>=0; i--){
				if(black[i][0] == white[i][0])
					continue;
				else if(black[i][0] > white[i][0]) return 1;
				else return -1;
			}
			return 0;
		}
		if(2 == rank){
			for(int i = 0; i < 4; i++){
				if(black[i][0] == black[i+1][0])
					pair_b[0] = black[i][0];
				if(white[i][0] == white[i][0])
					pair_w[0] = white[i][0];
			}
			if(pair_b[0] > pair_w[0]) return 1;
			else if(pair_b[0] < pair_w[0]) return -1;
			else{
				int j = 0, k = 0;
				for(int i=0; i<5; i++)
				{
					if(pair_b[0] != black[i][0])
						remain_b[j++]=black[i][0];
					if(pair_w[0] != white[i][0])
						remain_w[k++]=white[i][0];
				}
				for(int i = 2; i >= 0; i--){
					if(remain_b[i] > remain_w[i]) return 1;
					else if(remain_b[i] < remain_w[i]) return -1;
					return 0;
				}
			}
			
		}
		if(3 == rank){
			int j = 0, k = 0;
			for(int i = 0; i < 4; i++){
				if(black[i][0] == black[i+1][0])
					pair_b[j++] = black[i][0];
				if(white[i][0] == white[i][0])
					pair_w[k++] = white[i][0];
			}
			for(int i=1 ;i>=0 ;i--)
			{
				if(pair_b[i] == pair_w[i])
					continue;
				else if(pair_b[i] > pair_w[i])
					return 1;
				else
					return -1;
			}
			for(int i=0 ;i<5 ;i++)
			{
				if(black[i][0] != pair_b[0] && black[i][0] != pair_b[1])
					remain_b[0]=black[i][0];
				if(white[i][0] != pair_w[0] && white[i][0] != pair_w[1])
					remain_w[0]=white[i][0];
			}
			if(remain_w[0] == remain_b[0])
				return 0;
			else if(remain_b[0] > remain_w[0])
				return 1;
			else 
				return -1;
		}
		if(4 == rank || 7 == rank){
			for(int i=0 ;i<3 ;i++)
			{
				if(black[i][0] == black[i+1][0] && black[i][0] == black[i+2][0])
					three_kind_b=black[i][0];
				if(white[i][0] == white[i+1][0] && white[i][0] == white[i+2][0])
					three_kind_w=white[i][0];
			}
			if(three_kind_b > three_kind_w)
				return 1;
			else if(three_kind_b < three_kind_w)
				return -1;
			else{
				int j = 0, k = 0;
				for(int i=0 ;i<5 ;i++)
				{
					if(black[i][0] != three_kind_b)
						remain_b[j++]=black[i][0];
					if(white[i][0] != three_kind_w)
						remain_w[k++]=white[i][0];
				}
				for(int i = 2; i>=0; i--){
					if(remain_w[i] == remain_b[i])
						return 0;
					else if(remain_b[i] > remain_w[i])
						return 1;
					else 
						return -1;
				}
			}
		}
		if(5 == rank || 9 == rank){
			if(black[4][0] > white[4][0])
				return 1;
			else if(black[4][0] < white[4][0])
				return -1;
			else 
				return 0;
		}
		if(8 == rank){
			for(int i=0 ;i<2 ;i++)
			{
				if(black[i][0]==black[i+1][0] && black[i][0]==black[i+2][0] && black[i][0]==black[i+3][0])
					four_kind_b=black[i][0];
				if(white[i][0]==white[i+1][0] && white[i][0]==white[i+2][0] && white[i][0]==white[i+3][0])
					four_kind_w=white[i][0];
			}
			if(four_kind_b > four_kind_w)
				return 1;
			else if(four_kind_b > four_kind_w)
				return -1;
			else{
				for(int i=0 ;i<5 ;i++)
				{
					if(black[i][0] != four_kind_b)
						remain_b[0]=black[i][0];
					if(white[i][0] != four_kind_w)
						remain_w[0]=white[i][0];
				}
				if(remain_w[0] == remain_b[0])
					return 0;
				else if(remain_b[0] > remain_w[0])
					return 1;
				else 
					return -1;
			}
		}
		return 0;
	}
}
