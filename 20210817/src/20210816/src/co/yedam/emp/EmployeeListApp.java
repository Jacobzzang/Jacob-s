package co.yedam.emp;

import java.util.Scanner;


public class EmployeeListApp {
public static void main(String[] args) {
	
   EmployeeList app =  EmployeeList.getInstance();
   //	new EmployeeList(); private ���� �ÿ��� ���� ó�� ������
   
   Scanner scanner = new Scanner(System.in);
   int selectNo = 0;
   boolean run = true;

   while (run) {
      System.out.println("0.����  1.�����  2.��������Է�  3.�������Ʈ  4.�˻�  5.�޿��հ�    6.����");
      System.out.print("����>");
      selectNo = scanner.nextInt();
      if (selectNo == 1) {
         app.init();
      } else if (selectNo == 2) {
         app.input();
      } else if (selectNo == 3) {
         app.print();
      } else if (selectNo == 4) {
         System.out.print("�˻��� ���>");
         int no = scanner.nextInt();
         String name = app.search(no);
         System.out.println("�̸��� "+ name);
      }else if (selectNo == 5) {
         int s = app.sum();
         System.out.println("�޿��հ� = " + s);
      } else if (selectNo == 0) {
         run = false;
      }
   }
   System.out.println("���α׷� ����");
   scanner.close();
}
}