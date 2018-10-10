package com.yizhipin.base.ui.web;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yizhipin.base.R;



/**
 * Created by ${XiLei} on 2018/8/5.
 */
public class YZWebView extends LinearLayout {
    WebView mWebView;
    ProgressBar mProgressBar;
    private String mTitle;
    private Uri mPhoneUri;

    public WebView getWebView() {
        return mWebView;
    }

    private Context mContext;

    private String url;
    private static final String TAG = "YZECWebView";
    //	private String errorHtml = "<html><head><meta charset='UTF-8'></head><body><br><br><br><br><br><br><br><div align='center' style='font-size: smaller'  onclick='window.android.refresh()' ><a href='http://www.baidu.com' style='text-decoration: none'>暂无数据 <br/> 点击调用android方法 </a></div></body></html>";

    //	@JavascriptInterface
    //	public void refresh() {
    //		Toast.makeText(mContext, "js 调用方法", Toast.LENGTH_SHORT).show();
    //	}


    public YZWebView(Context context) {
        this(context, null);
    }


    public YZWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YZWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_webview, this);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示滚动条     
        mWebView.setVerticalScrollBarEnabled(false);//垂直不显示滚动条
    }

    public String getUrl() {
        return url;
    }

    public String getCurrent() {
        return mWebView.getUrl();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void loadUrl(String url) {
        if (TextUtils.isEmpty(url)) {
//            url = "http://blog.csdn.net/gyw520gyw";
            url = "";
        }
        initWebview(url);
    }

    private void initWebview(String url) {
        Log.d("2", "URL=" + url);
//         mWebView.addJavascriptInterface(this, "android");
        //		mWebView.addJavascriptInterface(new );
        exposeJsInterface();
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置默认缩放方式尺寸是far
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        mWebView.loadUrl(url);

        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            // url拦截
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                //                view.loadUrl(url);
                // 相应完成返回true
                //                return true;

                if (url.contains("tel:")) {
                    //                    String mobile = url.substring(url.lastIndexOf("/") + 1);
                    mPhoneUri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_CALL, mPhoneUri);
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //申请电话权限
                        ActivityCompat.requestPermissions((WebViewActivity) mContext, new String[]{Manifest.permission.CALL_PHONE}, WebViewActivity.REQUEST_PHONE);
                    } else {
                        mContext.startActivity(intent);
                        ((WebViewActivity) mContext).startActivityForResult(intent, WebViewActivity.REQUEST_PHONE);
                    }

                    //这个超连接,java已经处理了，webview不要处理了
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }

            // 页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);

                super.onPageFinished(view, url);
            }

            // WebView加载的所有资源url
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //				view.loadData(errorHtml, "text/html; charset=UTF-8", null);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                // handler.cancel();// Android默认的处理方式
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }


        });

