class ImageAlbumWithThumbnail {
  String _folderName;
  List<String> _imagePaths;
  List<String> _thumbnailPaths;

  ImageAlbumWithThumbnail(this._folderName, this._imagePaths);

  ImageAlbumWithThumbnail.fromJSON(Map<String, dynamic> json) {
    _folderName = json['folder'];
    List<dynamic> list = json['images'];
    _imagePaths = list.map((data) => data as String).toList();
    list = json['thumbnails'];
    _thumbnailPaths = list.map((data) => data as String).toList();
  }

  List<String> get imagePaths => _imagePaths;

  String get folderName => _folderName;

  List<String> get thumbnailPaths => _thumbnailPaths;
}
