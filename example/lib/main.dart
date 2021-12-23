import 'dart:ffi';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:smart_pos_z91_interface/smart_pos_z91_interface.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _name = "";

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String name="";
    String platformVersion = '...';
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    /*try {
      platformVersion =
          await SmartPosZ91Interface.platformVersion ?? 'Unknown platform version';
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }*/

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;
    setState(() {
      _name = name;
    });
    setState(() {
      _platformVersion = platformVersion;
    });

    @override
    Widget build(BuildContext context) {
      // TODO: implement build
      throw UnimplementedError();
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text(''),
        ),
        body: Column(
          children: [
            TextField(
              onChanged: (val) {
                 setState(() {
       _name=val;
    });
             
              },
            ),
            ElevatedButton(
              onPressed: () async {
                try {
                  _platformVersion = await SmartPosZ91Interface.print ??
                      'Unknown platform version';
                  
                } on PlatformException {
                  _platformVersion = 'Failed to get platform version.';
                }
              },
              child: Text("Click"),
            ),
          ],
        ),
      ),
    );
  }
}
