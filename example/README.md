```dart
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter_image_media/image_album.dart';
import 'package:flutter_image_media/flutter_image_media.dart';

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
    showAllImages();
  }

  Future<void> showAllImages() async {
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
