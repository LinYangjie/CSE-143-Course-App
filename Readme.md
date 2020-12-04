CSE 143 Project User Guide
===

![image](https://user-images.githubusercontent.com/50006786/101043459-3395ed80-3533-11eb-8920-486b57bd5ec9.png)



**Requirements:**\
This project is written completely in java code, you can execute this project either on Ed CSE 143 workSpace or 
other environments that can execute java's code.

**Setup:**\
If you run it on the **Ed CSE 143 workspace**, all you need to do is start a terminal, switch to the work folder
(`cd work`) within it, then enter the command `javac Main.java && java Main; rm *.class`.

**DataSource:**\
All the data in the resource files are in *csv format* and located in the resource folder, which includes one student
dataset and two course datasets. 
- student dataset: This is a randomly generated dataset which we used to test our database. 
- course dataset: Two course datasets. One for CSE department, and the other one is all the courses from UW Seattle Campus.

**User Guide**
The program interaction consists mainly of running commands from a menu interface. The commands available are as follows:
- create\
  this is a command for the user to create a student and save it in the student dataset under the given format. Including the
  length and the rule for each field.
  + The Rules for command **`create <firstName> <lastName> <id> <phoneNumber> <email> <state>`**
    * id: should be **7** digits and also no duplicates are allowed in the database. 
    * email: no duplicates are allowed in the database.
    * state: should be in **2** or **3** Capital Letters.

Example: `create` `John` `Wick` `1234567` `232323232` `DontTouchmyDog@uw.edu` `NYC`    
  
- search\
  this is a command for the user to search for courses relevant to the course topic
  + command form **`search <course>`**
    * the `<course>` can have multiple tokens, but they each need a white space between them.
  
Example: `search` `CSE` `143` will find the course, however `CSE143` will not find any relevant result

- addCourse\
  this is a command to add the course to a student's course list.
    + command form **`addCourse <id> <course>`**
    
Example: `addCourse` `1234567` `3052`

- findPartner\
  this is a command to find students who are all taking the course represented by the course id.
  + command form **`findPartner <courseId>`**
    
Example `findPartner` `3052` will show all the students who also take this 3052 *(CSE 143)* class.
- quit\
  this is a command to terminate the app.
  
- printCourse\
  this is a command to visualize all the course in the database, however the course might be too many to print in the
  console. It will crash after printing out too many lines of result. 
  So, in order to see all the result we could change to a relatively smaller course database 
  which only includes about 160 courses in the cse department.
    + command form **`printCourse`**
    + The way to change the database:
        we open the main file name `Main.java` and then we replace line 17\
      `private static final String COURSE_DATASET = "./resource/allCourse.csv";` to\
      `private static final String COURSE_DATASET = "./resource/cseCourse.csv";`
  
- printStudent\
this is a command to visualize all the students in the database.
  + command form **`printStudent`**
