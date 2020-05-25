package com.study.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {
    public static void main(String[] args) throws Exception {
        //创建一个原始的二维数组 11 * 11
        //0:表示没有棋子，1 表示黑子，2 表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        //输出原始二维数组
        for(int[] row : chessArr1){
            for(int item : row){
                System.out.print("\t"+item);
            }
            System.out.println();
        }
        //将二维数组转换成稀疏数组
        //1.先遍历二维数组 得到非零数据的个数
        int sum = 0;
        for(int i = 0;i<chessArr1.length;i++){
            for(int j = 0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0)
                    sum++;
            }
        }
        //2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历二维数组，将非零的值存放到稀疏数组
        int count = 0;//用于记录是第几个非零数据
        for(int i = 0;i<chessArr1.length;i++){
            for(int j = 0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为======");
        for(int i = 0;i < sparseArray.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();
        //将稀疏数组恢复成二维数组
        /**
         * 1、先读取稀疏数组第一行数据，来创建二维数组
         * 2、在读取稀疏数组剩余行的数据，并赋值给二维数组
         */
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        for(int i = 1;i<sparseArray.length;i++){
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for(int[] row:chessArr2){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        File file = new File("E:\\map.data");
        FileWriter writer = new FileWriter(file);
        for(int[] row:chessArr2){
            for(int item:row){
                writer.write(item+"\t");
            }
            writer.write("\r\n");
        }
        writer.close();

        //读取
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null){
            row++;
            list.add(line);
        }
        reader.close();
        int arr[][] = new int[row][row];
        for(int i = 0;i<list.size();i++){
            String s = list.get(i);
            String[] split = s.split("\t");
            for(int j = 0;j<split.length;j++){
                int i1 = Integer.parseInt(split[j]);
                if(i1 > 0) arr[i][j] = i1;
            }
        }
        System.out.println("文件读取出的数组");
        for(int[] row1:arr){
            for(int item:row1){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
}
