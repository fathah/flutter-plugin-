
import 'dart:async';

import 'package:flutter/services.dart';

class SmartPosZ91Interface {
  static const MethodChannel _channel = MethodChannel('smart_pos_z91_interface');
  static const MethodChannel _channel1 = MethodChannel('name');

  static Future<String?> get print async {
    final String? printStatus = await _channel.invokeMethod('print');
    return printStatus;
  }
}
