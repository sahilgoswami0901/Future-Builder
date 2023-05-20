import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

class Placement_cell{
    private String S_start_date_time;
    private String S_end_date_time;
    private String C_start_date_time;
    private String C_end_date_time;
    boolean start;
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Company> companies = new ArrayList<>();
    ArrayList<Student> Blocked_students = new ArrayList<>();
    ArrayList<Student> Placed_students = new ArrayList<>();

    public void Open_student_reg(){
        System.out.println("Fill in the details:- ");
        System.out.println("     1) Set the Opening time for student registrations");
        System.out.println("     1) Set the Closing time for student registrations");
        Scanner sc = new Scanner(System.in);
        String start=sc.nextLine();
        this.setS_start_date_time(start);
        String end=sc.nextLine();
        this.setS_end_date_time(end);
//        this.S_start_date_time=sc.nextLine();
//        this.S_end_date_time=sc.nextLine();
    }
    public void Open_company_reg()
    {   start=false;
        System.out.println("Fill in the details:- ");
        System.out.println("     1) Set the Opening time for company registrations");
        System.out.println("     1) Set the Closing time for company registrations");
        Scanner sc = new Scanner(System.in);
        String start=sc.nextLine();
        String end=sc.nextLine();
        this.setC_start_date_time(start);
        this.setC_end_date_time(end);
//        this.C_start_date_time=sc.nextLine();
//        this.C_end_date_time=sc.nextLine();
    }
    public void get_student()
    {
        System.out.println("The Number of Student Registrations are: "+this.students.size());
    }
    public void get_company()
    {
        System.out.println("The Number of Company Registrations are: "+this.companies.size());
    }
    public void get_PlacedStudents()
    {
        System.out.println("Number of Offered Students are: "+ this.Placed_students.size());
        System.out.println("Details are: ");
        for(Student i: this.Placed_students)
        {
            System.out.println(i+") Name: "+i.getName());
            System.out.println("    RollNo: "+i.getRoll_no());
            System.out.println("    CGPA: "+i.getCgpa());
            System.out.println("    Branch: "+i.getBranch());
        }
    }
    public void get_UnplacedStudents()
    {
        System.out.println("Number of Unplaced Students are: "+(this.students.size()-(this.Placed_students.size()+this.Blocked_students.size())));
    }
    public void get_BlockedStudents()
    {
        System.out.println("Number of Offered Students are: "+ this.Blocked_students.size());
    }
    public void setS_start_date_time(String date)
    {
        this.S_start_date_time=date;
    }
    public String getS_start_date_time()
    {
        return this.S_start_date_time;
    }
    public void setS_end_date_time(String date)
    {
        this.S_end_date_time=date;
    }
    public String getS_end_date_time()
    {
        return this.S_end_date_time;
    }
    public void setC_start_date_time(String date)
    {
        this.C_start_date_time=date;
    }
    public String getC_start_date_time()
    {
        return this.C_start_date_time;
    }
    public void setC_end_date_time(String date)
    {
        this.C_end_date_time=date;
    }
    public String getC_end_date_time()
    {
        return this.C_end_date_time;
    }
}
class Student {
    private String name;
    private long roll_no;
    private float cgpa;
    float s_CTC;
    private String branch;
    String status;
    boolean placed;
    boolean eligible;
    final private Placement_cell P1;
    String date_time;
    String offer;
    ArrayList<Company> offers = new ArrayList<>();

    public Student(String name, long roll_no, float cgpa, String branch, Placement_cell P) {
        this.name = name;
        this.roll_no = roll_no;
        this.cgpa = cgpa;
        this.branch = branch;
        this.status = "not-applied";
        this.placed = false;
        this.s_CTC=0;
        P1 = P;
    }

