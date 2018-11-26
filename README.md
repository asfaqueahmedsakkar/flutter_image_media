# flutter_image_media

A new Flutter plugin for getting image list from the phone (only android).

## Getting Started

To use import 
 ```dart
  import 'package:flutter_image_media/flutter_image_media.dart';
```
<<<<<<< HEAD

To get the list of ImageAlbum 
 ```dart
  Liat<ImageAlbum> imageAlbums=FlutterImageMedia.getImages;
```

The `ImageAlbum` contains folder/album name and the list of path of the images.
this can be assesed using
```dart
  ImageAlbum imageAlbum;
  String album=imageAlbum.folderName;
  List<String> imagePaths=imageAlbum.imagePaths;
```

To get the list of ImageAlbumWithThumbnailPath
 ```dart
 List<ImageAlbumWithThumbnail> images = FlutterImageMedia.getAllImagesWithMiniThumbnail;
```

The `ImageAlbumWithThumbnail` contains folder/album name, the list of path of the thumbnail and the list of path of the images.
this can be assesed using
```dart
  ImageAlbumWithThumbnail imageAlbum;
  String album=imageAlbum.folderName;
  List<String> imagePaths=imageAlbum.imagePaths;
  List<String> thumbnailPaths=imageAlbum.thumbnailPaths;
```

=======

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
>>>>>>> bee91dc67295aa11a5bcd5b63250b05bf81c439d
