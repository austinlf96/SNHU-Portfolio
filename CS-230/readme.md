# The Gaming Room - Draw It or Lose It

The Gaming Room is a simulated client that sought to migrate their android app, a game called Draw It or Lose It, to several other platforms utilizing a web-based approach. They requested assistance with the back-end development, specifically the server environment and the physical server setup. Properly devising the environment required that the game could support the following features:
- A game will have the ability to have one or more teams involved
- Each team will have multiple players assigned to it
- Game and team names must be unique to allow users to check whether a name is in use when choosing a team name
- Only one instance of a game can exist in memory at any given time by creating unique identifiers for each instance of a game, team, or player
This assignment called for multiple aspects of development to be addresses and completed. The first segment of the assignment was creating the foundational code for the game that met the requirements above. The second segment was creating a software design document. 
## Software Design Document Achievements
The software design document thoroughly addresses the features that macOS Server, Linux, Windows Server, and mobile device servers offer by using a table comparison. The domain model and recommendation sections of the software design document are also comprehensive and provide sufficient detail to assist The Gaming Room with deciding how they would like to set up their physical server. 
### How a Software Design Document Helps
A common phrase in the industry is "Weeks of programming can save you hours of planning." It is an ironic phrase meant to highlight the sifgnificance of planning in the production process. Creating a software design document assisted with coding the back-end in the following ways:
- The domain model provided clarity about the interactions between the various classes associated with the program
- Design constraints informed decisions about the coding language that would be utilized, as well as the tests that should be created to ensure the client's satisfaction with the software
- The executive summary guided the overall project so that any individual viewing the software design document could gather an inclination as to what they could work on, provided they communicate with the rest of the team about the team's current progress
### Potential Software Design Document Revisions
Notably, the system architecture view was not completed as part of this project. One revision that would be impactful is filling out this section, but additional information will need to be gathered from The Gaming Room in order to fulfill this requirement. 
### Translating User Needs
An important aspect of designing software is not only to consider what the client is requesting, but also what the end-user needs. A couple identified user needs are:
- The ability to play the game independently of the platform, which is why The Gaming Room requested a game to be developed for browsers rather than each operating system. 
- The ability to sign in to the game, which is why an authorization and authentication system was implemented in the game programming, as found in the *gameauth* folder. 
It is important to consider end user desires because even if the software meets the client's requirements, the programmer(s) will still be held responsible for customer satisfaction to an extent. 
### Software Design Document Approach
#### Considerations
Designing software requires considering numerous aspects of the program such as:
- Security
- Legal compliance
- Ethical programming
- User-friendly development
- Scalability
- Operation efficiency
- Intuitiveness
- Program interactions
- Memory management
#### Techniques
There are multiple resources available for the planning stage of any software development project, including the following components
##### Tools
- UML Diagrams
- Design patterns (behavioral, creational, and structural)
##### Strategies
- Top-Down Design
- Bottom-up Design
## Take away
The techniques identified above can be applied to future projects in software design for both academic and real-world purposes. UML diagrams and design patterns will undoubtedly be a couple of the more useful tools that I will use in the future. Top-down and bottom-up design are also likely to be utilized in the planning stages, but rarely is it a good idea to utilize *only one* method of design strategy. Design works best when applying both design strategies to a comprehensive program design. 
