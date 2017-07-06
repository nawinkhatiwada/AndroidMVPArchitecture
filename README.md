# Android MVP Architecture
This repository consists of basic android MVP architecture without using Dagger, RxJava, RxAndroid. In this repository there is Retrofit, OkHttp for API purpose. AndroidMVPArchitecture is developed for beginners who are well known about basic android application development and wants to use trending MVP Architecture in the project, but are unknown about RxJava, RxAndroid and searching for ways to implement MVP without using RX and Dagger. The architecture is done in such a way that developers can easily implement Rx and Dagger, after knowing about it, in this project.

  **MVC vs MVP**
  
  
![MVC vs MVP](http://www.techyourchance.com/wp-content/uploads/2015/06/MVC_MVP.png)
 
 # What is MVP?
 
![MVP Pattern](https://i-msdn.sec.s-msft.com/dynimg/IC340102.png)

MVP is a user interface architectural pattern engineered to facilitate automated unit testing and improve the separation of concerns in presentation logic:

The model is an interface defining the data to be displayed or otherwise acted upon in the user interface.
The view is a passive interface that displays data (the model) and routes user commands (events) to the presenter to act upon that data.
The presenter acts upon the model and the view. It retrieves data from repositories (the model), and formats it for display in the view.

**source:** https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter

**Feel free to submit the issues and suggestions for improving the coding standard :)**



# Important Links:
https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf

https://github.com/googlesamples/android-architecture

https://code.tutsplus.com/tutorials/an-introduction-to-model-view-presenter-on-android--cms-26162

https://stackoverflow.com/questions/2056/what-are-mvp-and-mvc-and-what-is-the-difference

https://msdn.microsoft.com/en-us/library/ff649571.aspx

