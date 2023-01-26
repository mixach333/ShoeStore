# The Shoe Store

A small project to keep track of your shoes, where you keep its list and can add new shoes. At the start, the app populates the default shoe list for each user.

##


<img src="https://img.shields.io/badge/kotlin-1C2149?style=for-the-badge&logo=kotlin&logoColor=orange"/>  <img src="https://img.shields.io/badge/Clean-Architecture-1C2149?style=for-the-badge&logo=kotlin&logoColor=orange"/>  <img src="https://img.shields.io/badge/Two way-data binding-1C2149?style=for-the-badge&logo=kotlin&logoColor=orange"/>

<img src="https://img.shields.io/badge/live-data-1C2149?style=for-the-badge&logo=google&logoColor=blue"/>  <img src="https://img.shields.io/badge/MVVM-SharedViewModel-1C2149?style=for-the-badge&logo=google&logoColor=blue"/>  <img src="https://img.shields.io/badge/Nav-Component-1C2149?style=for-the-badge&logo=google&logoColor=blue"/>  <img src="https://img.shields.io/badge/Card-View-1C2149?style=for-the-badge&logo=google&logoColor=blue"/>

<img src="https://img.shields.io/badge/git-1C2149?style=for-the-badge&logo=github&logoColor=#181717"/>  <img src="https://img.shields.io/badge/github-1C2149?style=for-the-badge&logo=github&logoColor=#181717"/>
 
<img src="https://img.shields.io/badge/Dynamic ScrollView-1C2149?style=for-the-badge&logo=ScrollReveal&logoColor=7F52FF"/>  <img src="https://img.shields.io/badge/Code generated View-1C2149?style=for-the-badge&logo=v&logoColor=7F52FF"/>  <img src="https://img.shields.io/badge/Toolbar-1C2149?style=for-the-badge&logo=Bandcamp&logoColor=7F52FF"/>  <img src="https://img.shields.io/badge/Custom registration and login-1C2149?style=for-the-badge&logo=lospec&logoColor=7F52FF"/>

##

## Project consists of five screens.

### 1. Login screen: Email and password fields and labels plus register and login buttons

<p align="center"><img src="https://i.postimg.cc/Wz721hGx/Screenshot-20230126-174925.png"/></p>

- Registered users are kept in SharedViewModel, without Firebase or Room, in this case it was not needed.

- Login button checks if the login and password fields corresponds to some user, if that so - navigates to the welcome screen.

- Registration requires from the user proper e-mail format and password to be at least: 8 symbols, 1 lowercase letter, 1 uppercase letter and 1 special symbol, 1 digit.

- Logging and registration logic is encapsulated in separate Use Cases in it's invoke() methods.

- It's impossible to get back to this screen using back-arrow navigation, only "logout" menu button on listing screen can do that.

##

### 2. Welcome onboarding screen

<p align="center"><img src="https://i.postimg.cc/Hn6fN54W/Screenshot-20230126-174937.png"/></p>

- The user is navigated to this screen when successfully logged in
- Clicking the "GO TO INSTRUCTIONS" button navigates the user to screen with instructions.

##

### 3. Instructions onboarding screen

<p align="center"><img src="https://i.postimg.cc/G2q73RBq/Screenshot-20230126-174945.png"/></p>

- Short description of what the app does.

##

### 4. Shoe Listing screen with log out menu button

<p align="center"><img src="https://i.postimg.cc/PfvY5h8J/Screenshot-20230126-175158.png"/></p>

- The main screen of the app. It has pre-populated list of default shoes, if the user is new.
  - If the user was registered before, his own list of shoes is kept in SharedViewModel.
- The creating each CardView logic is encapsulated in separate Use Case called CreateCardViewForShoeUseCase in its invoke() method.
- The main View to display the list of shoes was chosen as ScrollView, not RecyclerView, to practice the manual code-based generating the layout.
- Clicking the FAB navigates the user to a new screen, called ShoeDetail. 
- The screen has logout menu button, which navigates the user back to login screen.

##

### 5. Shoe Detail screen for adding a new shoe

<p align="center"><img src="https://i.postimg.cc/Njq4cFJQ/Screenshot-20230126-175151.png"/></p>

- The user can do 2 actions: 
  - Click the button "CANCEL" which navigates the user back to shoe listing screen.
  - Enter the proper data for Shoe fields, such as Shoe name, size, description, manufacturer. 
    - If one of the field is blank or incorrect, the user will see the Toast with this information.
    - If the entered information is valid, the user clicks "CONFIRM" and is being navigated to the listing screen with new shoe added to top of the list.
- Layout is used with two-way data binding with Static Inverse Method held in separate Use Case, which converts Double to String and String to Double.

##

### Navigation is organized using navGraph as follows:

<p align="center"><img src="https://i.postimg.cc/Vv9Vytq0/Screenshot-2.jpg"/></p>