    public void reg_placement_drive() throws ParseException {
        if(P1.start)
        {
            boolean check=false;
            for(int i=0;i<P1.Blocked_students.size();i++)
            {
                if(P1.Blocked_students.get(i).name==this.name)
                {
                    check=true;
                }
            }
            if(!check) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm a");
                Date dateTime1 = formatter.parse(P1.getS_start_date_time());
                Date dateTime2 = formatter.parse(P1.getS_end_date_time());
                Date dateTime3 = formatter.parse(this.date_time);
                boolean bool1 = dateTime3.after(dateTime1);
                boolean bool2 = dateTime3.before(dateTime2);
                boolean bool3 = dateTime3.equals(dateTime2);
                if (bool1 && bool2 || bool1 && bool3) {
                    P1.students.add(this);
                    System.out.println();
                    System.out.println(this.name + " registered for the Placement Drive at IIITD!!!!");
                    System.out.println("Your details are:");
                    System.out.println("Name: " + this.name);
                    System.out.println("RollNo: " + this.roll_no);
                    System.out.println("CGPA: " + this.cgpa);
                    System.out.println("Branch: " + this.branch);
                    System.out.println();
                } else {
                    System.out.println("Be on time!!!!");
                }
            }
            else {
                System.out.println(this.name+"is blocked by placement cell");
            }
        }
        else {
            System.out.println("Registrations for Students haven't started yet");
        }
    }

    public void reg_for_Company(Company C) {
        if (this.cgpa >= C.getCGPA() && C.getCTC()>=3*this.s_CTC) {
            this.eligible = true;
        }

        if (!this.placed && this.eligible) {
            this.status = "applied";
            C.reg_students.add(this);
            System.out.println("Successfully Registered for "+C.getRole()+" Role at "+C.getName_company());
        }
//        if(this.placed && this.eligible)
//        {
//            this.status="applied";
//            C.reg_students.add(this);
//            System.out.println("Successfully Registered for "+C.role+" Role at "+C.name_company);
//        }

    }

    public void available_companies() {
        int count=1;
        System.out.println("List of All Available companies are as follows: ");
        for (Company i: P1.companies) {
            System.out.println(count+")  Company Name: "+i.getName_company());
            System.out.println("    Company role offering: "+i.getRole());
            System.out.println("    Company Package: "+i.getCTC()+" LPA");
            System.out.println("    Company CGPA criteria: "+i.getCGPA());
            System.out.println();
            count++;
        }
    }

    public void Update_cgpa(String n) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Older CGPA: ");
        float o_cgpa = sc.nextFloat();
        System.out.println(o_cgpa);
        System.out.print("Enter the Updated CGPA: ");
        float n_cgpa = sc.nextFloat();
        System.out.println(n_cgpa);
        for (int i = 0; i < P1.students.size(); i++) {
            if (P1.students.get(i).cgpa == o_cgpa && P1.students.get(i).name.equals(n)) {
                P1.students.get(i).cgpa = n_cgpa;
            }
        }
    }
    public void Accept_offer ()
    {
//        if(this.placed && this.offer=="Accepted")
//        {
//           this.offers.remove(this.offers.size()-2);
//           System.out.println("Congratulations "+this.name+"!!! You have accepted the offer by "+this.offers.get(this.offers.size()-1).name_company+"!!");
//        }
        this.offer="Accepted";
        System.out.println("Congratulations "+this.name+"!!! You have accepted the offer by "+this.offers.get(this.offers.size()-1).getName_company()+"!!");
        this.placed=true;
        P1.Placed_students.add(this);
        this.s_CTC=this.offers.get(this.offers.size()-1).getCTC();

    }

    public void Reject_offer()
    {
        this.offer="Rejected";
        System.out.println("OOPS "+this.name+"!!! You have rejected the offer by "+this.offers.get(this.offers.size()-1).getName_company()+"!!");
        this.offers.remove(this.offers.get(this.offers.size()-1));
        if(this.offers.size()==0)
        {
            P1.Blocked_students.add(this);
        }
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setRoll_no(long roll)
    {
        this.roll_no=roll;
    }
    public long getRoll_no()
    {
        return roll_no;
    }
    public void setCgpa(float cgpa)
    {
        this.cgpa=cgpa;
    }
    public float getCgpa()
    {
        return this.cgpa;
    }
    public void setBranch(String branch)
    {
        this.branch=branch;
    }
    public String getBranch()
    {
        return this.branch;
    }
}

class Company {
    private String name_company;
    private String role;
    private float CTC;
    private float CGPA;
    String date_time;
    ArrayList<Student> reg_students = new ArrayList<>();
    ArrayList<Student> sel_students = new ArrayList<>();
    private Placement_cell P2;


    public Company(String name_company, float CTC, float CGPA, String role, Placement_cell P) {
        this.name_company = name_company;
        this.CTC = CTC;
        this.CGPA = CGPA;
        this.role = role;
        P2=P;
    }

