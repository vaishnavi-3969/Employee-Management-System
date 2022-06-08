//Assignment-0 (DBMS)
//To implement the insert, delete, update and search operations on the database
//Consider the Employees data like (EmpNo, EmpName, Post, Salary, DeptNo) and
// implement the following operations on the database using file handling in any language
// like JAVA, Python etc.
// using file handling (any language like JAVA, Python etc)

import java.io.*;
import java.util.*;

class Employee implements Serializable {
  int empNo;
  String empName;
  String post;
  float salary;
  int deptNo;

  public Employee(int empNo, String empName, String post, float salary, int deptNo) {
    this.empNo = empNo;
    this.empName = empName;
    this.post = post;
    this.salary = salary;
    this.deptNo = deptNo;
  }

  public String toString() {
    return ("\nEmployee Details: " + "\nEmployee No.: " + this.empNo + "\nEmployee Name: " + this.empName + "\nPost: " + this.post + "\nSalary: " + this.salary + "\nDepartment No.: " + this.deptNo);
  }
}

public class Main {
  static void display(ArrayList<Employee> al) {
    System.out.println("\n---------------------------------------Employee List---------------------------------------");
    System.out.println(String.format("%-10s%-30s%-30s%-10s%-10s", "Employee No.","Employee Name","Post","Salary","Department No."));
    System.out.println("--------------------------------------------------------------------------------------------");
      for (Employee e: al) {
        System.out.println(String.format("%-10s%-30s%-30s%-10s%-10s",e.empNo , e.empName , e.post, e.salary , e.deptNo ));
        System.out.println("--------------------------------------------------------------------------------------------");
      }
   }

  public static void main(String[] args) {
    int empNo;
    String empName;
    float salary;
    String post;
    int deptNo;

    Scanner scan = new Scanner(System.in);
    ArrayList<Employee> al = new ArrayList<Employee>();
    File f  = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    try {
      f = new File("Employee.txt");
      if(f.exists()) {
        fis = new FileInputStream(f);
        ois = new ObjectInputStream(fis);
        al = (ArrayList<Employee>)ois.readObject();
      }
    }
    catch (Exception exception) {
      // System.out.println(exception);
    }

    do {
      System.out.println("********************** Employee Management System **********************");
      System.out.println("\n1. Insert New Record \n2. Delete a Record \n3. Update salary of an employee \n4. Update post of an employee \n5. Search by Employee No. \n6. Search by Employee Name \n7. Exit");
      System.out.println("Enter your choice: ");
      int ch = scan.nextInt();

      switch (ch) {
        case 1:
          System.out.println("\nAdd a Record");
          System.out.println("Enter the Employee No.: ");
          empNo = scan.nextInt();
          System.out.println("Enter name of the Employee: ");
          empName = scan.next();
          System.out.println("Enter the post of the Employee: ");
          post = scan.next();
          System.out.println("Enter the salary of the employee (in LPA): ");
          salary = scan.nextFloat();
          System.out.println("Enter the Department No. of the Employee: ");
          deptNo = scan.nextInt();
          al.add(new Employee(empNo, empName, post, salary, deptNo));
          display(al);
          break;

        case 2:
          System.out.println("Enter the Employee No. to delete: ");
          empNo = scan.nextInt();
          int k = 0;
          try {
            for(Employee e : al) {
              if (empNo == e.empNo) {
                al.remove(e);
                display(al);
                k++;
                }
            }

            if (k == 0) {
              System.out.println("\nEmployee No. Invalid!");
            }
          } catch (Exception exception) {
            // System.out.println(exception);
          }
          break;

        case 3:
          System.out.println("\nUpdating salary of a particular employee");
          System.out.println("Enter the Employee no. of employee ");
            empNo = scan.nextInt();
          int h = 0;
          for (Employee e :al) {
            if (empNo == e.empNo) {
              System.out.println("Enter the new salary of the employee: ");
              e.salary = scan.nextFloat();
              System.out.println("Salary updated" + e + "\n");
              h++;
            } 
          }
          if (h ==0){
              System.out.println("Employee details unavailable");
         }  
          break;

        case 4: 
          System.out.println("Update post of a particular employee");
          System.out.println("Enter the Employee Number");
          empNo = scan.nextInt();
          int n = 0;
          for (Employee e :al) {
            if (empNo == e.empNo) {
              System.out.println("Enter the new post of the employee: ");
              e.post = scan.next();
              System.out.println("Post updated" + e + "\n");
              n++;
            }  
            if (n == 0){
              System.out.println("Employee details unavailable");
              }  
            }
          break;
          
        case 5:
          System.out.println("Search a record with employee no. ");
          System.out.println("Enter the Employee No.: ");
          empNo = scan.nextInt();
          int i = 0;
          for (Employee e: al) {
            if (empNo == e.empNo) {
              System.out.println(e + "\n");
              i++;
            }
          } 
          if (i == 0) {
            System.out.println("Employee details unavailable!!!");
          }
          break;

        case 6:
          System.out.println("Search with employee name");
          System.out.println("Enter the name of the Employee: ");
          empName = scan.next();
          int a = 0;
          for(Employee e : al) {
            if (empName.equals(e.empName)) {
              System.out.println(e + "\n");
              a++;
            } 
            if (a == 0) {
              System.out.println("Employee details unavailable!!!");
            }
          }
          break;

        case 7:
          try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(al);
          } catch (Exception e2) {
            e2.printStackTrace();
          } finally {
            try {
              fis.close();
              ois.close();
              fos.close();
              oos.close();
            } catch (Exception e) {
              // e.printStackTrace();
            }
          }
          System.out.println("\nSaving all files and closing");
          scan.close();
          System.exit(0);
          break;

        default:
          System.out.println("Invalid Choice!!!");
          break;
      }
    } while(true);
  }
}