# ZipCodeApi
App to calculate distances, radius, and locations for US zip codes and CA postal codes.

### zip code by radius
Created a feature module to maintain functionality related to searching for zipcodes by radius.
When th app is opened, app module will start the "ZipcodeByRadiusActivity" activity of the module.

We initially disable the Search button until user provides the zipcode and radius. Once user provides
the details they can click search to see list of items. We show a loading indicator till the response
is obtained.

Once the response is obtained and success, if data is available, we show list of data. If empty, we
show a snack bar saying no zipcodes nearby. If error, we show an error snackbar saying that something
went wrong.

### Bindable Adapter
It uses the Data Binding concept which helps to show different view types in a single recyclerview.

### Concepts/Libraries being used

- Mvvm with Repository and Data Binding.
- Retrofit:For Network Handling
- Gson: For Serialization and Deserialization
- Live Data
- ViewModel
- Kotlin coroutines
- Mockk: For Testing to mock dependencies.

### Dependency maintenance
Currently all the dependencies are being maintained at "root/gradle/support/dependencies.gradle" file.
This file is applied in the app and feature module.