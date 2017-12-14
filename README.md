**Download sample apk from here**

[![apk](https://img.shields.io/badge/apk-download-orange.svg)](https://play.google.com/store/apps/details?id=com.nawin.androidmvparchitecture)

# Android MVP Architecture
Before starting, I assume
1. you know basics of **android** application development
2. Heard about **Android MVP Architecture.**
3. And are eager to learn **Android MVP Architecture** with different types of implementation. 

**AndroidMVPArchitecure Includes:**
1. Three branches viz  **master**, **mvp_with_rx_and_dagger** and **mvp_with_rx_and_kotlin**
2. **master** -> Implementation of MVP Architecture without using Dagger, RxJava, RxAndroid
3. **mvp_with_rx_and_dagger** -> Implementation with Dagger2, RxJava, RxAndroid
4. **mvp_with_rx_and_kotlin** ->  Implementation with Dagger2, RxJava, RxAndroid, Kotlin
5. **Unit testing with Mockito**

  **MVC vs MVP**
  
![MVC vs MVP](http://www.techyourchance.com/wp-content/uploads/2015/06/MVC_MVP.png)
 
 # What is MVP?
 
![MVP Pattern](http://gwb.blob.core.windows.net/rajeshpillai/figure_1.jpg)

MVP is a user interface architectural pattern engineered to facilitate automated unit testing and improve the separation of concerns in presentation logic:

The model is an interface defining the data to be displayed or otherwise acted upon in the user interface.
The view is a passive interface that displays data (the model) and routes user commands (events) to the presenter to act upon that data.
The presenter acts upon the model and the view. It retrieves data from repositories (the model), and formats it for display in the view.

**source:** [Model–view–presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)

# Important Links:
[RxJava](https://github.com/ReactiveX/RxJava/wiki)

[RxAndroid](https://github.com/ReactiveX/RxAndroid/wiki)

[MVP for Android](https://antonioleiva.com/mvp-android/)

[Dagger2](https://google.github.io/dagger/)

[Kotlin for Android](https://kotlinlang.org/docs/reference/android-overview.html)

[Unit tests with Mockito](http://www.vogella.com/tutorials/Mockito/article.html)

[Model-View-Presenter: Android guidelines](https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf)

[google samples](https://github.com/googlesamples/android-architecture)

[An Introduction to Model View Presenter on Android](https://code.tutsplus.com/tutorials/an-introduction-to-model-view-presenter-on-android--cms-26162)

[MVP vs MVC](https://stackoverflow.com/questions/2056/what-are-mvp-and-mvc-and-what-is-the-difference)

[The Model-View-Presenter (MVP) Pattern by Microsoft](https://msdn.microsoft.com/en-us/library/ff649571.aspx)

--------------------------------------------------------------------------------------------
# Attribution

Thank you [Subhash Acharya](https://github.com/subhasha1) for your continuous guidance and support to improve the coding standard. I sincerely appreciate the time you spent and assistance you have provided me by reviewing my code and recommending me for achieving the best result.

--------------------------------------------------------------------------------------------

**Feel free to submit any type of issues and suggestions for improving the coding standard**

**Happy Coding!!!** ![](https://github.com/nawinkhatiwada/AndroidMVPArchitecture/blob/master/app/src/main/res/drawable/happiness.png)

# License
```text
Copyright 2017 Nawin Khatiwada

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

