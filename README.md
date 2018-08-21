# once
a library that makes it easier to implement code that should only ever run once in the apps entire lifetime 

## Usage

add jitpack to your project dependency
```
repositories {
     maven { url='https://jitpack.io'}
}    
```

add the library to your module dependency
```
dependencies {
    implementation 'com.github.mundia416:once:{LATEST_RELEASE}'
}
```

to use, simply create a once object, give it an id and pass a RunOnce interface in the execute method
```
  Once(context,id).execute(object : RunOnce{
                override fun run() {
                   //your code that will only ever execute once in your apps lifetime
      }
  })
  ````
  
  the id enables you to have different blocks of code in different parts of the app run id.

## Features to Come
-have code that will execute every time the app is updated

## Author

Mundia Mundia 



## License

Copyright 2018 Mundia Mundia

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.