    public void reg_InstituteDrive() throws ParseException {
        P2.start=true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm a");
        Date dateTime1 = formatter.parse(P2.getC_start_date_time());
        Date dateTime2 = formatter.parse(P2.getC_end_date_time());
        Date dateTime3 = formatter.parse(this.date_time);
        Boolean bool1 = dateTime3.after(dateTime1);
        Boolean bool2 = dateTime3.before(dateTime2);
        Boolean bool3 = dateTime3.equals(dateTime2);
        if (bool1 && bool2 || bool1 && bool3) {
            P2.companies.add(this);
            System.out.println("Registered");
        } else {
            System.out.println("Be on time!!!!");
        }
    }

    public void Selected_students() {
        Random rand = new Random();
        int ub = this.reg_students.size();
        int range = rand.nextInt(ub);
        if (range == 1) {
            range = range + 1;
        }
        for (int i = 0; i < this.reg_students.size(); i++) {
            this.sel_students.add(this.reg_students.get(i));
        }
    }

    public void change_role(String role) {
        this.role = role;
    }

    public void change_package(float CTC) {
        this.CTC = CTC;
    }

    public void change_CGPA(float CGPA) {
        this.CGPA = CGPA;
    }
    public void setName_company(String name)
    {
        this.name_company=name;
    }
    public String getName_company()
    {
        return this.name_company;
    }
    public void setRole(String role)
    {
        this.role=role;
    }
    public String getRole()
    {
        return this.role;
    }
    public void setCTC(float ctc)
    {
        this.CTC=ctc;
    }
    public float getCTC()
    {
        return this.CTC;
    }
    public void setCGPA(float cgpa1)
    {
        this.CGPA=cgpa1;
    }
    public float getCGPA()
    {
        return this.CGPA;
    }
}

