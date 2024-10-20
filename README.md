# Rick and Morty

Rick and Morty Explorer is an Android application that fetches data from the [Rick and Morty API](https://rickandmortyapi.com/) and displays a list of characters from the show. The app is built using XML layouts and follows the MVVM architecture pattern. It includes features for listing characters, viewing detailed information for each character, and saving favorite characters locally.

## Features 🚀
- Character Listing: View a list of characters from the Rick and Morty universe.
- Character Details: View detailed information about each character.
- Favorites: Save characters to a favorites list for quick access.
- Pagination: Supports pagination for loading large sets of data efficiently.
- Multiple theme support: You can change the theme in the application settings

## Screenshots 📱
Below are some screenshots of the app:

## Technologies and Libraries Used 🛠
- Retrofit: For making API requests to the Rick and Morty API.
- Coroutines: For managing background tasks such as API requests.
- Room: For saving and retrieving favorite characters locally.
- Pagination: For loading paginated data from the API.
- Fragment Navigation: For handling navigation between different screens.
- Glide: For loading character images.

## Architecture Details 🛠️

- MVVM Architecture:
  <br>
1- ViewModel: Manages the UI state and handles business logic.
  <br>
2- Repository: Fetches data from the Rick and Morty API or local Room database.
  
- Coroutine Support: API calls and database operations are handled asynchronously with Kotlin Coroutines.
- Fragment Navigation: The app uses the Fragment Navigation component to move between the character list, detail screen, and favorites.

## Setup and Run

1- Clone the repository:
```bash
git clone https://github.com/your-username/rick-and-morty.git
```
2- Open the project in Android Studio and run it on your device/emulator.

## Contributing
Feel free to contribute by:
- Forking the repository.
- Working on a new feature or bug fix.
- Submitting a pull request.
