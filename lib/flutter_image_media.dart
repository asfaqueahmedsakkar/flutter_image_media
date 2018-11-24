import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';
import 'package:flutter_image_media/ImageAlbum.dart';
import 'package:simple_permissions/simple_permissions.dart';
import 'dart:convert';

class FlutterImageMedia {
  static const MethodChannel _channel =
      const MethodChannel('flutter_image_media');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<List<ImageAlbum>> get getImages async {
    bool permission =
        await SimplePermissions.checkPermission(Permission.ReadExternalStorage);

    String data = "";
    if (permission)
      data = await _channel.invokeMethod('getAllImages');
    else {
      PermissionStatus permissionStatus =
          await SimplePermissions.requestPermission(
              Permission.ReadExternalStorage);
      if (permissionStatus == PermissionStatus.authorized)
        data = await _channel.invokeMethod('getAllImages');
      else
        exit(0);
    }

    final out = json.decode(data) as List;

    List<ImageAlbum> imagesWithAlbum = new List();

    for (int i = 0; i < out.length; i++) {
      imagesWithAlbum.add(ImageAlbum.fromJSON(out[i]));
    }

    return imagesWithAlbum;
  }
}
