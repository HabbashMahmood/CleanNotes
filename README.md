
![Logo](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/app_icon.png?raw=true)

# Clean Notes

'Clean Notes' is a simple note-taking 📝 android application built to describe the use of Jetpack Compose with Clean Architecture Design pattern in Android development. Made with love ❤️ by HabbashMahmood

## Screenshots

Light Theme

<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Light%20Mode/Spash%20Screen.png?raw=true)</kbd>
<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Light%20Mode/Landing%20Screen%20Filled.png?raw=true)</kbd>
<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Light%20Mode/Update%20Note%20Screen.png?raw=true)</kbd>

Dark Theme

<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Dark%20Mode/Spash%20Screen.png?raw=true)</kbd>
<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Dark%20Mode/Landing%20Screen%20Filled.png?raw=true)</kbd>
<kbd>![App Screenshot](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/Dark%20Mode/Update%20Note%20Screen.png?raw=true)</kbd>

## Built With

'Clean Notes' is written in with Clean Architecture Design pattern.

Some other technologies used in this project:

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Hilt](https://dagger.dev/hilt/) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs. 
  - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors. 
  - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous version of a Sequence, a type of collection whose values are lazily produced.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.


## Package Structure

```
## Clean Architecture

com.habbashmahmood.clean_note_app/  # Main Package

├───core
│   ├───presentation
│   │   ├───components
│   │   └───util
│   └───setup
│       └───navigation
├───di
├───feature_note
│   ├───data
│   │   ├───data_source
│   │   └───repository
│   ├───domain
│   │   ├───model
│   │   ├───repository
│   │   ├───use_case
│   │   └───util
│   └───presentation
│       ├───add_edit_note
│       │   └───components
│       ├───notes
│       │   └───components
│       └───search_note
│           └───components
├───feature_settings
│   └───presentation
│       └───components
└───ui
    └───theme
```
'Clean Notes' is written in with Clean Architecture Design pattern. 

![Clean Architecture Flow Diagram](https://github.com/HabbashMahmood/CleanNotes/blob/master/screenshots/clean%20architecture%20flow%20diagram.png?raw=true)

## Features

- Light/dark mode toggle
- Fullscreen mode
- No permissions required
- Support for Lollipop devices and up
- Color notes for quick organisation
- Filter notes on basis of Title, Date and Color
- Order notes by Ascending and Decending Order
- Simple minimilistic design
## Future Roadmap

- Auto save
- Pin notes to always keep them at the top
- Label notes for quick organisation
- Archive notes to keep them around, but out of your way
- Export notes as TXT, XML, JSON, HTML or PDF files with formatting
- Create rich text notes with support for bold, italics, mono space and strike-through
- Add clickable links to notes with support for phone numbers, email addresses and web urls
- Search notes
- Settings screen
## Contact
Have an project? DM us at 👇

Drop a mail to:- ch.habbash@gmail.com


## Feedback

If you have any feedback, please reach out to us at ch.habbash@gmail.com


## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/) 

```
MIT License

Copyright (c) 2022 Habbash Mahmood

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
