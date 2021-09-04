<h1>Problem Statement:</h1>
Develop an application that allows a user to select a country and display a
small amount of information about that country, including displaying the
country’s flag. The task has been left deliberately open so that you can
decide you approach, architecture, UX and what features to include.
The application should make use of the REST countries API

<h1>Approach Selected</h1>
I have chosen **MVVM with clean architecture** for this project because it
provides loose coupling of things and testing can also be performed with ease. I have also used **Dagger**
to demonstrate my knowledge on dependency injection.

As clean architecture has mainly 3 layers
1. domain
3. data
3. Presentation

Our code is divided into above mentioned packages we can say
1. **domain -- Models & RepoInterface will be places**
2. **data  -- API Service and RepoImpl are places**
3. **ui   -- Activities, Fragments, ViewModels are placed**
4. **di  -- Dagger related stuff like  component and modules are placed**

<h3>Flow</h3>
On clicking the app icon it will open an splash screen which uses coroutine scope for delay.
 On delay completion it will take the user to home screen(CountriesListActivity) which has a fragment container added to it.
 By default CountriesListFragment will be launched which has a list of countries loaded from an API and a search bar for searching countries with name, native name, short code, etc.
 On selecting a country user will navigate to Country details page where few details of country like flag, name,
 currency, languages are displayed. 
 
 <h3>APK Download</h3>
 To Test the app you can download the APK here
https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/app-debug.apk

<h3>Code</h3>
The app consists of
 1. Splash Screen
 2. CountriesListActivity
 3. CountriesListFragment where countries list is displayed with an option to search
 4. CountryDetailsFragment for displaying details of selected country

<h3>Edge cases</h3>
I have covered edge cases like no Internet availability, No results for the search, etc

<h3>App Screenshots</h3>
Check-out the folder https://github.com/pavan5208/android_rebtel_countries/tree/master/refrences

![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/1_splash.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/2_list.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/3_details.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/4_search.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/5_details.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/6_no_network.jpg)
![This is an image](https://github.com/pavan5208/android_rebtel_countries/blob/master/refrences/7_no_results.jpg)

**Note**: The API response is a bit slow which is based on server.
 Also I haven't covered any test cases as it was not mentioned
