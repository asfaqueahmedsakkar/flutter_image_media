```dart
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter_image_media/image_album.dart';
import 'package:flutter_image_media/flutter_image_media.dart';
import 'package:flutter_image_media/image_album_with_thumbnail.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
  }

  Future<void> getAllImagesWithThumbnail() async {
    List<ImageAlbumWithThumbnail> images =
            await FlutterImageMedia.getAllImagesWithMiniThumbnail;
    
    for (ImageAlbumWithThumbnail imageAlbum in images) {
      print("${imageAlbum.folderName} ${imageAlbum.imagePaths.length} ${imageAlbum.thumbnailPaths.length}");
    }
  }

  Future<void> getAllImages() async {
    List<ImageAlbum> images = await FlutterImageMedia.getImages;
    for (ImageAlbum imageAlbum in images) {
      print(imageAlbum.folderName);
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
      ),
    );
  }
}
```
