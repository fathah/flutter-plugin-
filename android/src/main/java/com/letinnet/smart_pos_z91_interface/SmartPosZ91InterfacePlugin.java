package com.letinnet.smart_pos_z91_interface;
import androidx.annotation.NonNull;
import android.text.Layout;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;
import com.szzcs.scan.OnActivateListener;
import com.szzcs.scan.SDKUtils;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;

/** SmartPosZ91InterfacePlugin */
public class SmartPosZ91InterfacePlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private MethodChannel channel1;
  private DriverManager mDriverManager;
  private Printer mPrinter;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "smart_pos_z91_interface");
    channel.setMethodCallHandler(this);
    channel1=new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "name");
    channel1.setMethodCallHandler(this);

    mDriverManager = DriverManager.getInstance();
    Sys mSys = mDriverManager.getBaseSysDevice();
    int i = mSys.sdkInit();
    mPrinter = mDriverManager.getPrinter();

  }
  

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("print")) {

     String name = call.argument('name');

      PrnStrFormat format = new PrnStrFormat();
      format.setTextSize(30);
      format.setAli(Layout.Alignment.ALIGN_CENTER);
      format.setStyle(PrnTextStyle.BOLD);
      format.setFont(PrnTextFont.CUSTOM);
   //   format.setPath(Environment.getExternalStorageDirectory() +  "/fonts/simsun.ttf");
      mPrinter.setPrintAppendString(name, format);
     // format.setTextSize(25);
      //format.setStyle(PrnTextStyle.NORMAL);
      format.setAli(Layout.Alignment.ALIGN_NORMAL);
      mPrinter.setPrintAppendString(" ", format);
      mPrinter.setPrintAppendString("MERCHANGT NAME:" + " Test ", format);
      mPrinter.setPrintAppendString("MERCHANT NO:" + " 123456789012345 ", format);
      mPrinter.setPrintAppendString("TERMINAL NAME:" + " 12345678 ", format);
      mPrinter.setPrintAppendString("OPERATOR NO:" + " 01 ", format);
      mPrinter.setPrintAppendString("CARD NO: ", format);
      format.setAli(Layout.Alignment.ALIGN_CENTER);
      format.setTextSize(30);
      format.setStyle(PrnTextStyle.BOLD);
      mPrinter.setPrintAppendString("6214 44** **** **** 7816", format);
      format.setAli(Layout.Alignment.ALIGN_NORMAL);
      format.setStyle(PrnTextStyle.NORMAL);
      format.setTextSize(25);
      mPrinter.setPrintAppendString(" -----------------------------", format);
      mPrinter.setPrintAppendString(" ", format);
      mPrinter.setPrintAppendString(" ", format);
      mPrinter.setPrintAppendString(" ", format);
      mPrinter.setPrintAppendString(" ", format);
      int printStatus = mPrinter.setPrintStart();
      result.success("Print Status " + printStatus);
      
      //result.success("Android xyz");
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
