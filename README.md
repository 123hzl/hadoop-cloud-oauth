#租户管理

#请求日志管理

#登陆授权管理

#黑白明单管理，禁止授权

参考：https://www.cnblogs.com/ifme/p/12188982.html
参考：https://blog.csdn.net/qq_27828675/article/details/82466599

#获取授权
http://localhost:8080/workflow/requestlogs/1463775644510908418
授权码模式：

1：获取授权码：http://localhost:8888/oauth/authorize?response_type=code&client_id=hadoop 会跳转到登陆界面，登陆后授权返回授权码
2：根据授权码获取token: curl -i -X POST -d "username=hzl&password=123&grant_type=authorization_code&client_id=hadoop&client_secret=123&code=EEzo3S" http://localhost:8888/oauth/token
3：携带token访问：http://localhost:8080/workflow/requestlogs/1463775644510908418?access_token=c574806a-7d94-4fd8-b9d0-3ae25526f465


密码模式：
curl -i -X POST -d "username=hzl&password=123&grant_type=password&client_id=hadoop&client_secret=123" http://localhost:8888/oauth/token 获取token