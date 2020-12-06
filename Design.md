
CSE 143 Project : Design Decision & Implementation
===

### Author: Yangjie Lin, Ryan Smit, Jason Zheng

![cse 143 uml-1](https://user-images.githubusercontent.com/50006786/101194012-3e737f80-3612-11eb-9ed2-82937c5b632f.png)

**Preliminary Thought & Motivation:**\
&emsp; At first, we were trying to design a simple application to save personal information. We then decided we wanted to make our application more useful overall. 
We know that there are a lot of frameworks and ways to store different type of database not only in a standard RDBMS (Relational Database Management Systems) form but also in a JSON form which is a more flexible form, but then we need to write code in sequential query language which is beyond this course. 
So we ended up storing information in a naive way and read those databases from raw files, such as text and CSV files. 
It may not be good management on storing databases by reading/writing a local file if we are looking to scale or run a big system, but this is a good way to utilize what we have learned in this class.\
&emsp; As for the problem, we are looking to solve it by finding a way to interact between different database storage.
Every quarter at UW, we use a search engine to search classes by SLN codes, course keyswords, etc. This quarter, we learned how to create our own search engine, so we started off by implementing a search engine in our application. After completing the search engine we figured it would be more useful if a user can search people taking the same class as them, and being able to contact them and reach out though the student database.\
**Design of Different Classes & Function Implementation:**\
&emsp; First we need to instantiate Student and Course.
Student instance includes fields such as `firstName`, `lastName`, `email`, `phone`, `id`, `state` .
To record what courses students were taking, we also add a `courselist` field, so that we could implement a findPartner method of a searchEngine class.\
&emsp; From the aspect of designing the database, we hope the information we store in the StudentDatabase has some restrictions without confusion. We add some restrictions for checking to see if the clients input is in similar fashion of how we defined a primary key to ensure unique fields when we create
a table in the database system.\
&emsp; For StudentDatabase class, we are adding courses in our database. For example, when we are saving the course to the student's course list field,
how do we get the student object? First, we will need to know if the id exists and is able to update the course list field.
So we chose to use a map that is in the format of id as the key and the entire student object as the value.
As for email. We want to make sure there are no duplicate emails, so we have an extra set to save registered emails.
If we search through the entire map to see if there are duplicates, the time complexity will be O(n). Instead of doing that,
We choose to use a set so when calling the contains function, the time complexity will be constant time complexity O(1)\
&emsp; For CourseDatabase class, it is similar to the studentDatabase. We also need to get the course out by course id, and how to generate it is by indexing the course of the map size.
So the first course we put in the courseDatabase will be 1 and so on.\
&emsp; For the search engine, it is just using the information from the student database and course database to get matched results.\
&emsp; For the main interface, it is combining code reference to CSE 414, which is the course one of our teammates previously have taken,
and a method to restrict user type in a pattern, and a method of how to read info from CSV files.
