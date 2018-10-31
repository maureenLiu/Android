该例子包含了绑定本地服务和远程服务。</br>
通过两个按钮控制。</br>
如果只是绑定了其中一个服务，然后点击back键退出当前界面，则程序会出现异常。因为在onDestory里解绑了一个并未绑定的Connection.  </br>  

①绑定本地服务时：<br>
onBind返回的IBinder对象值：<br>
onBind:mServiceBinder=com.example.maureen.mytestbindservice.TestLocalService$TestLocalServiceBinder@3ef6efd<br>
onServiceConection中的IBinder参数：<br>
onServiceConnected:iBinder=com.example.maureen.mytestbindservice.TestLocalService$TestLocalServiceBinder@3ef6efd  </br>  

②绑定远程服务时：
onBind返回的IBinder对象值：<br>
onBind:mRemoteBinder=com.example.maureen.mytestbindservice.TestRemoteService$RemoteServiceImpl@3920201<br>
onServiceConection中的IBinder参数：<br>
onServiceConnected:iBinder=android.os.BinderProxy@6ff13d8 <br>

更多分析见CSDN博客：https://blog.csdn.net/u011386173/article/details/83575069

