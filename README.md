# Project3-UniDigital

## Project Title: Digital Student Card System
 •	Back-End: Java Spring Boot (RESTful API, MySQL Database)
 
 •	Front-End: HTML/CSS/JavaScript, Figma for UI mockups

# 1. Briefly Problem Statement and Solution.
## 1.1 Problem Statement
At many educational institutions, including CPUT, students are required to collect physical student cards to access campus services and facilities. The current process for issuing these cards is inefficient and outdated. Students are forced to stand in long queues at printing facility, often due to slow systems and a lack of digital infrastructure. These bottlenecks lead to wasted time, student frustration, and delayed access to campus buildings.
 
Moreover, there's no temporary or digital alternative available while students wait for their physical cards. This creates accessibility issues, especially for students needing urgent access to secure facilities or services.

## 1.2 Proposed Solution Breakdown
To address the inefficiencies in the current student card issuance system, this project proposes the development of a comprehensive web-based application using Java Spring Boot for the backend and modern web technologies for the frontend. The application will manage both digital and physical student card services, significantly reducing the need for in-person visits.
Overall, the solution aims to digitize and modernize the student card issuance process. It reduces wait times, improves user experience, and offers students the flexibility of a digital identity during and after the application process. It also empowers the institution to scale card services without investing in additional hardware or expanding physical infrastructure.

 # Wireframes and UI Designs.
• Each member has already hand-drawn wireframes and then later converts them into digital mockups using Figma. 
• Now we design focus is on intuitive navigation, clean layout, and mobile responsiveness user interfaces.

## 2.1 Link Account & Login Pages
Since we don’t have access to CPUT database we created a “Link Account page” in our application that allows the user to link his existing details to the application and then he can access the application by proceeding to redirect to the Login page. With the link Account page, we will then be able to store the user details in our own database.
<img width="211" height="440" alt="Screenshot 2025-05-23 143653" src="https://github.com/user-attachments/assets/7eb72e0d-b9ff-4463-bbfc-85bd015e1190" />
<img width="211" height="440" alt="Screenshot 2025-05-23 143719" src="https://github.com/user-attachments/assets/9c319900-4a1c-4120-bd5a-4b675037cd72" />

## 2.2 Home Interface
The “Home Interface” is a page where the student will be redirected to after signing in to the app, the home page offers its own redirections from it where students can redirect to other pages/interfaces achieving its main purpose of many home pages. We designed user friendly interface that is not complicated according to student feedback since they would prefer a home page like this one.
 <img width="211" height="448" alt="Screenshot 2025-05-23 143744" src="https://github.com/user-attachments/assets/7a5b7e82-4753-4dd2-b60b-ccc0e3b6c0d2" />


## 2.3 Register Card
This “Register card page” is an interface that is mandatory for the application process, Students will have to apply for their physical card online by filling out pre-populated forms based on their account data and uploading an appropriate photo. This eliminates manual paperwork and reduces administrative workload.
<img width="211" height="448" alt="Screenshot 2025-05-23 144059" src="https://github.com/user-attachments/assets/474d4581-08a7-4736-bc89-3615548135f6" />

 
## 2.4 Edit Profile interface
This “Profile page” is an interface that allows students to edit their personal details in case they changed their contact phone number, relocated and want to change their registered address and even change their passwords for their security reasons.
<img width="211" height="448" alt="Screenshot 2025-05-23 144008" src="https://github.com/user-attachments/assets/e342b485-34b9-48ac-9b75-71dd93d49670" />

## 2.5 Card and NFC Tap Session Pages
“Card page interface” is a feature that provides an overview of the remote card from front and back view and have features where you can turn on and off related to the student card, while the “NFC Tap session interface” is a crucial feature in the application because that would be the most used feature since student will use this feature almost everyday, this allows the students to gain access to the building using their phone, Student will have limited time of 2 minutes before the session expires then he would have to reload to start the session again.
<img width="211" height="448" alt="Screenshot 2025-05-23 143825" src="https://github.com/user-attachments/assets/330a5647-84e6-4424-b35a-c7143deb71fd" />
<img width="211" height="448" alt="Screenshot 2025-05-23 143804" src="https://github.com/user-attachments/assets/04a381f2-4834-4c86-9851-76f524a9a638" />

## 2.6 Map feature Interface.
This “Map page” is a feature in our application that Student can use to see locations where they can use their cards to access buildings, this feature will be helpfully as student will be able to use the app to access the map and able to see the areas with NFC System in the campus buildings. All locations with NFC Reader systems on the campus are marked with a red location icon.
 <img width="211" height="448" alt="Screenshot 2025-05-23 144027" src="https://github.com/user-attachments/assets/a836f8f3-c10c-4b02-aa62-1a5cbe0b6989" />

## 2.7 Notification and Track Status Page
This Status page allows students to view their status about the card application whether it is pending, approved or ready for collection. Students can check their status application of their physical card after redirecting from “Notification page”. The status track is for the physical card, student can use their remote card to access campus while waiting for their physical card.
<img width="211" height="448" alt="Screenshot 2025-05-23 143849" src="https://github.com/user-attachments/assets/5aae371d-1bd9-425d-a117-72f81e624439" />
<img width="211" height="448" alt="Screenshot 2025-05-23 143907" src="https://github.com/user-attachments/assets/587fc88a-c9e0-44a6-a229-f980dbcf3651" />

  
## 2.8 Collection Page
The “Collection page” allows students to book or schedule a pickup date to fetch their physical card at the pickup location provided according to where each campus usually uses for student card application or collection venue. The aim of the interface is to remind students to able to have a pickup date for the physical card since many students said they would be lazy to go and pick the card if the remote card application is implemented according to the feedback we got from them.
<img width="211" height="448" alt="Screenshot 2025-05-23 143929" src="https://github.com/user-attachments/assets/8f5f3c9a-2d56-4291-9b21-a5bd64d2ba87" />
<img width="211" height="448" alt="Screenshot 2025-05-23 143944" src="https://github.com/user-attachments/assets/73255744-fba8-4472-8709-495676f29c5a" />

## 2.9 Data Design for database
The group will use DDD For database backend using Java Spring Boot. Creating entities using Domain-Driven Design and the Spring Boot JPA APIs. 
## Entities Include:
•	Student
•	DigitalCard
•	NFCSession
•	Notification 
## Design Notes:
•	All entities will be normalized to avoid redundancy
•	student_id is the primary foreign key in most relationships
•	Spring Data JPA is used to interact with the database

