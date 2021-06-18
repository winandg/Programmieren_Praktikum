package store;

import java.util.*;
public class Main
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        ArrayList<Integer> list=new ArrayList<Integer>(num);
        for(int i=0;i<num;i++)
        {
            list.add(sc.nextInt());

        }
        Iterator itr=list.iterator();
        {
            while(itr.hasNext())
            {
                System.out.print(itr.next()+" ");
            }
        }
    }
}