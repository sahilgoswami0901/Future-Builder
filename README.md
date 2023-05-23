# Placement-Cell
Command line simulation of Placement Cell Portal
<br/>
<div align="center">
  <img width="329" style="center" alt="image" src="https://github.com/sahilgoswami0901/Flipzon/assets/107829550/480a4f8c-5277-4fd1-951e-d53cd7666181"> <br/>
</div>

The placement season is just about to begin at IIITD, and the placement cell needs a portal <br/>
“FutureBuilder” to conduct the placement drive. You are supposed to create a portal to be used <br/>
by the students of the institute by which they can apply to the companies. You have to use <br/>
various concepts covered in class till lecture-3 to design this system. The application needs to <br/>
serve the following requirements:- <br/>
1) The placement cell opens company registrations, following which students register for
companies they are interested in.
2) A student can register for a company if they meet the following criteria -
a) If not placed yet and is eligible.
b) CGPA of the student >= minimum CGPA criteria of the company
c) CTC offered by the company >= 3 * highest CTC currently held by the student
For example - a student has received an offer from a company ‘A’ with a CTC Rs. x, now
this student can only register for a company ‘B’ if it offers a CTC >= 3*x, and also
satisfies the CGPA criteria set by the company.
3) Once the company receives all the registrations, it selects some students out of the ones
who applied (a company has to select at least one student; students can be picked
randomly for this question).
4) The selected students have the option to accept or reject the offer.
5) If a student accepts an offer, the status of that student should be marked as offered and
should not be allowed to sit in any further processes (unless the student satisfies criteria
2(b) and 2(c)).
6) If a not yet placed student rejects all the available offers at any point, then the student
status should be marked as blocked and shouldn’t be allowed to participate further.
7) A company can register only once.
NOTE: if a student gets selected in more than 1 company simultaneously, the student
will get the offer with a higher package.
You have to implement this system which should have the following functionalities.

# Student Functionalities:
1. Register For Placement Drive:- the student should be able to register for the placement
drive and have the following details -
a. Name
b. Roll No.
c. CGPA
d. Branch
2. Register For Company - An eligible student should be able to register for an available
company and status should be changed to applied. By default the status should be
“not-applied”.
3. Get All available companies - Print all the companies and its details where the student
can apply for. If a student doesn’t meet the criteria for sitting in further processes, print
unavailable.
4. Get Current Status - print the status of the student, whether blocked, offered, un-offered.
If the student is offered, print the company from which the student got the offer from and
its details.
5. Update CGPA - the student should give the older CGPA and the new updated CGPA to
the institute placement cell. The CGPA should be updated by the placement cell only.
6. Accept / Reject offer - the student has a choice to accept / reject the latest offer available
to him/her
Institute-Placement Cell Functionalities
1. Open Student Registrations - print that institute is open for student-registrations for the
placement drive. Also set a deadline till when the students can apply. It should have a
start and end date-time.
NOTE: registrations for students can’t open before company registrations end.
2. Open Company Registrations - print that the institute is open for company registrations
for the placements. Also, set a deadline for the companies can register. It should have a
start and end date and time.
NOTE: you will need to maintain the date-time as an attribute of a company when it
registers for placement process to check whether or not the company has applied before
the deadline.
3. Get Number of Student Registrations - print the number of students registered for the
placement drive.

4. Get Number of Company Registrations - print the number of companies registered for
the placement drive.
5. Get Number of Placed/UnPlaced/Blocked Students - print number of offered students
with their details.
Note: an unplaced student who rejects all available offers at any instant is blocked
from placement cycle
6. Get Student Details - it takes in the name and roll number of the student and prints
details of the participation in the placement drive (details of the companies applied for
and didn’t apply for. Also print whether the student was offered by the company or not).
7. Get Company Details - it takes in the name of the company and prints the details of the
company. Also print the names and roll no. of the students if the company has offered
any student.
8. Get Average Package: print the average package offered to the students of the institute.
9. Get Company Process Results:- In this query, the company should send in a list of all
the students they have selected to the cell. Take the company name as input in this
case.

# Company Functionalities:
1. RegisterToInstituteDrive - to register for an institute’s placement drive you need to add
following information.
a. Name of the company
b. Role a company offers
c. Package being offered by the company
d. CGPA criteria
e. Registration date-time (when company registers for the placement process)
Once registered you should print registered on the console.
NOTE:- you are not required to register a company for multiple roles. For simplicity
assume one company offers one type of role only.
2. Get Selected Students - get all the selected students of the campus who will be offered.
Print the names and details of the students (such as email ID, roll no, etc. )
3. Update Role/Package/CGPA criteria - using these options, the company can set the role
and package and the CGPA criteria.

For the demo of the working of the application, you are expected to add at least 1 company
and 3 students. There should be different modes where you should be able to operate the
portal.
1. The Application should start when you type in “Enter FutureBuilder”
2. On Entering we should get the following options
a. Student mode: type 1 to switch to student mode where you perform only student
queries.
NOTE:- you will be asked 3 options to choose in student mode(as given in the
test case). You can perform queries once student objects are created.
b. Institute Placement Cell mode: type 2 to switch to placement cell mode where
you perform only placement cell queries.
c. Company mode : type 3 to switch to company mode where you perform only
company queries.
d. Type 4 to return back to the main application
3. The Application should stop when you type in “Exit FutureBuilder”