//        // 设置WebChromeClient
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            // 处理javascript中的alert
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                return super.onJsAlert(view, url, message, result);
//            }
//
//            @Override
//            // 处理javascript中的confirm
//            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
//                return super.onJsConfirm(view, url, message, result);
//            }
//
//            @Override
//            // 处理javascript中的prompt
//            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
//                return super.onJsPrompt(view, url, message, defaultValue, result);
//            }
//
//            // 设置网页加载的进度条
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                mProgressBar.setProgress(newProgress);
//                super.onProgressChanged(view, newProgress);
//            }
//
//            // 设置程序的Title
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//
//                super.onReceivedTitle(view, title);
//            }
//        });
        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            // 处理javascript中的alert
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            // 处理javascript中的confirm
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            // 处理javascript中的prompt
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            // 设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

            // 设置程序的Title
            @Override
            public void onReceivedTitle(WebView view, String title) {

                super.onReceivedTitle(view, title);
            }

            /**************************************上传文件**********************************************************/
            //        //The undocumented magic method override
            //        //Eclipse will swear at you if you try to put @Override here
            //        // For Android 3.0+
            //        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            //
            //                            mUploadMessage = uploadMsg;
            ////            ((MainActivity) mContext).setUploadMessage(uploadMsg);
            //            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            //            i.addCategory(Intent.CATEGORY_OPENABLE);
            //            i.setType("image/*");
            //            ((MainActivity) mContext).startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            //
            //        }
            //
            //            // For Android 3.0+
            //            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            //                                mUploadMessage = uploadMsg;
            ////                ((MainActivity) mContext).setUploadMessage(uploadMsg);
            //                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            //                i.addCategory(Intent.CATEGORY_OPENABLE);
            //                i.setType("*/*");
            //                ((MainActivity) mContext).startActivityForResult(
            //                        Intent.createChooser(i, "File Browser"),
            //                        FILECHOOSER_RESULTCODE);
            //            }
            //
            //            //For Android 4.1
            //            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            //                //                mUploadMessage = uploadMsg;
            ////                ((MainActivity) mContext).setUploadMessage(uploadMsg);
            //                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            //                i.addCategory(Intent.CATEGORY_OPENABLE);
            //                i.setType("image/*");
            //                ((MainActivity) mContext).startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            //
            //            }
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                this.openFileChooser(uploadMsg, "image/*");
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                this.openFileChooser(uploadMsg, acceptType, null);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                //        if (mUploadMessage!=null){
                //            mUploadMessage.onReceiveValue(null);
                //        }
                //                mUploadMessage = uploadMsg;
                if (mWebView.getContext() instanceof WebViewActivity) {
                    ((WebViewActivity) mWebView.getContext()).setUploadMessage(uploadMsg);
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    ((WebViewActivity) mContext).startActivityForResult(Intent.createChooser(i, "Image Browser"), WebViewActivity.FILECHOOSER_RESULTCODE);
                }
            }

            /**********************5.0调用*********************/
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, WebChromeClient.FileChooserParams fileChooserParams) {
                //        if (mUploadMessageArray!=null){
                //            mUploadMessageArray.onReceiveValue(null);
                //        }
                //                mUploadMessageForAndroid5=uploadMsg;
                if (mWebView.getContext() instanceof WebViewActivity) {
                    ((WebViewActivity) mContext).setUploadMessageForAndroid5(uploadMsg);
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    ((WebViewActivity) mContext).startActivityForResult(Intent.createChooser(i, "Image Browser"), WebViewActivity.FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
                    return true;
                } else {
                    return false;
                }
            }


        });

        /********************下载管理******************************/
        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // 监听下载功能，当用户点击下载链接的时候，直接调用系统的浏览器来下载

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
        /**************************************************/

        mWebView.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键

                        mWebView.goBack(); // 后退

                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    public boolean canBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return false;
        }
        return true;
    }

    @SuppressLint("JavascriptInterface")
    private void exposeJsInterface() {
//        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)) {
//            Log.i(TAG, "Disabled addJavascriptInterface() bridge since Android version is old.");
//            // Bug being that Java Strings do not get converted to JS strings automatically.
//            // This isn't hard to work-around on the JS side, but it's easier to just
//            // use the prompt bridge instead.
//            return;
//        }
        //        this.addJavascriptInterface(new ExposedJsApi(bridge), "_cordovaNative");
        mWebView.addJavascriptInterface(new Object() {

           /* @JavascriptInterface
            public void onShareWechatSession(String title, String content, String url, String imageUrl) {
                interfacemy.onShareWechatSession(title, content, url, imageUrl);
            }

            @JavascriptInterface
            public void onShareWechatTimeLine(String title, String content, String url, String imageUrl) {
                interfacemy.onShareWechatTimeLine(title, content, url, imageUrl);
            }

            @JavascriptInterface
            public void updateNavigationTitle(String title) {
                interfacemy.updateNavigationTitle(title);
            }

            @JavascriptInterface
            public void closeIndex(String type) {
                interfacemy.closeIndex(type);
            }

            @JavascriptInterface
            public void jumpToService() {
                interfacemy.jumpToService();
            }

            @JavascriptInterface
            public void goLogin() {
                interfacemy.goLogin();
            }*/

        }, "_webNativeJS");
    }


    /**
     * @VincentZX
     */
    private WebViewInterface interfacemy;

    public interface WebViewInterface {

       /* void onShareWechatSession(String shareTitle, String shareDesc, String shareLink, String shareImgUrl);//邀请好友微信好友

        void onShareWechatTimeLine(String shareTitle, String shareDesc, String shareLink, String shareImgUrl);//邀请好友微信朋友圈

        void updateNavigationTitle(String title);

        void closeIndex(String type);

        void jumpToService();*/

        void goLogin();
    }

    public void setInterfacemy(final WebViewInterface interfacemy) {
        this.interfacemy = interfacemy;
    }

    public Uri getPhoneUri() {
        return mPhoneUri;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
