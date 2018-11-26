class ImageAlbum {
  String _folderName;
  List<String> _imagePaths;

  ImageAlbum(this._folderName, this._imagePaths);

  ImageAlbum.fromJSON(Map<String, dynamic> json) {
    _folderName = json['folder'];
    List<dynamic> list = json['images'];
    _imagePaths = list.map((data) => data as String).toList();
  }

  List<String> get imagePaths => _imagePaths;

  String get folderName => _folderName;
}