public class Project1 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        Placement_cell P = new Placement_cell();
        ArrayList<Student> Students = new ArrayList<>();
        ArrayList<Company> Companies = new ArrayList<>();
        boolean entry=true;
        while(entry) {
            String enter;
            enter = sc.nextLine();
            l8: switch (enter) {
                case "Enter FutureBuilder":
                    while (true) {
                        int choice1;
                        System.out.println("Welcome to FutureBuilder:");
                        System.out.println("   1) Enter the Application");
                        System.out.println("   2) Exit the Application");
                        choice1 = sc.nextInt();
                        sc.nextLine();
                        l1:
                        switch (choice1) {
                            case 1:
                                while (true) {
                                    System.out.println("Choose the mode you want to Enter in:-");
                                    System.out.println("    1) Enter as Student Mode");
                                    System.out.println("    2) Enter as Company Mode");
                                    System.out.println("    3) Enter as Placement Cell Mode");
                                    System.out.println("    4) Return to Main Application");
                                    int choice2;
                                    choice2 = sc.nextInt();
                                    sc.nextLine();
                                    l2:
                                    switch (choice2) {
                                        case 1:
                                            while (true) {
                                                System.out.println("Choose the Student Query to perform-");
                                                System.out.println("   1) Enter as a Student(Give Student Name and Roll No.");
                                                System.out.println("   2) Add Students");
                                                System.out.println("   3) Back");
                                                int choice3 = sc.nextInt();
                                                sc.nextLine();
                                                l3:
                                                switch (choice3) {
                                                    case 1:
                                                        String n;
                                                        long roll;
                                                        System.out.println("Enter the name:");
                                                        n = sc.nextLine();
                                                        System.out.println("Enter the roll number: ");
                                                        roll = sc.nextLong();
                                                        System.out.println();
                                                        while (true) {
                                                            System.out.println("Welcome " + n + "!!");
                                                            System.out.println("   1) Register For Placement Drive");
                                                            System.out.println("   2) Register For Company");
                                                            System.out.println("   3) Get All available companies");
                                                            System.out.println("   4) Get Current Status");
                                                            System.out.println("   5) Update CGPA");
                                                            System.out.println("   6) Accept offer");
                                                            System.out.println("   7) Reject offer");
                                                            System.out.println("   8) Back");
                                                            int choice4;
                                                            choice4 = sc.nextInt();
                                                            sc.nextLine();
                                                            l4:
                                                            switch (choice4) {
                                                                case 1:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            Students.get(i).date_time=sc.nextLine();
                                                                            Students.get(i).reg_placement_drive();
                                                                        }
                                                                    }
                                                                    break;

                                                                case 2:
                                                                    System.out.println("Enter the name of Company: ");
                                                                    String name2 = sc.nextLine();
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        for(int j=0;j< Companies.size();j++)
                                                                        {
                                                                            if (Companies.get(j).getName_company().equals(name2) && Students.get(i).getName().equals(n)) {
                                                                                Students.get(i).reg_for_Company(Companies.get(j));
                                                                            }
                                                                        }
                                                                    }
                                                                    break;

                                                                case 3:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            Students.get(i).available_companies();
                                                                        }
                                                                    }
                                                                    break;

                                                                case 4:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        for(int j=0;j< Companies.size();j++)
                                                                        {
                                                                            if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                                for (Student k : Companies.get(j).sel_students) {
                                                                                    if (k.getName().equals(n)) {
                                                                                        Students.get(i).offers.add(Companies.get(j));
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            if(!Students.get(i).eligible || Students.get(i).status=="not-applied" || Students.get(i).offers.size()==0)
                                                                            {
                                                                                System.out.println(Students.get(i).getName()+" is Unplaced");
                                                                            }
                                                                            else if (Students.get(i).offers.size() == 1) {
                                                                                System.out.println("You have been offered by " + Students.get(i).offers.get(Students.get(i).offers.size() - 1).getName_company() + "!! Please accept the offer.");
                                                                            }
                                                                            else {
                                                                                System.out.println("2");
                                                                                for (int k = 0; k < Students.get(i).offers.size() - 1; k++) {
                                                                                    if (Students.get(i).offers.get(k).getCTC() > Students.get(i).offers.get(k + 1).getCTC()) {
                                                                                        System.out.println("You have been offered by " + Students.get(i).offers.get(k).getName_company() + "!! Please accept the offer.");
                                                                                    }
                                                                                }

//                                                                                    else if (Students.get(i).offers.size() == 1) {
//                                                                                        System.out.println("3");
//                                                                                        System.out.println("You have been offered by " + Students.get(i).offers.get(k).name_company + "!! Please accept the offer.");
//                                                                                    }
                                                                            }
                                                                        }
                                                                    }
                                                                    break;

                                                                case 5:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            Students.get(i).Update_cgpa(n);
                                                                        }
                                                                    }
                                                                    break;

                                                                case 6:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            Students.get(i).Accept_offer();
                                                                        }
                                                                    }
                                                                    break;

                                                                case 7:
                                                                    for (int i = 0; i < Students.size(); i++) {
                                                                        if (Students.get(i).getName().equals(n) && Students.get(i).getRoll_no() == roll) {
                                                                            Students.get(i).Reject_offer();

                                                                        }
                                                                    }
                                                                    break;

                                                                case 8:
                                                                    break l3;
                                                            }
                                                        }
                                                    case 2:
                                                        System.out.println("Number of students to add");
                                                        int add;
                                                        add = sc.nextInt();
                                                        sc.nextLine();
                                                        System.out.println("Please add students name, Roll No, CGPA, Branch(in order):");
                                                        for (int i = 0; i < add; i++) {
                                                            String name = sc.nextLine();
                                                            long roll_no = sc.nextLong();
                                                            float cgpa = sc.nextFloat();
                                                            sc.nextLine();
                                                            String branch = sc.nextLine();
                                                            Students.add(new Student(name, roll_no, cgpa, branch, P));
                                                            System.out.println();
                                                        }
                                                        break;
                                                    case 3:
                                                        break l2;
                                                }
                                            }
                                        case 2:
                                            while (true) {
                                                System.out.println("Choose the Company Query to perform-");
                                                System.out.println("    1) Add Company and Details");
                                                System.out.println("    2) Choose Company");
                                                System.out.println("    3) Get Available Companies");
                                                System.out.println("    4) back");
                                                int choice5;
                                                choice5 = sc.nextInt();
                                                l5: switch (choice5) {
                                                    case 1:
                                                        sc.nextLine();
                                                        String name = sc.nextLine();
                                                        String role = sc.nextLine();
                                                        float CTC = sc.nextFloat();
                                                        sc.nextLine();
                                                        float CGPA = sc.nextFloat();
                                                        System.out.println(name);
                                                        System.out.println(role);
                                                        System.out.println(CTC);
                                                        System.out.println(CGPA);
                                                        Companies.add(new Company(name, CTC, CGPA, role, P));
                                                        break;

                                                    case 2:
                                                        int count=1;
                                                        System.out.println("Choose to enter into mode of Available Companies");
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            System.out.println((count) + ") " + Companies.get(i).getName_company());
                                                            count++;
                                                        }
                                                        int choice6;
                                                        choice6 = sc.nextInt();
                                                        while (true) {
                                                            System.out.println("Welcome " + Companies.get(choice6-1).getName_company());
                                                            System.out.println("    1) Update Role");
                                                            System.out.println("    2) Update Package");
                                                            System.out.println("    3) Update CGPA criteria");
                                                            System.out.println("    4) Register To Institute Drive");
                                                            System.out.println("    5) Back");
                                                            int choice7;
                                                            choice7 = sc.nextInt();
                                                            sc.nextLine();
                                                            l6:
                                                            switch (choice7) {
                                                                case 1:
                                                                    System.out.println();
                                                                    String updated_role;
                                                                    updated_role = sc.nextLine();
                                                                    Companies.get(choice6-1).change_role(updated_role);
                                                                    break;

                                                                case 2:
                                                                    System.out.println();
                                                                    float updated_CTC;
                                                                    updated_CTC = sc.nextFloat();
                                                                    Companies.get(choice6-1).change_package(updated_CTC);
                                                                    break;

                                                                case 3:
                                                                    System.out.println();
                                                                    float updated_CGPA;
                                                                    updated_CGPA = sc.nextFloat();
                                                                    Companies.get(choice6-1).change_CGPA(updated_CGPA);
                                                                    break;

                                                                case 4:
                                                                    System.out.println();
                                                                    Companies.get(choice6-1).date_time = sc.nextLine();
                                                                    Companies.get(choice6-1).reg_InstituteDrive();
                                                                    break;

                                                                case 5:
                                                                    break l5;
                                                            }
                                                        }

                                                    case 3:
                                                        int count3=1;
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            System.out.println(count3 + ") Name: " + Companies.get(i).getName_company());
                                                            System.out.println("    Role: " + Companies.get(i).getRole());
                                                            System.out.println("    CTC: " + Companies.get(i).getCTC());
                                                            System.out.println("    CGPA: " + Companies.get(i).getCGPA());
                                                            count3++;
                                                        }
                                                        break;

                                                    case 4:
                                                        break l2;

                                                }
                                            }
                                        case 3:
                                            while (true) {
                                                System.out.println("Welcome to IIITD Placement Cell");
                                                System.out.println("    1) Open Student Registrations");
                                                System.out.println("    2) Open Company Registrations");
                                                System.out.println("    3) Get Number of Student Registrations");
                                                System.out.println("    4) Get Number of Company Registrations");
                                                System.out.println("    5) Get Number of Offered/Unoffered/Blocked Students");
                                                System.out.println("    6) Get Student Details");
                                                System.out.println("    7) Get Company Details");
                                                System.out.println("    8) Get Average Package");
                                                System.out.println("    9) Get Company Process Results");
                                                System.out.println("   10) Back");
                                                int choice10 = sc.nextInt();
                                                sc.nextLine();
                                                l7: switch (choice10) {
                                                    case 1:
                                                        P.Open_student_reg();
                                                        break;

                                                    case 2:
                                                        P.Open_company_reg();
                                                        break;

                                                    case 3:
                                                        P.get_student();
                                                        break;

                                                    case 4:
                                                        P.get_company();
                                                        break;

                                                    case 5:
                                                        P.get_PlacedStudents();
                                                        P.get_UnplacedStudents();
                                                        P.get_BlockedStudents();
                                                        break;

                                                    case 6:
                                                        System.out.print("Enter the Name: ");
                                                        String name1 = sc.nextLine();
                                                        System.out.println();
                                                        System.out.print("Enter the RollNO: ");
                                                        long roll = sc.nextLong();
                                                        sc.nextLine();
                                                        System.out.println();
                                                        System.out.println(name1);
                                                        System.out.println(roll);
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            if(Students.get(i).getName().equals(name1) && Companies.get(i).reg_students.size()==0)
                                                            {
                                                                System.out.println(Students.get(i).getName()+" didn't applied for the company "+Companies.get(i).getName_company());
                                                            }
                                                            else {
                                                                for (int j = 0; j < Companies.get(i).reg_students.size(); j++) {
                                                                    if (Companies.get(i).reg_students.get(j).getName().equals(name1) && Companies.get(i).reg_students.get(j).getRoll_no() == roll) {
                                                                        System.out.println("Name of Company: " + Companies.get(i).getName_company());
                                                                        System.out.println(name1 + " is applied for the role of " + Companies.get(i).getRole());
                                                                        System.out.println("CTC offered by the " + Companies.get(i).getName_company() + " is " + Companies.get(i).getCTC());
                                                                        System.out.println();
                                                                    }
//                                                                else if(Students.get(i).name.equals(name1) && !Companies.get(i).reg_students.get(j).name.equals(name1))
//                                                                {
//                                                                    System.out.println(Students.get(i).name+" didn't applied for the company "+Companies.get(i).name_company);
//                                                                }
                                                                    else if(Companies.get(i).reg_students.get(j).getName()!=(name1) && Companies.get(i).reg_students.get(j).getRoll_no() != roll){
                                                                        System.out.println(name1 + " didn't applied for " + Companies.get(i).getName_company());
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            for (int j = 0; j < Companies.get(i).sel_students.size(); j++) {
                                                                if (Companies.get(i).sel_students.get(j).getName().equals(name1) && Companies.get(i).sel_students.get(j).getRoll_no() == roll) {
                                                                    System.out.println(name1 + " have been offered by the " + Companies.get(i).getName_company());
                                                                } else if (Companies.get(i).sel_students.get(j).getName()!=(name1) && Companies.get(i).sel_students.get(j).getRoll_no() != roll){
                                                                    System.out.println(name1 + " haven't been offered by the " + Companies.get(i).getName_company());
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    case 7:
                                                        System.out.print("Enter the Name of Company:");
                                                        String name2 = sc.nextLine();
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            if (Companies.get(i).getName_company().equals(name2)) {
                                                                System.out.println("Name of Company: " + Companies.get(i).getName_company());
                                                                System.out.println("Role: " + Companies.get(i).getRole());
                                                                System.out.println("CTC: " + Companies.get(i).getCTC());
                                                            }
                                                        }
                                                        int count2=1;
                                                        System.out.println("Students Offered by the " + name2);
                                                        for (int j = 0; j < Companies.size(); j++) {
                                                            if (Companies.get(j).getName_company().equals(name2)) {
                                                                for (Student k : Companies.get(j).sel_students) {
                                                                    System.out.println(count2 + ") Name: " + k.getName());
                                                                    System.out.println("   RollNo: " + k.getRoll_no());
                                                                    count2++;
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    case 8:
                                                        float c;
                                                        float sum = 0;
                                                        int number;
                                                        for (int i = 0; i < P.Placed_students.size(); i++) {
                                                            sum=sum+(P.Placed_students.get(i).s_CTC);
                                                        }
                                                        int t_students = P.students.size();
                                                        float avg_package = sum / t_students;
                                                        System.out.println("Average Package is: " + avg_package);
                                                        break;

                                                    case 9:
                                                        System.out.println("Enter the name of Company: ");
                                                        String c_name = sc.nextLine();
                                                        System.out.println(c_name);
                                                        int count=1;
                                                        for (int i = 0; i < Companies.size(); i++) {
                                                            if (Companies.get(i).getName_company().equals(c_name)) {
                                                                Companies.get(i).Selected_students();
                                                                for (Student j : Companies.get(i).sel_students) {
                                                                    System.out.println("Following are the students selected: ");
                                                                    System.out.println(count + ")" + "  Name: " + j.getName());
                                                                    System.out.println("    RollNo: " + j.getRoll_no());
                                                                    System.out.println("    CGPA: " + j.getCgpa());
                                                                    System.out.println("    Branch: " + j.getBranch());
                                                                    count++;
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    case 10:
                                                        break l2;
                                                }
                                            }
                                        case 4:
                                            break l1;

                                    }
                                }
                            case 2:
                                break l8;

                        }
                    }

                case "Exit FutureBuilder":
                    System.out.println("Thanks For Using FutureBuilder!!!!");
                    entry=false;
                    break;
            }
        }
    }
}


