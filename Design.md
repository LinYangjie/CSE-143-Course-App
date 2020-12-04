
CSE 143 Project : Design Decision & Implementation
===

### Author: Yangjie, Lin, Ryan, Yason

![cse 143 uml-1](https://user-images.githubusercontent.com/50006786/101194012-3e737f80-3612-11eb-9ed2-82937c5b632f.png)

**Preliminary Thought & Motivation**\
&emsp; At first, we are only trying to design an app to save personal information. However, since we
want it to be a useful and solving problem of the world if applicable. We know that there are a lot of frameworks and ways
to store different type of database not in a standard RDBMS (Relational Database Management Systems) form but also in a JSON form which is a more flexible form, but then we will need to write code in sequential query language which is beyond this course. So, we ended up store information in a naive way and read those databases from raw files, such as
text, CSV files. It may not be good management on storing databases by reading/writing a local file if we are looking to scale or
running a big system, but this is a good way to utilize what we have learned in this class.\
&emsp; As for the problem, we are looking to solve it by finding a way to interact between different database storage.
Given that we are using a UW website to register courses based on the SLN code, and searching courses by keywords
every quarter and one of the assessments are also implementing the search engine. So, the first thing we are doing is to
simulate the search engine. Then we found out adding search partner features for a certain class is cool and useful, since
if we want to ask someone privately by directly email them or text them we could just check who is also in this course and try to reach out to them by the student database info.

**Design of Different Classes & Function Implementation:**\
&emsp; First we need to instantiate Student, Course.
Student instance includes fields such as `FirstName`, `LastName`, `Email`, `Phone`, `studentId`.
To record what courses do students took, we also add a `courselist` field, so that we could implement a findPartner in later searchEngine class.\
From the aspect of designing the database, we hope the info we store in the StudentDatabase has some restrictions without confusion. We add some restriction by checking validation of the form client types in the console which is a similar fashion of we defined a primary key to ensure unique fields when we create
a table in the database system.\
&emsp; For StudentDatabase class, we are adding courses in our database. For example, when we are saving the course to the student's course list field,
how do we get the student object? First, we will need to know if the id exists and able to update the course list field.
So we choose to use a map that is in the format of id as the key and the entire student object as the value.
As for email. We hope users do not have the same email registered, so we save an extra set to save all the emails been used.
Since if we check through the entire map to see if there are duplicates, the time complexity will be O(n). Instead of doing that,
I choose to use a set when calling contains function is only a constant time complexity O(1).\
&emsp; For CourseDatabase class, it is similar to the studentDatabase, we also need to get the course out by course id, and how to generate it is by indexing the course of the map size.
So the first course we put in the courseDatabase will be 1 and so on.\
&emsp; For the search engine is just using the information from the student database and course database to get matched results.\
&emsp; For the main interface is combining codes reference to CSE 414, which is the course one of our teammates previously have taken,
and some method to restrict user type in a pattern, and some method of how to read info from CSV files.
