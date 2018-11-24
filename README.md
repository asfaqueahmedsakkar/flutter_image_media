# flutter_image_media

A new Flutter plugin for getting image list from the phone (only android).

## Getting Started

To use import 
 ```dart
  import 'package:flutter_image_media/flutter_image_media.dart';
```

To get the list of ImageAlbum 
 ```dart
  Liat<ImageAlbum> imageAlbums=FlutterImageMedia.getImages;
```

The `ImageAlbum` contains folder/album name nad the list of path of the images.
this can be assesed using
```dart
  ImageAlbum imageAlbum;
  String album=imageAlbum.folderName;
  List<String> imagePaths=imageAlbum._imagePaths;
```
