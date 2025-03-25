public class GomokuGame {
    public final int SIZE = 15;
    private int[][] board;

    public GomokuGame() {
        board = new int[SIZE][SIZE];
    }
    
    public void setBoard(int x,int y,int s)
    {
        board[x][y]=s;
    }
    // 其他遊戲邏輯方法...
    public boolean checkWin(int x,int y)
    {
        //System.out.println(" ");
        //System.out.println(board[x][y]);
        
        if(checkVertical(x,y) || checkHorizontal(x,y) || checkUpperLeft(x,y) || checkLowerLeft(x,y))
        {
            //System.out.println("win");
            return true;
        }
        return false;
    }
    private boolean checkVertical(int x,int y)
    {
        int count=1;
        int nowx=x;
        int nowy=y;
        for(int i=0;i<4;i++)
        {
            nowx--;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    System.out.println(count);
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        nowx=x;
        nowy=y;
        for(int o=0;o<4;o++)
        {
            nowx++;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ) )
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    System.out.println(x+" "+y+" "+nowx+" "+nowy);
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        //System.out.println(count);
        if(count==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkHorizontal(int x,int y)
    {
        int count=1;
        int nowx=x;
        int nowy=y;
        for(int i=0;i<4;i++)
        {
            nowy--;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        nowx=x;
        nowy=y;
        for(int o=0;o<4;o++)
        {
            nowy++;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        System.out.println(count);
        if(count==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkLowerLeft(int x,int y)
    {
        int count=1;
        int nowx=x;
        int nowy=y;
        for(int i=0;i<4;i++)
        {
            nowx--;
            nowy--;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    System.out.println(count);
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        nowx=x;
        nowy=y;
        for(int o=0;o<4;o++)
        {
            nowx++;
            nowy++;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        System.out.println(count);
        if(count==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkUpperLeft(int x,int y)
    {
        int count=1;
        int nowx=x;
        int nowy=y;
        for(int i=0;i<4;i++)
        {
            nowx--;
            nowy++;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        nowx=x;
        nowy=y;
        for(int o=0;o<4;o++)
        {
            nowx++;
            nowy--;
            if(( nowx>=0 && nowx<15 ) && (nowy>=0 && nowy<15 ))
            {
                if(board[nowx][nowy]== board[x][y])
                {
                    count++;
                }
            }
            else
            {
                break;
            }
        }
        System.out.println(count);
        if(count==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
